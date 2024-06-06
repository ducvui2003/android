package com.example.truyenapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RedeemRewardAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.view.adapter.InventoryAdapter;
import com.example.truyenapp.model.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryFragment extends Fragment {

    private View view;
    private RecyclerView rcv;
    private InventoryAdapter adapter;
    private List<Item> listItem;
    private RedeemRewardAPI redeemRewardAPI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rcv_linear, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        callAPI();
    }

    public void init() {
        rcv = view.findViewById(R.id.rcv_comic_card);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        listItem = new ArrayList<>();
        adapter = new InventoryAdapter(getActivity(), listItem);
        rcv.setAdapter(adapter);
        redeemRewardAPI = RetrofitClient.getInstance(this.getContext()).create(RedeemRewardAPI.class);
    }

    public void callAPI() {
        redeemRewardAPI.getItemsUser().enqueue(new Callback<APIResponse<DataListResponse<Item>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<Item>>> call, Response<APIResponse<DataListResponse<Item>>> response) {
                APIResponse<DataListResponse<Item>> apiResponse = response.body();
                if (apiResponse.getCode() == 400) {
                    return;
                }
                List<Item> items = apiResponse.getResult().getData();
                listItem.addAll(items);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<APIResponse<DataListResponse<Item>>> call, Throwable throwable) {
                Log.d("InventoryFragment", "onFailure: " + throwable.getMessage());
            }
        });
    }

}