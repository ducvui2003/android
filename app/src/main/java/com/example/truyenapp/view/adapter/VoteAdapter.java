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
import com.example.truyenapp.model.ClassifyStory;

import java.util.List;

public class VoteAdapter extends RecyclerView.Adapter<VoteAdapter.VoteViewHolder> {
    private Context context;
    private List<ClassifyStory> listCommic;
    private String email;

    public VoteAdapter(Context context, List<ClassifyStory> listCommic) {
        this.context = context;
        this.listCommic = listCommic;
    }

    public VoteAdapter(Context context, List<ClassifyStory> listCommic, String email) {
        this.context = context;
        this.listCommic = listCommic;
        this.email = email;
    }

    public void setData(List<ClassifyStory> list) {
        this.listCommic = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_theloainew, parent, false);
        return new VoteAdapter.VoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoteViewHolder holder, int position) {
        ClassifyStory truyen = listCommic.get(position);
        if (truyen == null) {
            return;
        }

        Glide.with(this.context).load(truyen.getLinkImage()).into(holder.img_theloai);
        holder.tv_tentruyen.setText(truyen.getNameStory());
        holder.tv_pl.setText("Đánh giá: " + truyen.getEvaluate());
        holder.ll_rcv_theloai.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), CTTruyen.class);
            intent.putExtra("id_truyen", truyen.getId());
            intent.putExtra("email", email);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (listCommic != null) {
            return listCommic.size();
        }
        return 0;
    }

    public class VoteViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_tentruyen, tv_pl;
        private View ll_rcv_theloai;
        private ImageView img_theloai;

        public VoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tentruyen = itemView.findViewById(R.id.item_rcv_name_commic);
            tv_pl = itemView.findViewById(R.id.item_rcv_date_commic);
            ll_rcv_theloai = itemView.findViewById(R.id.ll_rcv_theloai);
            img_theloai = itemView.findViewById(R.id.item_rcv_thumnail);
        }
    }
}
