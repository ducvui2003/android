package com.example.truyenapp.response;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponse {
    Integer id;
    String name;
    String author;
    String description;
    Integer view;
    Double rating;
    String thumbnail;
    Integer quantityChapter;

    List<String> categoryNames;
    Date publishDate;

    public BookResponse(Integer id, String name, String author, String description, Integer view, Double rating, String thumbnail, Integer quantityChapter, List<String> categoryNames, Date publishDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.view = view;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.quantityChapter = quantityChapter;
        this.categoryNames = categoryNames;
        this.publishDate = publishDate;
    }

}
