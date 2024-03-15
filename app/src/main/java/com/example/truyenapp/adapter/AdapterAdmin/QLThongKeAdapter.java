package com.example.truyenapp.adapter.AdapterAdmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.admin.ShowThongTinThongKe;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.ThongKe;
import com.example.truyenapp.model.Truyen;

import java.util.ArrayList;

public class QLThongKeAdapter extends RecyclerView.Adapter<QLThongKeAdapter.QLThongKeViewHolder>{
    private Context context;
    private ArrayList<ThongKe> list;
    private Database db;

    public QLThongKeAdapter(Context context, ArrayList<ThongKe> list, Database db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public QLThongKeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qlthongke,parent,false);
        return new QLThongKeAdapter.QLThongKeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLThongKeViewHolder holder, int position) {
        ThongKe thongKe=list.get(position);
        if(thongKe==null){
            return;
        }

        holder.tv_idqlthongke.setText(""+thongKe.getId());
        Truyen truyen=db.getTruyenById(thongKe.getIdtruyen());
        holder.tv_qltktentruyen.setText(truyen.getTentruyen());
        holder.ll_rcv_qlthongke.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowThongTinThongKe.class);
            intent.putExtra("id_thongke",thongKe.getId());
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

    public class QLThongKeViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_idqlthongke,tv_qltktentruyen;
        private LinearLayout ll_rcv_qlthongke;

        public QLThongKeViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_idqlthongke=itemView.findViewById(R.id.tv_idqlthongke);
            tv_qltktentruyen=itemView.findViewById(R.id.tv_qltktentruyen);
            ll_rcv_qlthongke=itemView.findViewById(R.id.ll_rcv_qlthongke);
        }
    }
}
