package com.example.truyenapp.adapter;

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
import com.example.truyenapp.activity.DocChapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Chapter;
import com.example.truyenapp.model.LichSuDocTruyen;
import com.example.truyenapp.model.TaiKhoan;
import com.example.truyenapp.model.Truyen;

import java.util.List;

public class TruyenDaDocAdapter extends RecyclerView.Adapter<TruyenDaDocAdapter.TruyenDaDocViewHolder>{
    private Context context;
    private List<LichSuDocTruyen> list;
    private TaiKhoan taiKhoan;
    private Database db;

    public TruyenDaDocAdapter(Context context, List<LichSuDocTruyen> list, TaiKhoan taiKhoan, Database db) {
        this.context = context;
        this.list = list;
        this.taiKhoan = taiKhoan;
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
        LichSuDocTruyen truyendadoc=list.get(position);
        if(truyendadoc==null){
            return;
        }

        int id=truyendadoc.getIdchapter();
        Chapter chapter=db.getOneChapter(id);
        Truyen truyen=db.getOneTruyen(chapter);
        String tenchaptermoinhat=db.getTenChapterNew(truyen.getId());

        Glide.with(this.context).load(truyen.getLinkhanh()).into(holder.img_truyendadoc);
        holder.tv_tentruyen.setText(truyen.getTentruyen());
        holder.tv_chapterdangxem.setText("Chapter đang xem: "+chapter.getTenchapter());
        holder.tv_chaptermoinhat.setText("Chapter mới nhất: "+tenchaptermoinhat);

        holder.ll_rcv_truyendadoc.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), DocChapter.class);
            intent.putExtra("id_truyen",truyen.getId());
            intent.putExtra("id_chapter",truyendadoc.getIdchapter());
            intent.putExtra("email",taiKhoan.getEmail());
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
