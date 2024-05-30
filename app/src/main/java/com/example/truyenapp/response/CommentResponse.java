package com.example.truyenapp.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private Integer id;
    private Integer userId;
    private String bookName;
    private String chapterNumber;
    private String content;
    private Date createdAt;
    private Integer state;
}
