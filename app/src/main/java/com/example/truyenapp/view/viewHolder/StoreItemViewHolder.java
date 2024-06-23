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
import com.example.truyenapp.constraints.BundleConstraint;
import com.example.truyenapp.enums.ExchangeStatus;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.request.ExchangeRequest;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.ExchangeResponse;
import com.example.truyenapp.utils.DialogEvent;
import com.example.truyenapp.utils.DialogHelper;
import com.example.truyenapp.view.activity.StoreActivity;
import com.example.truyenapp.view.fragment.StoreFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreItemViewHolder extends RecyclerView.ViewHolder {
    private StoreActivity storeActivity;
    private StoreFragment storeFragment;
    private View view;
    private ImageView img_vatpham;
    private TextView tv_tenvatpham, tv_diem;
    private Button bt_doivatpham;
    private ExchangeStatus exchangeStatus;
    private RedeemRewardAPI redeemRewardAPI;
    private DialogHelper dialogHelper;

    public StoreItemViewHolder(@NonNull View itemView, StoreFragment storeFragment) {
        super(itemView);
        view = itemView;
        img_vatpham = itemView.findViewById(R.id.img_vatpham);
        tv_diem = itemView.findViewById(R.id.tv_store_item_score);
        tv_tenvatpham = itemView.findViewById(R.id.tv_store_item_name);
        bt_doivatpham = itemView.findViewById(R.id.btn_store_item);
        redeemRewardAPI = RetrofitClient.getInstance(itemView.getContext()).create(RedeemRewardAPI.class);
        storeActivity = (StoreActivity) itemView.getContext();
        this.storeFragment = storeFragment;
    }

    public void setData(Item item) {
        Glide.with(this.view.getContext()).load(item.getImage()).into(img_vatpham);
        tv_tenvatpham.setText(item.getName());
        tv_diem.setText("Điểm: " + item.getPoint());
        bt_doivatpham.setOnClickListener(view -> {
            ExchangeRequest request = new ExchangeRequest(item.getId());
            showDialog(request, item.getPoint());
        });
    }

    private void showDialog(ExchangeRequest request, int score) {
        dialogHelper = new DialogHelper(view.getContext(), new DialogEvent() {
            @Override
            public void onPositiveClick() {
                callAPI(request, score);
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

    public void callAPI(ExchangeRequest exchangeRequest, int score) {
        redeemRewardAPI.exchange(exchangeRequest).enqueue(new Callback<APIResponse<ExchangeResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<ExchangeResponse>> call, Response<APIResponse<ExchangeResponse>> response) {
                APIResponse<ExchangeResponse> data = response.body();
                if (data.getResult() == null || data.getCode() == 400) return;
                exchangeStatus = data.getResult().getStatus();
                if (exchangeStatus == ExchangeStatus.EXCHANGE_SUCCESS) {
                    storeActivity.setPoint(data.getResult().getTotalScore());
                    storeFragment.getInventoryViewModel().setExchangeStatus(exchangeRequest.getItemId());
                }
                notifyExchange();
            }

            @Override
            public void onFailure(Call<APIResponse<ExchangeResponse>> call, Throwable t) {

            }
        });
    }

}
