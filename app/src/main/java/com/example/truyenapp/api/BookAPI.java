package com.example.truyenapp.api;

import com.example.truyenapp.response.BookResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BookAPI {
    @GET("v1/books")
    Call<List<BookResponse>> getAllBooks();

}
