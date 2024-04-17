package com.example.truyenapp.api;

import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.request.AuthenticationRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface AuthAPI {
    @POST("v1/auth/login")
    Call<JWTToken> login(@Body AuthenticationRequest request);

}
