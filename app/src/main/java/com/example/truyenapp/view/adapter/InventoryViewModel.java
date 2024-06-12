package com.example.truyenapp.view.adapter;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.truyenapp.api.RedeemRewardAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.DataListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryViewModel extends ViewModel {
    MutableLiveData<List<Item>> data;
    RedeemRewardAPI redeemRewardAPI;
    Context context;

    public InventoryViewModel(Context context) {
        this.context = context;
        data = new MutableLiveData<>();
        redeemRewardAPI = RetrofitClient.getInstance(context).create(RedeemRewardAPI.class);
        fetchData();
    }


    public LiveData<List<Item>> getItems() {
        return data;
    }

    private void fetchData() {
        redeemRewardAPI.getItemsUser().enqueue(new Callback<APIResponse<DataListResponse<Item>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<Item>>> call, Response<APIResponse<DataListResponse<Item>>> response) {
                APIResponse<DataListResponse<Item>> apiResponse = response.body();
                if (apiResponse.getCode() == 400 || apiResponse.getResult() == null || apiResponse.getResult().getData() == null)
                    return;
                data.setValue(apiResponse.getResult().getData());
            }

            @Override
            public void onFailure(Call<APIResponse<DataListResponse<Item>>> call, Throwable throwable) {
                Log.d("API", "onFailure: " + throwable.getMessage());
            }
        });
    }
}
