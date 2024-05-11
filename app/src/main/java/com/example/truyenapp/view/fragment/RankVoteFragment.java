package com.example.truyenapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyenapp.R;
import com.example.truyenapp.api.RetrofitClient;
import com.example.truyenapp.api.SearchAPI;
import com.example.truyenapp.model.APIResponse;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.view.adapter.VoteAdapter;

import java.util.ArrayList;
import java.util.List;

import lombok.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankVoteFragment extends Fragment {
    private View view;
    private RecyclerView rcv;
    private VoteAdapter adapter;
    private List<ClassifyStory> listCommic = new ArrayList<>();
    private Integer categoryId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank_vote, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.init();
        getVoteList();
    }

    private void init() {
        this.rcv = view.findViewById(R.id.rcv_theloai_vote);
        this.adapter = new VoteAdapter(getActivity(), listCommic);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        this.rcv.setLayoutManager(linearLayoutManager);
        this.rcv.setAdapter(adapter);
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        getVoteList();
    }

    public void getVoteList() {
        SearchAPI response = RetrofitClient.getInstance(getContext()).create(SearchAPI.class);
        Call<APIResponse<DataListResponse<BookResponse>>> call;
        if (categoryId != null) {
            call = response.rank("rating", categoryId);
        } else {
            call = response.rank("rating");
        }
        call.enqueue(new Callback<APIResponse<DataListResponse<BookResponse>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<BookResponse>>> call, Response<APIResponse<DataListResponse<BookResponse>>> response) {
                listCommic.clear();
                APIResponse<DataListResponse<BookResponse>> data = response.body();
                if (data == null || data.getResult() == null || data.getResult().getData() == null) {
                    return;
                }
                for (BookResponse bookResponse : data.getResult().getData()) {
                    String nameCategory = bookResponse.getCategoryNames().get(0);
                    ClassifyStory classifyStory = new ClassifyStory(bookResponse.getId(), bookResponse.getView(), bookResponse.getRating().floatValue(), bookResponse.getName(), bookResponse.getPublishDate().toString(), nameCategory, bookResponse.getThumbnail());
                    listCommic.add(classifyStory);
                }
                adapter.setData(listCommic);
            }

            @Override
            public void onFailure(Call<APIResponse<DataListResponse<BookResponse>>> call, Throwable throwable) {
                Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}