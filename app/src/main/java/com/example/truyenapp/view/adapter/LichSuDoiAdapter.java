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
import com.example.truyenapp.model.RewardExchange;
import com.example.truyenapp.model.Account;

import java.util.ArrayList;
import java.util.List;

public class LichSuDoiAdapter extends RecyclerView.Adapter<LichSuDoiAdapter.LichSuDoiViewHolder>{
    private Context context;
    private List<RewardExchange> list;
    private Account account;
    private Database db;

    public LichSuDoiAdapter(Context context, List<RewardExchange> list, Account account, Database db) {
        this.context = context;
        this.list = list;
        this.account = account;
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
        RewardExchange rewardExchange =list.get(position);
        if(rewardExchange ==null){
            return;
        }

        ArrayList<String> list=db.getVatPhamDoi(rewardExchange.getIdItems());
        Glide.with(this.context).load(list.get(2)).into(holder.img_vatphamdoi);
        holder.tv_tenvatpham.setText(list.get(0));
        holder.tv_diem.setText("Điểm: "+list.get(1));
        holder.tv_ngaydoi.setText(rewardExchange.getExchangeDate());
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
            tv_diem=itemView.findViewById(R.id.tv_store_item_score);
            tv_tenvatpham=itemView.findViewById(R.id.tv_store_item_name);
            tv_ngaydoi=itemView.findViewById(R.id.tv_ngaydoi);

        }
    }
}
