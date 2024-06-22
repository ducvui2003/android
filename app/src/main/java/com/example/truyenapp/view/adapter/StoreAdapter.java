package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.RedeemRewardAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.enums.ExchangeStatus;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.request.ExchangeRequest;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.utils.DialogEvent;
import com.example.truyenapp.utils.DialogHelper;
import com.example.truyenapp.view.fragment.StoreFragment;
import com.example.truyenapp.view.viewHolder.StoreItemViewHolder;

import java.util.List;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreAdapter extends RecyclerView.Adapter<StoreItemViewHolder> {
    private Context context;
    private List<Item> list;
    @Getter
    private StoreFragment storeFragment;

    public StoreAdapter(Context context, List<Item> list, StoreFragment storeFragment) {
        this.context = context;
        this.list = list;
        this.storeFragment = storeFragment;
    }

    public void setData(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public StoreItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_store_item, parent, false);
        return new StoreItemViewHolder(view, storeFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreItemViewHolder holder, int position) {
        Item item = list.get(position);
        if (item == null) {
            return;
        }
        holder.setData(item);

    }


    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
}
