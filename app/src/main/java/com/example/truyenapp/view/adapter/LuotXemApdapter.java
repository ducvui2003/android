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

import java.util.List;

public class LuotXemApdapter extends RecyclerView.Adapter<LuotXemApdapter.LuotXemViewHolder> {
    private Context context;
    private List<ClassifyStory> list;
    private String email;

    public LuotXemApdapter(Context context, List<ClassifyStory> list, String email) {
        this.context = context;
        this.list = list;
        this.email = email;
    }

    @NonNull
    @Override
    public LuotXemApdapter.LuotXemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_rank, parent, false);
        return new LuotXemApdapter.LuotXemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LuotXemApdapter.LuotXemViewHolder holder, int position) {
        ClassifyStory truyen = list.get(position);
        if (truyen == null) {
            return;
        }


        Glide.with(this.context).load(truyen.getLinkImage()).into(holder.img_theloai);
        holder.tv_tentruyen.setText(truyen.getNameStory());
        holder.tv_pl.setText("Tổng lượt xem: " + truyen.getView());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailComicActivity.class);
            intent.putExtra(BundleConstraint.ID_COMMIC, truyen.getId());
            intent.putExtra("email", email);
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

    public class LuotXemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tentruyen, tv_pl;
        private ImageView img_theloai;

        public LuotXemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tentruyen = itemView.findViewById(R.id.item_rcv_name_commic);
            tv_pl = itemView.findViewById(R.id.item_rcv_date_commic);
            img_theloai = itemView.findViewById(R.id.item_rcv_thumnail);
        }
    }
}
