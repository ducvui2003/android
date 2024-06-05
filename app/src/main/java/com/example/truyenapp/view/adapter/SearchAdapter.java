package com.example.truyenapp.view.adapter;

import android.annotation.SuppressLint;
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
import com.example.truyenapp.R;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.ModelSearch;
import com.example.truyenapp.view.activity.DetailComicActivity;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Context context;
    private List<ModelSearch> list;

    public SearchAdapter(Context context, List<ModelSearch> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<ModelSearch> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_search, parent, false);
        return new SearchViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        ModelSearch commic = list.get(position);
        if (commic == null) {
            return;
        }
        Glide.with(this.context).load(commic.getLinkImage()).into(holder.thumbnail);
        holder.name.setText(commic.getNameStory());
        holder.view.setText("Lượt xem: " + commic.getView());
        holder.chapters.setText("Chapter: " + commic.getChapter());
        holder.review.setText("Đánh giá: " + Math.round(commic.getEvaluate() * 10) / 10.0);
        holder.category.setText(commic.getCategory());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailComicActivity.class);
            intent.putExtra(BundleConstraint.ID_COMIC, commic.getId());
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

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        private TextView name, view, review, chapters, category;
        private ImageView thumbnail;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_item_search_name);
            review = itemView.findViewById(R.id.tv_item_search_vote);
            view = itemView.findViewById(R.id.tv_item_search_view);
            chapters = itemView.findViewById(R.id.tv_item_search_chapter_num);
            thumbnail = itemView.findViewById(R.id.image_item_search);
            category = itemView.findViewById(R.id.tv_item_search_category);
        }
    }
}
