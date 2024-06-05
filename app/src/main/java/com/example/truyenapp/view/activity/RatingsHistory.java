package com.example.truyenapp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.BookAPI;
import com.example.truyenapp.api.RatingAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.UserAPI;
import com.example.truyenapp.model.Evaluate;
import com.example.truyenapp.model.JWTToken;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.RatingResponse;
import com.example.truyenapp.response.UserResponse;
import com.example.truyenapp.utils.SharedPreferencesHelper;
import com.example.truyenapp.utils.SystemConstant;
import com.example.truyenapp.view.adapter.RatingsHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingsHistory extends AppCompatActivity {

    private RecyclerView rcv_danhgia;
    private RatingsHistoryAdapter rcv_adapter;
    private List<Evaluate> listEvaluate = new ArrayList<>();
    private List<BookResponse> listBook = new ArrayList<>();
    private TextView tv_danhgia_tong;
    private UserResponse userResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdanhgia);

        rcv_danhgia = findViewById(R.id.rcv_danhgia_tong);
        rcv_danhgia.setLayoutManager(new LinearLayoutManager(this));
        tv_danhgia_tong = findViewById(R.id.tv_danhgia_tong);

        getAllBooksAndRatings();
    }

    private void getAllBooksAndRatings() {
        RetrofitClient.getInstance(this).create(UserAPI.class)
                .getUserInfo(SharedPreferencesHelper.getObject(this, SystemConstant.JWT_TOKEN, JWTToken.class).getToken())
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        userResponse = response.body();
                        if (userResponse != null) {
                            tv_danhgia_tong.setText("Tổng đánh giá: " + userResponse.getNumberOfRatings());
                            fetchBooksAndRatings();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void fetchBooksAndRatings() {
        RetrofitClient.getInstance(this).create(BookAPI.class).getAllBooks().enqueue(new Callback<List<BookResponse>>() {
            @Override
            public void onResponse(Call<List<BookResponse>> call, Response<List<BookResponse>> response) {
                listBook = response.body();
                if (listBook != null) {
                    fetchRatings();
                }
            }

            @Override
            public void onFailure(Call<List<BookResponse>> call, Throwable t) {
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    private void fetchRatings() {
        RetrofitClient.getInstance(this).create(RatingAPI.class).getRatingByUser(userResponse.getId())
                .enqueue(new Callback<APIResponse<List<RatingResponse>>>() {
                    @Override
                    public void onResponse(Call<APIResponse<List<RatingResponse>>> call, Response<APIResponse<List<RatingResponse>>> response) {
                        if (response.isSuccessful()) {
                            APIResponse<List<RatingResponse>> apiResponse = response.body();
                            if (apiResponse != null && apiResponse.getResult() != null) {
                                for (RatingResponse ratingResponse : apiResponse.getResult()) {
                                    listEvaluate.add(mapRatingResponseToEvaluate(ratingResponse));
                                }
                                rcv_adapter = new RatingsHistoryAdapter(getApplicationContext(), listEvaluate, listBook);
                                rcv_danhgia.setAdapter(rcv_adapter);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Trống", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse<List<RatingResponse>>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Lỗi, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Evaluate mapRatingResponseToEvaluate(RatingResponse ratingResponse) {
        Evaluate evaluate = new Evaluate();
        evaluate.setId(ratingResponse.getId());
        evaluate.setIdChapter(ratingResponse.getChapterId());
        evaluate.setIdAccount(ratingResponse.getUserId());
        evaluate.setStar(ratingResponse.getStar());
        evaluate.setEvaluateDate(ratingResponse.getCreatedAt().toString());
        return evaluate;
    }
}