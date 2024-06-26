package com.example.truyenapp.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.StatisticalAPI;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.StatisticalBookResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticalInformation extends AppCompatActivity {
    ImageView img;
    TextView tv_qltk_tentruyen, tv_qltk_id, tv_qltk_chapter, tv_qltk_danhgia, tv_qltk_tongdanhgia, tv_qltk_tongluotxem, tv_qltk_tongbinhluan;
    int id;
    private String bookName;
    private StatisticalAPI statisticalAPI;
    private StatisticalBookResponse statisticalBookResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistical_information);
        connectAPI();
        init();
        Intent intent = getIntent();
        id = intent.getIntExtra("id_thongke", 1);
        bookName = intent.getStringExtra("name");
        getStatisticalBook();
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        Glide.with(this).load(statisticalBookResponse.getThumbnail()).into(img);
        tv_qltk_tentruyen.setText(bookName);
        tv_qltk_id.setText("" + statisticalBookResponse.getIdBook());
        tv_qltk_danhgia.setText("" + statisticalBookResponse.getAverageBookRating());
        tv_qltk_tongdanhgia.setText("" + statisticalBookResponse.getCountBookRating());
        tv_qltk_chapter.setText("" + statisticalBookResponse.getCountBookChapters());
        tv_qltk_tongluotxem.setText("" + statisticalBookResponse.getCountBookViews());
        tv_qltk_tongbinhluan.setText("" + statisticalBookResponse.getCountBookComment());
    }

    private void init() {
        img = findViewById(R.id.img_qltk);
        tv_qltk_tentruyen = findViewById(R.id.tv_qltk_tentruyen);
        tv_qltk_id = findViewById(R.id.tv_qltk_id);
        tv_qltk_chapter = findViewById(R.id.tv_qltk_chapter);
        tv_qltk_danhgia = findViewById(R.id.tv_qltk_danhgia);
        tv_qltk_tongdanhgia = findViewById(R.id.tv_qltk_tongdanhgia);
        tv_qltk_tongluotxem = findViewById(R.id.tv_qltk_tongluotxem);
        tv_qltk_tongbinhluan = findViewById(R.id.tv_qltk_tongbinhluan);
    }

    private void connectAPI() {
        statisticalAPI = RetrofitClient.getInstance(this).create(StatisticalAPI.class);
    }

    private void getStatisticalBook() {
        statisticalAPI.getStatisticalBook(id).enqueue(new Callback<APIResponse<StatisticalBookResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<StatisticalBookResponse>> call, Response<APIResponse<StatisticalBookResponse>> response) {
                APIResponse<StatisticalBookResponse> apiResponse = response.body();
                if (apiResponse != null && apiResponse.getResult() != null) {
                    statisticalBookResponse = apiResponse.getResult();
                    setData();
                }

            }
            @Override
            public void onFailure(Call<APIResponse<StatisticalBookResponse>> call, Throwable throwable) {

            }
        });
    }
}
