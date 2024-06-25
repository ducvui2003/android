package com.example.truyenapp.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.CommentAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.paging.PagingScrollListener;
import com.example.truyenapp.request.CommentResponseOverall;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.CommentResponse;
import com.example.truyenapp.view.adapter.CommentAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountCommentActivity extends AppCompatActivity {
    private RecyclerView rcv;
    private CommentAdapter adapter;
    private TextView quantityComment;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int totalPage;
    private int currentPage = 1;
    private final int PAGE_SIZE = 5;
    private CommentAPI commentAPI;
    private LinearLayoutManager linearLayoutManager;
    private List<CommentResponse> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_comment);
        init();
        getData();
    }


    private void init() {
        quantityComment = findViewById(R.id.tv_account_comment_quantity);
        rcv = findViewById(R.id.rcv_account_comment);
        comments = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv.setLayoutManager(linearLayoutManager);
        adapter = new CommentAdapter(this, comments);
        rcv.setAdapter(adapter);
        commentAPI = RetrofitClient.getInstance(this).create(CommentAPI.class);
        rcv.addOnScrollListener(new PagingScrollListener(this.linearLayoutManager) {
            @Override
            public void loadMoreItem() {
                isLoading = true;
                currentPage += 1;
                getData();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    private void setFirstData(List<CommentResponse> list) {
        this.comments.addAll(list);
        adapter.setData(this.comments);
        if (currentPage < totalPage) {
            adapter.addFooterLoading();
        } else {
            isLastPage = true;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadNextPage(List<CommentResponse> list) {
        adapter.removeFooterLoading();
        this.comments.addAll(list);
        adapter.notifyDataSetChanged();
        this.isLoading = false;
        if (currentPage < totalPage) {
            adapter.addFooterLoading();
        } else {
            isLastPage = true;
        }
    }

    public void getData() {
        commentAPI.getCommentOverall(currentPage, PAGE_SIZE).enqueue(new Callback<APIResponse<CommentResponseOverall>>() {
            @Override
            public void onResponse(Call<APIResponse<CommentResponseOverall>> call, Response<APIResponse<CommentResponseOverall>> response) {
                APIResponse<CommentResponseOverall> data = response.body();

                if (data == null || data.getResult() == null || data.getResult().getData() == null) {
                    return;
                }

                if (data.getCode() == 400)
                    return;
                int totalComment = response.body().getResult().getTotalComment();
                setQuantityComment(totalComment);
                List<CommentResponse> listTemp = new ArrayList<>();
                currentPage = data.getResult().getData().getCurrentPage();
                totalPage = data.getResult().getData().getTotalPages();
                listTemp.addAll(data.getResult().getData().getData());
                if (currentPage == 1) {
                    setFirstData(listTemp);
                } else {
                    loadNextPage(listTemp);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<CommentResponseOverall>> call, Throwable throwable) {

            }
        });
    }

    private void setQuantityComment(int quantity) {
        quantityComment.setText("Tổng bình luận: " + quantity);
    }
}
