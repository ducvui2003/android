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
import com.example.truyenapp.paging.LoadingViewHolder;
import com.example.truyenapp.utils.Format;

import java.util.List;

public class ComicViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private boolean isLoading;
    private Context context;
    private List<ClassifyStory> listComic;

    public ComicViewAdapter(Context context, List<ClassifyStory> listComic) {
        this.context = context;
        this.listComic = listComic;
    }

    public void setData(List<ClassifyStory> list) {
        this.listComic = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (listComic != null && position == listComic.size() - 1 && isLoading)
            return TYPE_LOADING;
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (TYPE_ITEM == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_rank, parent, false);
            return new RankViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            RankViewHolder rankViewHolder = (RankViewHolder) holder;
            ClassifyStory comic = listComic.get(position);
            if (comic == null) {
                return;
            }
            String publishDate = Format.formatDate(comic.getPostingDate(), "yyyy-MM-dd", "dd-MM-yyyy");
            Glide.with(this.context).load(comic.getLinkImage()).into(rankViewHolder.imgComic);
            rankViewHolder.nameComic.setText(comic.getNameStory());
            rankViewHolder.info.setText("Tổng lượt xem: " + comic.getView());
            rankViewHolder.dateComic.setText("Ngày đăng: " + publishDate);
            rankViewHolder.detailComicView.setOnClickListener(view -> {
                Intent intent = new Intent(holder.itemView.getContext(), CTTruyen.class);
                intent.putExtra(BundleConstraint.ID_COMMIC, comic.getId());
                holder.itemView.getContext().startActivity(intent);
            });
        } else {
            ((LoadingViewHolder) holder).getProgressBar().setIndeterminate(true);
        }

    }

    public void addFooterLoading() {
        isLoading = true;
        listComic.add(null);
    }

    public void removeFooterLoading() {
        isLoading = false;
        int position = listComic.size() - 1;
        ClassifyStory commic = listComic.get(position);
        if (commic != null) {
            listComic.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public int getItemCount() {
        if (listComic != null)
            return listComic.size();
        return 0;
    }

    public static class RankViewHolder extends RecyclerView.ViewHolder {
        private View detailComicView;
        private ImageView imgComic;
        private TextView nameComic;
        private TextView dateComic;
        private TextView info;


        public RankViewHolder(View view) {
            super(view);
            this.imgComic = view.findViewById(R.id.item_rcv_thumnail);
            this.nameComic = view.findViewById(R.id.item_rcv_name_commic);
            this.info = view.findViewById(R.id.item_rcv_info_commic);
            this.dateComic = view.findViewById(R.id.item_rcv_date_commic);
            this.detailComicView = view.findViewById(R.id.item_detail_commic);
        }
    }
}
