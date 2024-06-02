package com.example.truyenapp.response;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RatingResponse {
    private Integer id;
    private Integer chapterId;
    private Integer userId;
    private Float star;
    private Date createdAt;

}
