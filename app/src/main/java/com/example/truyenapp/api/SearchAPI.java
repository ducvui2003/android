package com.example.truyenapp.api;

import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.DataListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPI {
    @GET("v1/book/rank")
    Call<APIResponse<DataListResponse<BookResponse>>> rank(@Query("type") String type);

    @GET("v1/book/string")
    Call<APIResponse<String>> string();

}
