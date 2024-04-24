package com.example.truyenapp.api;

import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.request.ChangePasswordRequest;
import com.example.truyenapp.request.ForgotPasswordRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("v1/users/change-password")
    Call<APIResponse<Void>> changePass(@Body ChangePasswordRequest passwordRequest);

    @POST("v1/users/forgot-password")
    Call<APIResponse<Void>> forgotPass(@Body ForgotPasswordRequest passwordRequest);
}
