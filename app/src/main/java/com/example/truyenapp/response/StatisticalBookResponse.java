package com.example.truyenapp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticalBookResponse {
    private int idBook;
    private String thumbnail;
    private int countBookChapters;
    private float averageBookRating;
    private int countBookRating;
    private int countBookViews;
    private int countBookComment;
}
