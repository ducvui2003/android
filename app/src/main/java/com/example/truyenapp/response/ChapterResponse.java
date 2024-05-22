package com.example.truyenapp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class ChapterResponse {
    private Integer id;
    private String name;
    private Date publishDate;
    private Integer view;
    private Float rating;
    private List<ChapterContentRespone> chapterContent;
}
