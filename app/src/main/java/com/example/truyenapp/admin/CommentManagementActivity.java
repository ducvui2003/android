package com.example.truyenapp.admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.CommentAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.mapper.CommentMapper;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.CommentResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.view.adapter.admin.CommentManagerAdapter;
import com.example.truyenapp.model.Comment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentManagementActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private CommentManagerAdapter adapter;
    private final int PAGE_SIZE = 10;
    private int currentPage = 1;
    private int totalPage;
    List<Comment> comments;
    CommentAPI commentAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_management);

        init();
        callAPI();
    }

    private void init() {
        rcv = findViewById(R.id.rcv_comment_management);
        this.commentAPI = RetrofitClient.getInstance(this).create(CommentAPI.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        this.comments = new ArrayList<>();
        adapter = new CommentManagerAdapter(this, comments);
        rcv.setAdapter(adapter);

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
                for (CommentResponse commentResponse : data.getResult().getData()) {
                    Comment comment = CommentMapper.INSTANCE.commentResponseToComment(commentResponse);
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
