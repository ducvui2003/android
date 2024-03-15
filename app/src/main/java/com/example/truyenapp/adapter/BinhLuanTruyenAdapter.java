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
import com.example.truyenapp.model.TaiKhoan;

import java.util.List;

public class BinhLuanTruyenAdapter extends RecyclerView.Adapter<BinhLuanTruyenAdapter.BinhLuanTruyenViewHolder>{

    private Context context;
    private List<BinhLuan> list;
    private Database db;

    public BinhLuanTruyenAdapter(Context context, List<BinhLuan> list, Database db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public BinhLuanTruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_binhluan_truyen,parent,false);
        return new BinhLuanTruyenAdapter.BinhLuanTruyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinhLuanTruyenViewHolder holder, int position) {
        BinhLuan binhLuan=list.get(position);
        if(binhLuan==null){
            return;
        }

        TaiKhoan taiKhoan=db.getTaiKhoanId(binhLuan.getIdtaikhoan());
        Glide.with(this.context).load(taiKhoan.getLinkanh()).into(holder.img_avatar);
        String email=db.getEmail(binhLuan.getIdtaikhoan());
        String tenchapter=db.getTenChapter(binhLuan.getIdchapter());
        holder.tv_taikhoan_blt.setText(email);
        holder.tv_nd_blt.setText(binhLuan.getNoidung());
        holder.tv_ngaybinhluant.setText(binhLuan.getNgaydang());
        holder.tv_tenchapter_blt.setText(tenchapter);
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class BinhLuanTruyenViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_taikhoan_blt,tv_nd_blt,tv_ngaybinhluant,tv_tenchapter_blt;
        private ImageView img_avatar;

        public BinhLuanTruyenViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_nd_blt=itemView.findViewById(R.id.tv_nd_blt);
            tv_taikhoan_blt=itemView.findViewById(R.id.tv_taikhoan_blt);
            tv_ngaybinhluant=itemView.findViewById(R.id.tv_ngaybinhluant);
            tv_tenchapter_blt=itemView.findViewById(R.id.tv_tenchapter_blt);
            img_avatar=itemView.findViewById(R.id.img_avatar);
        }
    }
}
