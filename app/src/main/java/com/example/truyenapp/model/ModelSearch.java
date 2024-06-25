package com.example.truyenapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelSearch {
    private int id, view, chapter;
    private float evaluate;
    private String nameStory, category, linkImage;
}
