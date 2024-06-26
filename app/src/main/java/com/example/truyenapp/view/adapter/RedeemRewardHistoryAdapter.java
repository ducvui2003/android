package com.example.truyenapp.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.model.RewardPoint;
import com.example.truyenapp.utils.Format;

import java.util.List;

public class RedeemRewardHistoryAdapter extends RecyclerView.Adapter<RedeemRewardHistoryAdapter.Holder> {
    private Context context;
    private List<RewardPoint> list;


    public RedeemRewardHistoryAdapter(Context context, List<RewardPoint> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<RewardPoint> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_redeem_reward_history, parent, false);
        return new Holder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        RewardPoint rewardPoint = list.get(position);
        if (rewardPoint == null) {
            return;
        }

        holder.tvScore.setText("+ " + rewardPoint.getPoint() + " điểm");
        holder.tvDate.setText(Format.formatDate(rewardPoint.getDate().toString(), "yyyy-MM-dd", "dd-MM-yyyy"));
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    protected class Holder extends RecyclerView.ViewHolder {
        private TextView tvScore, tvDate;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tvScore = itemView.findViewById(R.id.tv_redeem_reward_history_score);
            tvDate = itemView.findViewById(R.id.tv_redeem_reward_history_date);
        }
    }
}
