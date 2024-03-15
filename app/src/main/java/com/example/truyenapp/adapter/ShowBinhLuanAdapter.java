package com.example.truyenapp.adapter;

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
import com.example.truyenapp.model.BinhLuan;
import com.example.truyenapp.model.Truyen;

import java.util.ArrayList;
import java.util.List;

public class ShowBinhLuanAdapter extends RecyclerView.Adapter<ShowBinhLuanAdapter.ShowBinhLuanViewHolder>{
    private Context context;
    private List<BinhLuan> list;
    private Database db;

    public ShowBinhLuanAdapter(Context context, List<BinhLuan> list, Database db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public ShowBinhLuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_tong,parent,false);
        return new ShowBinhLuanAdapter.ShowBinhLuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowBinhLuanViewHolder holder, int position) {
        BinhLuan binhLuan=list.get(position);
        if(binhLuan==null){
            return;
        }

        int idtruyen=db.getIdTruyen(binhLuan.getIdchapter());
        String lenhSQlite="select * from truyen where id="+idtruyen;
        ArrayList<Truyen> list=db.getTruyen(lenhSQlite);
        Truyen truyen=list.get(0);

        Glide.with(this.context).load(truyen.getLinkhanh()).into(holder.img_tong_truyen);
        holder.tv_tong_ngaydang.setText(binhLuan.getNgaydang());
        holder.tv_tong_pl.setText(binhLuan.getNoidung());
        holder.tv_tong_tenchapter.setText(db.getTenChapter(binhLuan.getIdchapter()));
        holder.tv_tong_tentruyen.setText(truyen.getTentruyen());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ShowBinhLuanViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_tong_tentruyen,tv_tong_tenchapter,tv_tong_pl,tv_tong_ngaydang;
        private ImageView img_tong_truyen;

        public ShowBinhLuanViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_tong_tentruyen=itemView.findViewById(R.id.tv_tong_tentruyen);
            tv_tong_tenchapter=itemView.findViewById(R.id.tv_tong_tenchapter);
            tv_tong_pl=itemView.findViewById(R.id.tv_tong_pl);
            tv_tong_ngaydang=itemView.findViewById(R.id.tv_tong_ngaydang);
            img_tong_truyen=itemView.findViewById(R.id.img_tong_truyen);
        }
    }
}
