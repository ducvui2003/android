package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
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

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteViewHolder> {
    private Context context;
    private List<ClassifyStory> listCommic;
    private String email;
    public VoteAdapter(Context context, List<ClassifyStory> listCommic) {
        this.context = context;
        this.listCommic = listCommic;
    }
    public VoteAdapter(Context context, List<ClassifyStory> listCommic, String email) {
        this.context = context;
        this.listCommic = listCommic;
        this.email = email;
    }
    public void setData(List<ClassifyStory> list) {
        this.listCommic = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_rank, parent, false);
        return new VoteAdapter.VoteViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull VoteViewHolder holder, int position) {
        ClassifyStory commic = listCommic.get(position);
        if (commic == null) {
            return;
        }
        String publishDate = Format.formatDate(commic.getPostingDate(), "yyyy-MM-dd", "dd-MM-yyyy");
        Glide.with(this.context).load(commic.getLinkImage()).into(holder.imgCommic);
        holder.nameCommic.setText(commic.getNameStory());
        holder.info.setText("Đánh giá: " + commic.getEvaluate());
        holder.dateCommic.setText("Ngày đăng: " + publishDate);
        holder.detailCommicView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), CTTruyen.class);
            intent.putExtra(BundleConstraint.ID_COMMIC, commic.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        if (listCommic != null) {
            return listCommic.size();
        }
        return 0;
    }
    public class VoteViewHolder extends RecyclerView.ViewHolder {
        private View detailCommicView;
        private ImageView imgCommic;
        private TextView nameCommic;
        private TextView dateCommic;
        private TextView info;
        public VoteViewHolder(View view) {
            super(view);
            this.imgCommic = view.findViewById(R.id.item_rcv_thumnail);
            this.nameCommic = view.findViewById(R.id.item_rcv_name_commic);
            this.info = view.findViewById(R.id.item_rcv_info_commic);
            this.dateCommic = view.findViewById(R.id.item_rcv_date_commic);
            this.detailCommicView = view.findViewById(R.id.item_detail_commic);
        }
    }
}
