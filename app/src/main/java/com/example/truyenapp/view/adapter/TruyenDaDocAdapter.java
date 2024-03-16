package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.model.Story;
import com.example.truyenapp.view.activity.DocChapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Chapter;
import com.example.truyenapp.model.ReadingHistory;
import com.example.truyenapp.model.Account;

import java.util.List;

public class TruyenDaDocAdapter extends RecyclerView.Adapter<TruyenDaDocAdapter.TruyenDaDocViewHolder>{
    private Context context;
    private List<ReadingHistory> list;
    private Account account;
    private Database db;

    public TruyenDaDocAdapter(Context context, List<ReadingHistory> list, Account account, Database db) {
        this.context = context;
        this.list = list;
        this.account = account;
        this.db = db;
    }

    @NonNull
    @Override
    public TruyenDaDocAdapter.TruyenDaDocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_truyendadoc,parent,false);
        return new TruyenDaDocAdapter.TruyenDaDocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TruyenDaDocAdapter.TruyenDaDocViewHolder holder, int position) {
        ReadingHistory truyendadoc=list.get(position);
        if(truyendadoc==null){
            return;
        }

        int id=truyendadoc.getIdChapter();
        Chapter chapter=db.getOneChapter(id);
        Story story =db.getOneTruyen(chapter);
        String tenchaptermoinhat=db.getTenChapterNew(story.getId());

        Glide.with(this.context).load(story.getLinkImage()).into(holder.img_truyendadoc);
        holder.tv_tentruyen.setText(story.getNameStory());
        holder.tv_chapterdangxem.setText("Chapter đang xem: "+chapter.getTenchapter());
        holder.tv_chaptermoinhat.setText("Chapter mới nhất: "+tenchaptermoinhat);

        holder.ll_rcv_truyendadoc.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), DocChapter.class);
            intent.putExtra("id_truyen", story.getId());
            intent.putExtra("id_chapter",truyendadoc.getIdchapter());
            intent.putExtra("email", account.getEmail());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class TruyenDaDocViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_truyendadoc;
        private TextView tv_tentruyen,tv_chapterdangxem,tv_chaptermoinhat;
        private LinearLayout ll_rcv_truyendadoc;

        public TruyenDaDocViewHolder(@NonNull View itemView) {
            super(itemView);
            img_truyendadoc=itemView.findViewById(R.id.img_truyendadoc);
            tv_tentruyen=itemView.findViewById(R.id.tv_tentruyen);
            tv_chapterdangxem=itemView.findViewById(R.id.tv_chapterdangxem);
            tv_chaptermoinhat=itemView.findViewById(R.id.tv_chaptermoinhat);
            ll_rcv_truyendadoc=itemView.findViewById(R.id.ll_rcv_truyendadoc);
        }
    }
}
