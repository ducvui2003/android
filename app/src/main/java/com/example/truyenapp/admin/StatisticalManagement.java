package com.example.truyenapp.admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.BookAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.StatisticalAPI;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.StatisticalResponse;
import com.example.truyenapp.view.adapter.admin.StatisticalManagementAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticalManagement extends AppCompatActivity {
    private RecyclerView rcv;
    TextView tv_qltk_sltruyen, tv_qltk_slchapter, tv_qltk_slview, tv_qltk_slvote, tv_qltk_slcomment;
    List<BookResponse> bookResponseList;
    private StatisticalResponse statisticalResponse;
    private BookAPI bookAPI;
    private StatisticalAPI statisticalAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistical_management);
        connectAPI();
        init();
        getStatistical();
        getAllBooks();
    }

    private void recyclerViewQLThongKe() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        StatisticalManagementAdapter statisticalManagementAdapter = new StatisticalManagementAdapter(this, bookResponseList);
        rcv.setAdapter(statisticalManagementAdapter);
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        tv_qltk_slchapter.setText("Tổng số chapter: " + statisticalResponse.getCountChapters());
        tv_qltk_slcomment.setText("Tổng bình luận: " + statisticalResponse.getCountComment());
        tv_qltk_slview.setText("Tổng lượt xem: " + statisticalResponse.getCountViews());
        tv_qltk_slvote.setText("Tổng đánh giá: " + statisticalResponse.getCountRating());
        tv_qltk_sltruyen.setText("Tổng số truyện: " + statisticalResponse.getCountBooks());
    }

    private void init() {
        rcv = findViewById(R.id.rcv_quanlythongke);
        tv_qltk_sltruyen = findViewById(R.id.tv_qltk_sltruyen);
        tv_qltk_slchapter = findViewById(R.id.tv_qltk_slchapter);
        tv_qltk_slview = findViewById(R.id.tv_qltk_slview);
        tv_qltk_slvote = findViewById(R.id.tv_qltk_slvote);
        tv_qltk_slcomment = findViewById(R.id.tv_qltk_slcomment);
    }

    private void connectAPI() {
        statisticalAPI = RetrofitClient.getInstance(this).create(StatisticalAPI.class);
        bookAPI = RetrofitClient.getInstance(this).create(BookAPI.class);
    }

    private void getStatistical() {
        statisticalAPI.getStatistical().enqueue(new Callback<APIResponse<StatisticalResponse>>() {
            @Override
            public void onResponse(Call<APIResponse<StatisticalResponse>> call, Response<APIResponse<StatisticalResponse>> response) {
                APIResponse<StatisticalResponse> apiResponse = response.body();
                if (apiResponse != null && apiResponse.getResult() != null) {
                    statisticalResponse = apiResponse.getResult();
                    setData();
                } else {
                    Log.e("Statistical response is null", "Statistical response is null");
                }
            }

            @Override
            public void onFailure(Call<APIResponse<StatisticalResponse>> call, Throwable throwable) {
                Log.e("Something wrong when get statistical", throwable.getMessage());
            }
        });
    }

    private void getAllBooks() {
        bookAPI.getAllBooks().enqueue(new Callback<List<BookResponse>>() {
            @Override
            public void onResponse(Call<List<BookResponse>> call, Response<List<BookResponse>> response) {
                if (response.isSuccessful()) {
                    bookResponseList = response.body();
                    recyclerViewQLThongKe();
                } else {
                    Toast.makeText(StatisticalManagement.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<BookResponse>> call, Throwable t) {
                Toast.makeText(StatisticalManagement.this, "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
                Log.e("Error", t.getMessage());
            }
        });
    }
}
