package com.example.truyenapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.activity.DocChapter;
import com.example.truyenapp.model.Chapter;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder>{
    private Context context;
    private List<Chapter> list;
    private String email;

    public ChapterAdapter(Context context,List<Chapter> list,String email) {
        this.context = context;
        this.list = list;
        this.email=email;
    }

    @NonNull
    @Override
    public ChapterAdapter.ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_chapter,parent,false);
        return new ChapterAdapter.ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterAdapter.ChapterViewHolder holder, int position) {
        Chapter chapter=list.get(position);
        if(chapter==null){
            return;
        }
        holder.tv_chapter.setText(chapter.getTenchapter());
        holder.tv_luotxem.setText("Lượt xem: "+chapter.getSoluotxem());
        holder.tv_ngaydang.setText("Ngày đăng: "+chapter.getNgaydang());
        holder.ll_rcv_chapter.setOnClickListener(view -> {
            if(email!=null){
                Intent intent=new Intent(holder.itemView.getContext(), DocChapter.class);
                intent.putExtra("id_chapter",chapter.getId());
                intent.putExtra("id_truyen",chapter.getIdtruyen());
                intent.putExtra("email",email);
                holder.itemView.getContext().startActivity(intent);
            }else {
             Toast.makeText(this.context,"Vui lòng đăng nhập để xem nội dung truyện!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_chapter,tv_ngaydang,tv_luotxem;
        private LinearLayout ll_rcv_chapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_chapter=itemView.findViewById(R.id.tv_chapter);
            tv_ngaydang=itemView.findViewById(R.id.tv_ngaydang);
            tv_luotxem=itemView.findViewById(R.id.tv_luotxem);
            ll_rcv_chapter=itemView.findViewById(R.id.ll_rcv_chapter);
        }
    }
}
