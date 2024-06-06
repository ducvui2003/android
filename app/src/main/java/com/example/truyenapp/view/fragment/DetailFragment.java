package com.example.truyenapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.BookAPI;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.model.Comic;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.view.adapter.CommentAdapter;

import retrofit2.Callback;

public class DetailFragment extends Fragment {
    private Comic comic;
    private View view;
    private TextView rating, totalView, totalComment, description;
    private RecyclerView rcvBinhLuan;
    private CommentAdapter rcvAdapter;
    private int idComic;
    private BookAPI bookAPI;

    public DetailFragment(int idComic) {
        this.idComic = idComic;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        bookAPI = RetrofitClient.getInstance(getContext()).create(BookAPI.class);
        getDetail(idComic);
    }

    private void init() {
        rating = view.findViewById(R.id.tv_danhgiact);
        totalView = view.findViewById(R.id.tv_tongluotxem);
        totalComment = view.findViewById(R.id.tv_tongbinhluan);
        description = view.findViewById(R.id.tv_motatruyen);
        rcvBinhLuan = view.findViewById(R.id.rcv_binhluan);
    }

    public void getTotalComment(int idCommic) {
        bookAPI.getAllComment(idCommic).enqueue(new Callback<APIResponse<Integer>>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse<Integer>> call, retrofit2.Response<APIResponse<Integer>> response) {
                if (response.isSuccessful()) {
                    APIResponse<Integer> apiResponse = response.body();
                    if (apiResponse != null) {
                        totalComment.setText(String.valueOf(apiResponse.getResult()));
                    }
                } else {
                    Log.println(Log.ERROR, "API", "Error");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<APIResponse<Integer>> call, Throwable t) {
                Log.d("DetailFragment", "onFailure: " + t.getMessage());
            }
        });
    }

    public void getDetail(int idCommic) {
        bookAPI.getDescriptionBook(idCommic).enqueue(new Callback<APIResponse<BookResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse<BookResponse>> call, retrofit2.Response<APIResponse<BookResponse>> response) {
                if (response.isSuccessful()) {
                    APIResponse<BookResponse> apiResponse = response.body();
                    if (apiResponse != null) {
                        BookResponse bookResponse = apiResponse.getResult();
                        rating.setText(String.valueOf(bookResponse.getRating()));
                        totalView.setText(String.valueOf(bookResponse.getView()));
                        description.setText(bookResponse.getDescription());
                        getTotalComment(idCommic);
                    }
                } else {
                    Log.println(Log.ERROR, "API", "Error");
                }
            }

            @Override
            public void onFailure(retrofit2.Call<APIResponse<BookResponse>> call, Throwable t) {
                Log.d("DetailFragment", "onFailure: " + t.getMessage());
            }
        });
    }


}