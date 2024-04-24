package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.model.ClassifyStory;

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
        View view = View.inflate(context, R.layout.item_rcv_theloainew, null);
        return new RankViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        ClassifyStory commic = listCommic.get(position);
        if (commic == null) {
            return;
        }
//        holder.imgCommic.setImageResource(commic.getId());
        holder.nameCommic.setText(commic.getNameStory());
        holder.dateCommic.setText("Tổng lượt xem: " + commic.getView());
    }

    @Override
    public int getItemCount() {
        if (listCommic != null)
            return listCommic.size();
        else
            return 0;
    }

    public static class RankViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCommic;
        private TextView nameCommic;
        private TextView dateCommic;

        public RankViewHolder(View view) {
            super(view);
//            imgCommic = view.findViewById(R.id.item_rcv_thumnail);
            nameCommic = view.findViewById(R.id.item_rcv_name_commic);
            dateCommic = view.findViewById(R.id.item_rcv_date_commic);
        }
    }
}
