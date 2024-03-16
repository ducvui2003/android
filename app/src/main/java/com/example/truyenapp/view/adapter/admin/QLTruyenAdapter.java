package com.example.truyenapp.view.adapter.admin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.admin.ShowThongTinTruyen;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Truyen;

import java.util.ArrayList;

public class QLTruyenAdapter extends RecyclerView.Adapter<QLTruyenAdapter.QLTruyenViewHolder>{
    private Context context;
    private ArrayList<Truyen> list;
    private Database db;

    public QLTruyenAdapter(Context context, ArrayList<Truyen> list, Database db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public QLTruyenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_qlthongke,parent,false);
        return new QLTruyenAdapter.QLTruyenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLTruyenViewHolder holder, int position) {
        Truyen truyen=list.get(position);
        if(truyen==null){
            return;
        }

        holder.tv_idtruyen.setText(""+truyen.getId());
        holder.tv_tentruyen.setText(truyen.getTentruyen());
        holder.ll_rcv_qltruyen.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowThongTinTruyen.class);
            intent.putExtra("id_truyen",truyen.getId());
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

    public class QLTruyenViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_idtruyen,tv_tentruyen;
        private LinearLayout ll_rcv_qltruyen;

        public QLTruyenViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_idtruyen=itemView.findViewById(R.id.tv_idqlthongke);
            tv_tentruyen=itemView.findViewById(R.id.tv_qltktentruyen);
            ll_rcv_qltruyen=itemView.findViewById(R.id.ll_rcv_qlthongke);

        }
    }
}
