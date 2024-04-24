package com.example.truyenapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public final class RetrofitClient {
    private final static String BASE_URL = "https://6bc835276f0e7624250e575324ff6933.serveo.net/api/";
    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
