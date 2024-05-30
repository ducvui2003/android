package com.example.truyenapp.api;

import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.CommentResponse;
import com.example.truyenapp.response.DataListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CommentAPI {
    @GET("v1/admin/comment")
    public Call<APIResponse<DataListResponse<CommentResponse>>> getComments(@Query("page") int page, @Query("size") int size);
}
