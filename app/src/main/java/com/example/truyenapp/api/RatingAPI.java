package com.example.truyenapp.api;

import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.RatingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RatingAPI {
    @GET("v1/ratings/user/{id}")
    Call<APIResponse<List<RatingResponse>>> getRatingByUser(@Path("id") int userId);
}
