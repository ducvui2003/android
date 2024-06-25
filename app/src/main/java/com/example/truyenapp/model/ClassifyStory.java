package com.example.truyenapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassifyStory {
    private int id, view;
    private float evaluate;
    private String nameStory, postingDate, category, linkImage;
}
