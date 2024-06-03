package com.example.truyenapp.api;

import com.example.truyenapp.enums.ExchangeStatus;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.request.ExchangeRequest;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.CommentResponse;
import com.example.truyenapp.response.DataListResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RedeemRewardAPI {
    @GET("v1/items")
    Call<APIResponse<DataListResponse<Item>>> getItem();

    @POST("v1/items")
    Call<APIResponse<ExchangeStatus>> exchange(@Body ExchangeRequest exchangeRequest);
}
