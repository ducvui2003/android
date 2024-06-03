package com.example.truyenapp.api;

import com.example.truyenapp.model.AccountVerifyRequest;
import com.example.truyenapp.model.Notification;
import com.example.truyenapp.model.RewardPoint;
import com.example.truyenapp.request.ChangePasswordRequest;
import com.example.truyenapp.request.ForgotPasswordRequest;
import com.example.truyenapp.request.UserRequest;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.AttendanceResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.response.RewardPointResponse;
import com.example.truyenapp.response.UserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {
    @POST("v1/users/change-password")
    Call<APIResponse<Void>> changePass(@Body ChangePasswordRequest passwordRequest);

    @POST("v1/users/forgot-password")
    Call<APIResponse<Void>> forgotPass(@Body ForgotPasswordRequest passwordRequest);

    @GET("v1/users/info")
    Call<UserResponse> getUserInfo(@Query("token") String token);

    @POST("v1/users/update-info")
    Call<Void> updateInfo(@Body UserRequest request);

    @POST("v1/attendance")
    Call<APIResponse<AttendanceResponse>> attendance();
    @GET("v1/attendance/reward-point")
    Call<APIResponse<RewardPointResponse>> getRewardPoint();

    @GET("v1/attendance/history")
    Call<APIResponse<DataListResponse<RewardPoint>>> getAttendanceHistory();

    @POST("v1/users/register")
    Call<APIResponse<Void>> register(@Body UserRequest userRequest);

    @POST("v1/users/verify-account")
    Call<APIResponse<Void>> verifyAccount(@Body AccountVerifyRequest accountVerifyRequest);

    @GET("v1/notifications/all")
    Call<APIResponse<ArrayList<Notification>>> getNotifications();

    @GET("v1/notifications/count")
    Call<APIResponse<Integer>> getNumberNotifications();
}
