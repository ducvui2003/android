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
import com.example.truyenapp.view.adapter.ViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RankViewFragment extends Fragment {
    private View view;
    private RecyclerView rcv;
    private ViewAdapter adapter;
    private List<ClassifyStory> listCommic = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank_view, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getViewList();
    }

    private void init() {
        this.rcv = view.findViewById(R.id.rcv_theloai_view);
        this.adapter = new ViewAdapter(getActivity(), listCommic);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        this.rcv.setLayoutManager(linearLayoutManager);
        this.rcv.setAdapter(adapter);
    }

    //    call api lấy dữ liệu danh sách truyện theo lượt xem
    public void getViewList() {
        SearchAPI response = RetrofitClient.getInstance(getContext()).create(SearchAPI.class);
        response.rank("view").enqueue(new Callback<APIResponse<DataListResponse<BookResponse>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<BookResponse>>> call, Response<APIResponse<DataListResponse<BookResponse>>> response) {
                APIResponse<DataListResponse<BookResponse>> data = response.body();
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