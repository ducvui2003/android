package com.example.truyenapp.api;

import com.example.truyenapp.enums.ExchangeStatus;
import com.example.truyenapp.model.Item;
import com.example.truyenapp.request.ExchangeRequest;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.CommentResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.response.ExchangeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RedeemRewardAPI {
    @POST("v1/items/exchange")
    Call<APIResponse<ExchangeResponse>> exchange(@Body ExchangeRequest exchangeRequest);

    @GET("v1/items/user")
    Call<APIResponse<DataListResponse<Item>>> getItemsUser();

    @GET("v1/items/user/use/{id}")
    Call<APIResponse> useItem(@Path("id") Integer itemId);
}
