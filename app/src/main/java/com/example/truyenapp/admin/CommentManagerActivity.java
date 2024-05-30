package com.example.truyenapp.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.CommentAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.mapper.CommentMapper;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.CommentResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.view.adapter.admin.CommentManagerAdapter;
import com.example.truyenapp.database.Database;
import com.example.truyenapp.model.Comment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentManagerActivity extends AppCompatActivity {
    Database db;
    private RecyclerView rcv;
    private CommentManagerAdapter adapter;
    private final int PAGE_SIZE = 10;
    private int currentPage = 1;
    private int totalPage;
    List<Comment> comments = new ArrayList<>();
    CommentAPI commentAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_management);

        init();
        db = new Database(this);
        handleRecyclerView();
        callAPI();
    }

    private void handleRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        ArrayList<Comment> list = db.getListBinhLuan();
        adapter = new CommentManagerAdapter(this, list);
        rcv.setAdapter(adapter);
    }

    private void init() {
        rcv = findViewById(R.id.rcv_quanlybinhluan);
        this.commentAPI = RetrofitClient.getInstance(this).create(CommentAPI.class);
    }

    private void callAPI() {
        commentAPI.getComments(currentPage, PAGE_SIZE).enqueue(new Callback<APIResponse<DataListResponse<CommentResponse>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<CommentResponse>>> call, Response<APIResponse<DataListResponse<CommentResponse>>> response) {
                APIResponse<DataListResponse<CommentResponse>> data = response.body();

                if (data == null || data.getResult() == null || data.getResult().getData() == null) {
                    return;
                }

//                Status code ko tim thay
                if (data.getCode() == 400) return;
                List<Comment> listTemp = new ArrayList<>();
                currentPage = data.getResult().getCurrentPage();
                totalPage = data.getResult().getTotalPages();
                for (CommentResponse bookResponse : data.getResult().getData()) {
                    Comment comment = CommentMapper.INSTANCE.commentResponseToComment(bookResponse);
                    listTemp.add(comment);
                }
                comments.addAll(listTemp);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<APIResponse<DataListResponse<CommentResponse>>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
