package com.example.truyenapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.truyenapp.paging.PagingAdapter;
import com.example.truyenapp.utils.Format;
import com.example.truyenapp.view.activity.DetailComicActivity;

import java.util.List;

public class SearchAdapter extends PagingAdapter<ModelSearch, SearchAdapter.SearchViewHolder> {
    public SearchAdapter(Context context, List<ModelSearch> list) {
        super(context, list);
        setItemRcv(R.layout.item_rcv_search);
    }

    public void setData(List<ModelSearch> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    protected SearchViewHolder createItemViewHolder(View view) {
        return new SearchViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void bindData(SearchViewHolder holder, ModelSearch comic) {
        if (comic == null) {
            return;
        }
        Log.d("data", comic.toString());
        Glide.with(this.context).load(comic.getLinkImage()).into(holder.thumbnail);
        holder.name.setText(comic.getNameStory());
        holder.view.setText("Lượt xem: " + comic.getView());
        holder.chapters.setText("Chapter: " + comic.getChapter());
        holder.review.setText("Đánh giá: " + Format.roundNumber(comic.getEvaluate()));
        holder.category.setText(comic.getCategory());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailComicActivity.class);
            intent.putExtra(BundleConstraint.ID_COMIC, comic.getId());
            holder.itemView.getContext().startActivity(intent);
        });
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
