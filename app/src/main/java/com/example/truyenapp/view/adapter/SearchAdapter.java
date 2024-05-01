package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.CTTruyen;
import com.example.truyenapp.R;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.ModelSearch;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.TimKiemViewHolder> {
    private Context context;
    private List<ModelSearch> list;
    private String email;

    public SearchAdapter(Context context, List<ModelSearch> list, String email) {
        this.context = context;
        this.list = list;
        this.email = email;
    }

    public void setData(List<ModelSearch> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimKiemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_search, parent, false);
        return new SearchAdapter.TimKiemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimKiemViewHolder holder, int position) {
        ModelSearch commic = list.get(position);
        if (commic == null) {
            return;
        }
        Glide.with(this.context).load(commic.getLinkImage()).into(holder.img_timkiem);
        holder.tv_timkiem_tentruyen.setText(commic.getNameStory());
        holder.tv_timkiem_lx.setText("Lượt xem: " + commic.getView());
        holder.tv_timkiem_ch.setText("Chapter: " + commic.getChapter());
        holder.tv_timkiem_dg.setText("Đánh giá: " + commic.getEvaluate());
        holder.tv_timkiem_theloai.setText(commic.getCategory());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), CTTruyen.class);
            intent.putExtra("email", email);
            intent.putExtra(BundleConstraint.ID_COMMIC, commic.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class TimKiemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_timkiem_tentruyen, tv_timkiem_lx, tv_timkiem_dg, tv_timkiem_ch, tv_timkiem_theloai;
        private ImageView img_timkiem;

        public TimKiemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_timkiem_tentruyen = itemView.findViewById(R.id.tv_timkiem_tentruyen);
            tv_timkiem_dg = itemView.findViewById(R.id.tv_timkiem_dg);
            tv_timkiem_lx = itemView.findViewById(R.id.tv_timkiem_lx);
            tv_timkiem_ch = itemView.findViewById(R.id.tv_timkiem_ch);
            img_timkiem = itemView.findViewById(R.id.img_timkiem);
            tv_timkiem_theloai = itemView.findViewById(R.id.tv_timkiem_theloai);
        }
    }
}
