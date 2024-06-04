package com.example.truyenapp.view.adapter;

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
import com.example.truyenapp.CTTruyen;
import com.example.truyenapp.R;
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.paging.LoadingViewHolder;
import com.example.truyenapp.paging.PagingAdapter;
import com.example.truyenapp.utils.Format;

import java.util.List;

public class ComicVoteAdapter extends PagingAdapter<ClassifyStory, ComicVoteAdapter.VoteViewHolder> {
    public ComicVoteAdapter(Context context, List<ClassifyStory> list) {
        super(context, list);
    }

    public void setData(List<ClassifyStory> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    protected VoteViewHolder createItemViewHolder(View view) {
        return new VoteViewHolder(view);
    }

    @Override
    protected void bindData(VoteViewHolder holder, ClassifyStory commic) {
        if (commic == null) {
            return;
        }
        Log.d("date", commic.getPostingDate() + "");
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
