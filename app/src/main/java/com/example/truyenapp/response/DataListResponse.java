package com.example.truyenapp.response;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
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

}
