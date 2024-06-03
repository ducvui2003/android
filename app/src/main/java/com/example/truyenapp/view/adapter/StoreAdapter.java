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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreItemViewHolder> {
    private Context context;
    private List<Item> list;
    private ExchangeStatus exchangeStatus;
    private RedeemRewardAPI redeemRewardAPI;
    private DialogHelper dialogHelper;

    public StoreAdapter(Context context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public StoreItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_store_item, parent, false);
        redeemRewardAPI = RetrofitClient.getInstance(context).create(RedeemRewardAPI.class);
        return new StoreItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreItemViewHolder holder, int position) {
        Item item = list.get(position);
        if (item == null) {
            return;
        }


        Glide.with(this.context).load(item.getImage()).into(holder.img_vatpham);
        holder.tv_tenvatpham.setText(item.getNameItem());
        holder.tv_diem.setText("Điểm: " + item.getPoint());
        holder.bt_doivatpham.setOnClickListener(view -> {
            Log.d("click", "onBindViewHolder: 1");
            ExchangeRequest request = new ExchangeRequest(item.getId());
            showDialog(request);
        });
    }

    private void showDialog(ExchangeRequest request) {
        dialogHelper = new DialogHelper(context, new DialogEvent() {
            @Override
            public void onPositiveClick() {
                callAPI(request);
            }

            @Override
            public void onNegativeClick() {

            }

            @Override
            public void onCancel() {

            }
        });
        dialogHelper.showDialogExchange().show();
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }


    public void callAPI(ExchangeRequest exchangeRequest) {
        redeemRewardAPI.exchange(exchangeRequest).enqueue(new Callback<APIResponse<ExchangeStatus>>() {
            @Override
            public void onResponse(Call<APIResponse<ExchangeStatus>> call, Response<APIResponse<ExchangeStatus>> response) {
                APIResponse<ExchangeStatus> data = response.body();
                if (data.getCode() == 400 || data.getResult() == null) return;
                exchangeStatus = data.getResult();
                notifyExchange();
            }

            @Override
            public void onFailure(Call<APIResponse<ExchangeStatus>> call, Throwable t) {

            }
        });
    }

    private void notifyExchange() {
        String message = "";
        switch (exchangeStatus) {
            case EXIST_IN_REPO:
                message = "Vật phẩm đã có trong kho";
                break;
            case EXCHANGE_SUCCESS:
                message = "Đổi vật phẩm thành công";
                break;
            case POINT_NOT_ENOUGH:
                message = "Không đủ điểm để đổi. Vui lòng tích thêm điểm";
                break;
            case EXCHANGE_FAIL:
            default:
                message = "Xảy ra lỗi. Vui lòng thử lại sau!";
        }
        this.dialogHelper.showDialogExchangeSuccess(message).show();
    }

    class StoreItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_vatpham;
        private TextView tv_tenvatpham, tv_diem;
        private Button bt_doivatpham;

        public StoreItemViewHolder(@NonNull View itemView) {
            super(itemView);
            img_vatpham = itemView.findViewById(R.id.img_vatpham);
            tv_diem = itemView.findViewById(R.id.tv_store_item_score);
            tv_tenvatpham = itemView.findViewById(R.id.tv_store_item_name);
            bt_doivatpham = itemView.findViewById(R.id.btn_store_item);
        }

    }
}
