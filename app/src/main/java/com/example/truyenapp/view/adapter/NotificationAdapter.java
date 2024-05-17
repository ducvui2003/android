package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.model.Notification;
import com.example.truyenapp.model.Story;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ThongBaoViewHolder> {
    private Context context;
    private ArrayList<Notification> list;

    public NotificationAdapter(Context context, ArrayList<Notification> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(ArrayList<Notification> notifications) {
        this.list = notifications;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThongBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_thongbao, parent, false);
        return new NotificationAdapter.ThongBaoViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ThongBaoViewHolder holder, int position) {
        Notification notification = list.get(position);
        if (notification == null) {
            return;
        }
        holder.tv_ndtb.setText(notification.getContent());
        holder.tv_tieude.setText(notification.getTitle());

        if (notification.getPostingDate() != null) {
            holder.tv_ngaydangtb.setText(notification.getPostingDate().toString());
        } else {
            holder.tv_ngaydangtb.setText("Không có ngày đăng");
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class ThongBaoViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tieude, tv_ndtb, tv_ngaydangtb;

        public ThongBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ndtb = itemView.findViewById(R.id.tv_ndtb);
            tv_ngaydangtb = itemView.findViewById(R.id.tv_ngaydangtb);
            tv_tieude = itemView.findViewById(R.id.tv_tieudetb);
        }
    }
}
