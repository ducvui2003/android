package com.example.truyenapp.api;

import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.CategoryResponse;
import com.example.truyenapp.response.DataListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchAPI {
    @GET("v1/books/rank")
    Call<APIResponse<DataListResponse<BookResponse>>> rank(@Query("type") String type);

    @GET("v1/books/rank")
    Call<APIResponse<DataListResponse<BookResponse>>> rank(@Query("type") String type, @Query("categoryId") Integer categoryId);

    @GET("v1/books/string")
    Call<APIResponse<String>> string();

    @GET("v1/books/search")
    Call<APIResponse<DataListResponse<BookResponse>>> search(@Query("keyword") String keyword, @Query("categoryId") Integer category);

    @GET("v1/books/category")
    Call<APIResponse<List<CategoryResponse>>> getCategory();

    @GET("v1/books/search")
    Call<APIResponse<DataListResponse<BookResponse>>> getFullComic(@Query("keyword") String keyword, @Query("sortField_state") String state, @Query("size") Integer pageSize);

    @GET("v1/books/rank")
    Call<APIResponse<DataListResponse<BookResponse>>> getTopComic(@Query("type") String type, @Query("size") Integer pageSize);

    @GET("v1/books/newComic")
    Call<APIResponse<DataListResponse<BookResponse>>> getNewComic(@Query("size") Integer pageSize);
    @GET("v1/books/search")
    Call<APIResponse<DataListResponse<BookResponse>>> search(@Query("categoryId") Integer category);
}
