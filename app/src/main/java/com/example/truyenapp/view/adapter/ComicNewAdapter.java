package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.paging.PagingAdapter;
import com.example.truyenapp.utils.Format;
import com.example.truyenapp.view.activity.DetailComicActivity;

import java.util.List;

public class ComicNewAdapter extends PagingAdapter<ClassifyStory, ComicNewAdapter.NewHolder> {

    public ComicNewAdapter(Context context, List<ClassifyStory> list) {
        super(context, list);
    }

    @Override
    protected NewHolder createItemViewHolder(View view) {
        return new NewHolder(view);
    }


    public void setData(List<ClassifyStory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    protected void bindData(NewHolder holder, ClassifyStory comic) {
        if (comic == null) {
            return;
        }
        String publishDate = Format.formatDate(comic.getPostingDate(), "yyyy-MM-dd", "dd-MM-yyyy");
        Glide.with(this.context).load(comic.getLinkImage()).into(holder.imgComic);
        holder.nameComic.setText(comic.getNameStory());
        holder.dateComic.setText("Ngày đăng: " + Format.formatDate(comic.getPostingDate(), "yyyy-MM-dd", "dd-MM-yyyy"));
        holder.info.setVisibility(View.GONE);
        holder.detailComicView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailComicActivity.class);
            intent.putExtra(BundleConstraint.ID_COMIC, comic.getId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    public class NewHolder extends RecyclerView.ViewHolder {
        private View detailComicView;
        private ImageView imgComic;
        private TextView nameComic;
        private TextView dateComic;
        private TextView info;

        public NewHolder(@NonNull View view) {
            super(view);
            this.imgComic = view.findViewById(R.id.item_rcv_thumnail);
            this.nameComic = view.findViewById(R.id.item_rcv_name_commic);
            this.dateComic = view.findViewById(R.id.item_rcv_date_commic);
            this.detailComicView = view.findViewById(R.id.item_detail_commic);
            this.info = view.findViewById(R.id.item_rcv_info_commic);

        }
    }
}
