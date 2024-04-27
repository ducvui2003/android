package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.CTTruyen;
import com.example.truyenapp.R;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.utils.Format;

import java.util.List;

public class RankViewAdapter extends RecyclerView.Adapter<RankViewAdapter.RankViewHolder> {
    private Context context;
    private List<ClassifyStory> listCommic;

    public RankViewAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ClassifyStory> list) {
        this.listCommic = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_rcv_rank, null);
        return new RankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        ClassifyStory commic = listCommic.get(position);
        if (commic == null) {
            return;
        }
        String publishDate = Format.formatDate(commic.getPostingDate(), "yyyy-MM-dd", "dd-MM-yyyy");
        Glide.with(this.context).load(commic.getLinkImage()).into(holder.imgCommic);
        holder.nameCommic.setText(commic.getNameStory());
        holder.info.setText("Tổng lượt xem: " + commic.getView());
        holder.dateCommic.setText("Ngày đăng" + publishDate);
        holder.detailCommicView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), CTTruyen.class);
            intent.putExtra(BundleConstraint.ID_COMMIC, commic.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (listCommic != null)
            return listCommic.size();
        else
            return 0;
    }

    public static class RankViewHolder extends RecyclerView.ViewHolder {
        private View detailCommicView;
        private ImageView imgCommic;
        private TextView nameCommic;
        private TextView dateCommic;
        private TextView info;

        public RankViewHolder(View view) {
            super(view);
            this.imgCommic = view.findViewById(R.id.item_rcv_thumnail);
            this.nameCommic = view.findViewById(R.id.item_rcv_name_commic);
            this.info = view.findViewById(R.id.item_rcv_info_commic);
            this.dateCommic = view.findViewById(R.id.item_rcv_date_commic);
            this.detailCommicView = view.findViewById(R.id.item_detail_commic);
        }
    }
}
