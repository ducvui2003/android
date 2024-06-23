package com.example.truyenapp.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingResponse {
    private Integer id;
    private Integer chapterId;
    private Integer userId;
    private Float star;
    private Date createdAt;
    private Boolean isDeleted;
}
