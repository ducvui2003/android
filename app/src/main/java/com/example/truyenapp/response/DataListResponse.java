package com.example.truyenapp.response;

import java.util.List;

public class DataListResponse<T> {
    List<T> data;
    Integer currentPage;
    Integer totalPages;

    public DataListResponse() {
    }

    public DataListResponse(List<T> data, Integer currentPage, Integer totalPages) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
