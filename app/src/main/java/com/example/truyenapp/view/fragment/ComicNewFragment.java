package com.example.truyenapp.view.fragment;

import android.annotation.SuppressLint;
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
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.paging.PagingScrollListener;
import com.example.truyenapp.response.APIResponse;
import com.example.truyenapp.response.BookResponse;
import com.example.truyenapp.response.DataListResponse;
import com.example.truyenapp.view.adapter.ComicNewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComicNewFragment extends Fragment {

    private View view;
    private RecyclerView rcv;
    private ComicNewAdapter adapter;
    private List<ClassifyStory> listCommic = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private Integer categoryId;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int totalPage;
    private int currentPage = 1;
    private final int PAGE_SIZE = 5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rcv_linear, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getData();
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


    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        currentPage = 1;
        this.listCommic.clear();
        this.adapter.notifyDataSetChanged();
        getData();
    }

    private void init() {
        this.rcv = view.findViewById(R.id.rcv_comic_card);
        this.adapter = new ComicNewAdapter(getActivity(), listCommic);
        this.linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        this.rcv.setLayoutManager(linearLayoutManager);
        this.rcv.setAdapter(adapter);
    }

    private void setFirstData(List<ClassifyStory> list) {
        this.listCommic.addAll(list);
        adapter.setData(this.listCommic);
        if (currentPage < totalPage) {
            adapter.addFooterLoading();
        } else {
            isLastPage = true;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadNextPage(List<ClassifyStory> list) {
        adapter.removeFooterLoading();
        this.listCommic.addAll(list);
        adapter.notifyDataSetChanged();
        this.isLoading = false;
        if (currentPage < totalPage) {
            adapter.addFooterLoading();
        } else {
            isLastPage = true;
        }
    }

    public void getData() {
        SearchAPI response = RetrofitClient.getInstance(getContext()).create(SearchAPI.class);
        Call<APIResponse<DataListResponse<BookResponse>>> call;
        if (categoryId != null) {
            call = response.getNewComic(categoryId, currentPage, PAGE_SIZE);
        } else {
            call = response.getNewComic(currentPage, PAGE_SIZE);
        }
        call.enqueue(new Callback<APIResponse<DataListResponse<BookResponse>>>() {
            @Override
            public void onResponse(Call<APIResponse<DataListResponse<BookResponse>>> call, Response<APIResponse<DataListResponse<BookResponse>>> response) {
                APIResponse<DataListResponse<BookResponse>> data = response.body();

                if (data == null || data.getResult() == null || data.getResult().getData() == null) {
                    return;
                }

                if (data.getCode() == 400)
                    return;
                List<ClassifyStory> listTemp = new ArrayList<>();
                currentPage = data.getResult().getCurrentPage();
                totalPage = data.getResult().getTotalPages();
                for (BookResponse bookResponse : data.getResult().getData()) {
                    String nameCategory = bookResponse.getCategoryNames().get(0);
                    ClassifyStory classifyStory = new ClassifyStory(bookResponse.getId(), bookResponse.getView(), bookResponse.getRating().floatValue(), bookResponse.getName(), bookResponse.getPublishDate().toString(), nameCategory, bookResponse.getThumbnail());
                    listTemp.add(classifyStory);
                }
                if (currentPage == 1) {
                    setFirstData(listTemp);
                } else {
                    loadNextPage(listTemp);
                }
            }

            @Override
            public void onFailure(Call<APIResponse<DataListResponse<BookResponse>>> call, Throwable throwable) {
                Toast.makeText(getActivity(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}