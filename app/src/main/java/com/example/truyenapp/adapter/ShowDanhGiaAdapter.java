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
import com.example.truyenapp.model.DanhGia;
import com.example.truyenapp.model.Truyen;

import java.util.ArrayList;
import java.util.List;

public class ShowDanhGiaAdapter extends RecyclerView.Adapter<ShowDanhGiaAdapter.ShowDanhGiaViewHolder>{
    private Context context;
    private List<DanhGia> list;
    private Database db;

    public ShowDanhGiaAdapter(Context context, List<DanhGia> list, Database db) {
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

        DanhGia danhGia=list.get(position);
        if(danhGia==null){
            return;
        }

        int idtruyen=db.getIdTruyen(danhGia.getIdchapter());
        String lenhSQlite="select * from truyen where id="+idtruyen;
        ArrayList<Truyen> list=db.getTruyen(lenhSQlite);
        Truyen truyen=list.get(0);

        Glide.with(this.context).load(truyen.getLinkhanh()).into(holder.img_tong_truyen);
        holder.tv_tong_ngaydang.setText(danhGia.getNgaydanhgia());
        holder.tv_tong_pl.setText("Đánh giá: "+danhGia.getSosao());
        holder.tv_tong_tenchapter.setText(db.getTenChapter(danhGia.getIdchapter()));
        holder.tv_tong_tentruyen.setText(truyen.getTentruyen());
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
