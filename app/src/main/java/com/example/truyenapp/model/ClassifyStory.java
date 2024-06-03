package com.example.truyenapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class ClassifyStory {
    private int id, view;
    private float evaluate;
    private String nameStory, postingDate, category, linkImage;

    public ClassifyStory(int id, int view, float evaluate, String nameStory, String postingDate, String category, String linkImage) {
        this.id = id;
        this.view = view;
        this.evaluate = evaluate;
        this.nameStory = nameStory;
        this.postingDate = postingDate;
        this.category = category;
        this.linkImage = linkImage;
    }

}
