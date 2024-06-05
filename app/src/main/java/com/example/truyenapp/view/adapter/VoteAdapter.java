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
import com.example.truyenapp.view.activity.DetailComicActivity;
import com.example.truyenapp.R;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.paging.LoadingViewHolder;
import com.example.truyenapp.utils.Format;

import java.util.List;

public class VoteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private boolean isLoading;
    private Context context;
    private List<ClassifyStory> listCommic;

    public VoteAdapter(Context context, List<ClassifyStory> listCommic) {
        this.context = context;
        this.listCommic = listCommic;
    }

    public void setData(List<ClassifyStory> list) {
        this.listCommic = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (listCommic != null && position == listCommic.size() - 1 && isLoading)
            return TYPE_LOADING;
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (TYPE_ITEM == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_rank, parent, false);
            return new VoteViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            VoteViewHolder rankVoteHolder = (VoteViewHolder) holder;
            ClassifyStory commic = listCommic.get(position);
            if (commic == null) {
                return;
            }
            String publishDate = Format.formatDate(commic.getPostingDate(), "yyyy-MM-dd", "dd-MM-yyyy");
            Glide.with(this.context).load(commic.getLinkImage()).into(rankVoteHolder.imgCommic);
            rankVoteHolder.nameCommic.setText(commic.getNameStory());
            rankVoteHolder.info.setText("Đánh giá: " + commic.getEvaluate());
            rankVoteHolder.dateCommic.setText("Ngày đăng: " + publishDate);
            rankVoteHolder.detailCommicView.setOnClickListener(view -> {
                Intent intent = new Intent(holder.itemView.getContext(), DetailComicActivity.class);
                intent.putExtra(BundleConstraint.ID_COMMIC, commic.getId());
                holder.itemView.getContext().startActivity(intent);
            });
        } else {
            ((LoadingViewHolder) holder).getProgressBar().setIndeterminate(true);
        }
    }

    public void addFooterLoading() {
        isLoading = true;
        listCommic.add(new ClassifyStory());
    }

    public void removeFooterLoading() {
        isLoading = false;
        int position = listCommic.size() - 1;
        ClassifyStory commic = listCommic.get(position);
        if (commic != null) {
            listCommic.remove(position);
            notifyItemRemoved(position);
        }
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
