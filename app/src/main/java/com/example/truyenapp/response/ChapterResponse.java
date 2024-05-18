package com.example.truyenapp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class ChapterResponse {
    private Integer id;
    private String name;
    private Date publishDate;
    private Integer view;
}
