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
import com.example.truyenapp.model.DoiThuong;
import com.example.truyenapp.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class LichSuDoiAdapter extends RecyclerView.Adapter<LichSuDoiAdapter.LichSuDoiViewHolder>{
    private Context context;
    private List<DoiThuong> list;
    private TaiKhoan taiKhoan;
    private Database db;

    public LichSuDoiAdapter(Context context, List<DoiThuong> list, TaiKhoan taiKhoan, Database db) {
        this.context = context;
        this.list = list;
        this.taiKhoan = taiKhoan;
        this.db = db;
    }

    @NonNull
    @Override
    public LichSuDoiAdapter.LichSuDoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_lichsudoi,parent,false);
        return new LichSuDoiAdapter.LichSuDoiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LichSuDoiAdapter.LichSuDoiViewHolder holder, int position) {
        DoiThuong doiThuong=list.get(position);
        if(doiThuong==null){
            return;
        }

        ArrayList<String> list=db.getVatPhamDoi(doiThuong.getIdvatpham());
        Glide.with(this.context).load(list.get(2)).into(holder.img_vatphamdoi);
        holder.tv_tenvatpham.setText(list.get(0));
        holder.tv_diem.setText("Điểm: "+list.get(1));
        holder.tv_ngaydoi.setText(doiThuong.getNgaydoi());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class LichSuDoiViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_vatphamdoi;
        private TextView tv_tenvatpham,tv_diem,tv_ngaydoi;

        public LichSuDoiViewHolder(@NonNull View itemView) {
            super(itemView);
            img_vatphamdoi=itemView.findViewById(R.id.img_vatphamdoi);
            tv_diem=itemView.findViewById(R.id.tv_diem);
            tv_tenvatpham=itemView.findViewById(R.id.tv_tenvatpham);
            tv_ngaydoi=itemView.findViewById(R.id.tv_ngaydoi);

        }
    }
}
