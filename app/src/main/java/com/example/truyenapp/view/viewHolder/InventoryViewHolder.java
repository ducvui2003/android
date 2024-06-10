package com.example.truyenapp.view.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.RedeemRewardAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.utils.Format;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private ImageView thumbnail;
    private TextView name, score;
    private Button buttonUse;
    private TextView date;
    private RedeemRewardAPI redeemRewardAPI;
    private Item item;

    public InventoryViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        thumbnail = itemView.findViewById(R.id.img_inventory_item);
        score = itemView.findViewById(R.id.tv_inventory_item_score);
        name = itemView.findViewById(R.id.tv_inventory_item_name);
        buttonUse = itemView.findViewById(R.id.btn_item_inventory_use);
        redeemRewardAPI = RetrofitClient.getInstance(view.getContext()).create(RedeemRewardAPI.class);
        date = itemView.findViewById(R.id.tv_inventory_item_date);
    }

    public void setData(Item item) {
        this.item = item;
        Glide.with(view).load(item.getImage()).into(this.thumbnail);
        this.name.setText(item.getName());
        this.score.setText("Điểm: " + item.getPoint());
        if (item.getDate() != null)
            this.date.setText("Ngày đổi: " + Format.formatDate(item.getDate().toString(), "yyyy-MM-dd", "dd-MM-yyyy"));
        buttonUse.setOnClickListener(v -> {
            callAPI();
        });
    }


    private void callAPI() {
        redeemRewardAPI.useItem(item.getId()).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                APIResponse apiResponse = response.body();
                if (apiResponse.getCode() == 200) {
                    Toast.makeText(view.getContext(), "Sử dụng avatar thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "Đã có lỗi xảy ra. Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable throwable) {

            }
        });
    }
}