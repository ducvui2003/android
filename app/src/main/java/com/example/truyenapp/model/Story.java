package com.example.truyenapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Story {
    private int id;
    private String nameStory, author, describe, category, linkImage;
    private int status;
    private String keySearch;


    public Story(int id, String nameStory, String author, String describe, String category, String linkImage, int status, String keySearch) {
        this.id = id;
        this.nameStory = nameStory;
        this.author = author;
        this.describe = describe;
        this.category = category;
        this.linkImage = linkImage;
        this.status = status;
        this.keySearch = keySearch;

    }

}