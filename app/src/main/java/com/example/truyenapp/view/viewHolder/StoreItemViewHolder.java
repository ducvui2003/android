package com.example.truyenapp.view.viewHolder;

import android.util.Log;
import android.view.View;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreItemViewHolder extends RecyclerView.ViewHolder {
    private View view;
    private ImageView img_vatpham;
    private TextView tv_tenvatpham, tv_diem;
    private Button bt_doivatpham;
    private ExchangeStatus exchangeStatus;
    private RedeemRewardAPI redeemRewardAPI;
    private DialogHelper dialogHelper;

    public StoreItemViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        img_vatpham = itemView.findViewById(R.id.img_vatpham);
        tv_diem = itemView.findViewById(R.id.tv_store_item_score);
        tv_tenvatpham = itemView.findViewById(R.id.tv_store_item_name);
        bt_doivatpham = itemView.findViewById(R.id.btn_store_item);
        redeemRewardAPI = RetrofitClient.getInstance(itemView.getContext()).create(RedeemRewardAPI.class);
    }

    public void setData(Item item) {
        Glide.with(this.view.getContext()).load(item.getImage()).into(img_vatpham);
        tv_tenvatpham.setText(item.getName());
        tv_diem.setText("Điểm: " + item.getPoint());
        bt_doivatpham.setOnClickListener(view -> {
            Log.d("click", "onBindViewHolder: 1");
            ExchangeRequest request = new ExchangeRequest(item.getId());
            showDialog(request);
        });
    }

    private void showDialog(ExchangeRequest request) {
        dialogHelper = new DialogHelper(view.getContext(), new DialogEvent() {
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

}
