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

public class NewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADING = 1;
    private boolean isLoading;
    private Context context;
    private List<ClassifyStory> listComic;

    public NewAdapter(Context context, List<ClassifyStory> listComic) {
        this.context = context;
        this.listComic = listComic;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (TYPE_ITEM == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_rank, parent, false);
            return new NewAdapter.NewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
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


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM) {
            NewHolder newHolder = (NewHolder) holder;
            ClassifyStory comic = listComic.get(position);
            if (comic == null) {
                return;
            }
            String publishDate = Format.formatDate(comic.getPostingDate(), "yyyy-MM-dd", "dd-MM-yyyy");
            Glide.with(this.context).load(comic.getLinkImage()).into(newHolder.imgComic);
            newHolder.nameComic.setText(comic.getNameStory());
            newHolder.dateComic.setText("Ngày đăng: " + publishDate);
            newHolder.info.setVisibility(View.GONE);
            newHolder.detailComicView.setOnClickListener(view -> {
                Intent intent = new Intent(holder.itemView.getContext(), CTTruyen.class);
                intent.putExtra(BundleConstraint.ID_COMMIC, comic.getId());
                holder.itemView.getContext().startActivity(intent);
            });
        } else {
            ((LoadingViewHolder) holder).getProgressBar().setIndeterminate(true);
        }
    }


    @Override
    public int getItemCount() {
        if (listComic != null)
            return listComic.size();
        return 0;
    }

    public void addFooterLoading() {
        isLoading = true;
        listComic.add(null);
    }

    public void removeFooterLoading() {
        isLoading = false;
        int position = listComic.size() - 1;
        ClassifyStory comic = listComic.get(position);
        if (comic != null) {
            listComic.remove(position);
            notifyItemRemoved(position);
        }
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
