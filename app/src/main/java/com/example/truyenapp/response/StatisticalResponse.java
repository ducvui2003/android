package com.example.truyenapp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticalResponse {
    private int countBooks;
    private int countChapters;
    private int countViews;
    private int countRating;
    private int countComment;
}
