package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Evaluate;
import com.example.truyenapp.model.Story;

import java.util.ArrayList;
import java.util.List;

public class ShowDanhGiaAdapter extends RecyclerView.Adapter<ShowDanhGiaAdapter.ShowDanhGiaViewHolder>{
    private Context context;
    private List<Evaluate> list;
    private Database db;

    public ShowDanhGiaAdapter(Context context, List<Evaluate> list, Database db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public ShowDanhGiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_tong,parent,false);
        return new ShowDanhGiaAdapter.ShowDanhGiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowDanhGiaViewHolder holder, int position) {

        Evaluate evaluate =list.get(position);
        if(evaluate ==null){
            return;
        }

        int idtruyen=db.getIdTruyen(evaluate.getIdChapter());
        String lenhSQlite="select * from truyen where id="+idtruyen;
        ArrayList<Story> list=db.getTruyen(lenhSQlite);
        Story story =list.get(0);

        Glide.with(this.context).load(story.getLinkImage()).into(holder.img_tong_truyen);
        holder.tv_tong_ngaydang.setText(evaluate.getEvaluateDate());
        holder.tv_tong_pl.setText("Đánh giá: "+ evaluate.getStar());
        holder.tv_tong_tenchapter.setText(db.getTenChapter(evaluate.getIdChapter()));
        holder.tv_tong_tentruyen.setText(story.getNameStory());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ShowDanhGiaViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_tong_tentruyen,tv_tong_tenchapter,tv_tong_pl,tv_tong_ngaydang;
        private ImageView img_tong_truyen;

        public ShowDanhGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tong_tentruyen=itemView.findViewById(R.id.tv_tong_tentruyen);
            tv_tong_tenchapter=itemView.findViewById(R.id.tv_tong_tenchapter);
            tv_tong_pl=itemView.findViewById(R.id.tv_tong_pl);
            tv_tong_ngaydang=itemView.findViewById(R.id.tv_tong_ngaydang);
            img_tong_truyen=itemView.findViewById(R.id.img_tong_truyen);
        }
    }
}
