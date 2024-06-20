package com.example.truyenapp.api;

import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.response.StatisticalBookResponse;
import com.example.truyenapp.response.StatisticalResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StatisticalAPI {
    @GET("v1/admin/statistical")
    Call<APIResponse<StatisticalResponse>> getStatistical();

    @GET("v1/admin/statistical/book/{id}")
    Call<APIResponse<StatisticalBookResponse>> getStatisticalBook(@Path("id") int id);
}
