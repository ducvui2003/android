package com.example.truyenapp.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.RewardPoint;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.view.adapter.RedeemRewardHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RedeemRewardHistoryActivity extends AppCompatActivity {

    public RecyclerView rcv;
    public RedeemRewardHistoryAdapter rcv_adapter;
    private List<RewardPoint> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeeem_reward_history);
        init();
        recyclerView();
        callApi();
    }

    public void recyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);

        rcv_adapter = new RedeemRewardHistoryAdapter(this, list);
        rcv.setAdapter(rcv_adapter);
    }

    public void init() {
        rcv = findViewById(R.id.rcv_redeem_reward_history);
        this.list = new ArrayList<>();
    }

    public void callApi() {
        UserAPI response = RetrofitClient.getInstance(this).create(UserAPI.class);
        response.getAttendanceHistory().enqueue(new Callback<APIResponse<DataListResponse<RewardPoint>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<RewardPoint>>> call, Response<APIResponse<DataListResponse<RewardPoint>>> response) {
                APIResponse<DataListResponse<RewardPoint>> resp = response.body();
                if (resp.getCode() != 200) return;
                else {
                    for (RewardPoint item : resp.getResult().getData()) {
                        list.add(new RewardPoint(item.getPoint(), item.getDate()));
                    }
                    rcv_adapter.setData(list);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<DataListResponse<RewardPoint>>> call, Throwable throwable) {

            }
        });
    }
}
