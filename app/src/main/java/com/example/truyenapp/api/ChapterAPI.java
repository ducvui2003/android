package com.example.truyenapp.api;

import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.ChapterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChapterAPI {

    @GET("v1/chapters/book/{id}")
    Call<List<ChapterResponse>> getChaptersByBook(@Path("id") int bookId);

}
