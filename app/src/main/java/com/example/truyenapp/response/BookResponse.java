package com.example.truyenapp.response;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    String status;

}
