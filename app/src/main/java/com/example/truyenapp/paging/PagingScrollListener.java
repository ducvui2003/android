package com.example.truyenapp.paging;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PagingScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layoutManager;

    public PagingScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

//        Đếm số lượng item hiển thị trên màn hình
        int visibleItemCount = layoutManager.getChildCount();
//        Đếm tổng số item trong page
        int totalItemCount = layoutManager.getItemCount();
//        Vị trí position của item đầu tiên
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        Log.d("PagingScrollListener", "onScrolled: " + visibleItemCount + " " + totalItemCount + " " + firstVisibleItemPosition);
        if (isLoading() || isLastPage()) return;
        if (firstVisibleItemPosition >= 0 && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
            Log.d("PagingScrollListener", "onScrolled: load more item");
            loadMoreItem();
        }
    }

    public abstract void loadMoreItem();

    public abstract boolean isLoading();

    public abstract boolean isLastPage();
}
