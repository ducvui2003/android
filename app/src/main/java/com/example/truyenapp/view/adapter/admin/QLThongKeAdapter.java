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

import com.example.truyenapp.admin.ShowThongTinThongKe;
import com.example.truyenapp.R;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Statistical;
import com.example.truyenapp.model.Story;

import java.util.ArrayList;

public class QLThongKeAdapter extends RecyclerView.Adapter<QLThongKeAdapter.QLThongKeViewHolder>{
    private Context context;
    private ArrayList<Statistical> list;
    private Database db;

    public QLThongKeAdapter(Context context, ArrayList<Statistical> list, Database db) {
        this.context = context;
        this.list = list;
        this.db = db;
    }

    @NonNull
    @Override
    public QLThongKeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.book_management_item,parent,false);
        return new QLThongKeAdapter.QLThongKeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QLThongKeViewHolder holder, int position) {
        Statistical statistical =list.get(position);
        if(statistical ==null){
            return;
        }

        holder.tv_idqlthongke.setText(""+ statistical.getId());
        Story story =db.getTruyenById(statistical.getIdStory());
        holder.tv_qltktentruyen.setText(story.getNameStory());
        holder.ll_rcv_qlthongke.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(), ShowThongTinThongKe.class);
            intent.putExtra("id_thongke", statistical.getId());
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
