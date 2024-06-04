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
import com.example.truyenapp.paging.PagingAdapter;
import com.example.truyenapp.utils.Format;

import java.util.List;

public class ComicViewAdapter extends PagingAdapter<ClassifyStory, ComicViewAdapter.RankViewHolder> {

    public ComicViewAdapter(Context context, List<ClassifyStory> list) {
        super(context, list);
    }

    public void setData(List<ClassifyStory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    protected RankViewHolder createItemViewHolder(View view) {
        return new RankViewHolder(view);
    }

    @Override
    protected void bindData(RankViewHolder holder, ClassifyStory comic) {
        if (comic == null) {
            return;
        }
        String publishDate = Format.formatDate(comic.getPostingDate(), "yyyy-MM-dd", "dd-MM-yyyy");
        Glide.with(this.context).load(comic.getLinkImage()).into(holder.imgComic);
        holder.nameComic.setText(comic.getNameStory());
        holder.info.setText("Tổng lượt xem: " + comic.getView());
        holder.dateComic.setText("Ngày đăng: " + publishDate);
        holder.detailComicView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), CTTruyen.class);
            intent.putExtra(BundleConstraint.ID_COMMIC, comic.getId());
            holder.itemView.getContext().startActivity(intent);
        });
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
