package com.example.truyenapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.model.DiemThuong;

import java.util.ArrayList;

public class LSNhanDiemAdapter extends RecyclerView.Adapter<LSNhanDiemAdapter.LSNhanDiemViewHolder>{
    private Context context;
    private ArrayList<DiemThuong> list;

    public LSNhanDiemAdapter(Context context, ArrayList<DiemThuong> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LSNhanDiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_lsnhandiem,parent,false);
        return new LSNhanDiemAdapter.LSNhanDiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LSNhanDiemViewHolder holder, int position) {
        DiemThuong diemThuong=list.get(position);
        if(diemThuong==null){
            return;
        }

        holder.tv_diem.setText("+ "+diemThuong.getDiem()+ " điểm");
        holder.tv_ngaynhan.setText(diemThuong.getNgaynhan());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class LSNhanDiemViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_diem,tv_ngaynhan;

        public LSNhanDiemViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_diem=itemView.findViewById(R.id.tv_diem_lsnd);
            tv_ngaynhan=itemView.findViewById(R.id.tv_ngaynhan_lsnd);
        }
    }
}
