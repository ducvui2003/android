package com.example.truyenapp.model;

import lombok.ToString;

@ToString
public class ModelSearch {
    private int id, view, chapter;
    private float evaluate;
    private String nameStory, category, linkImage;

    public ModelSearch(int id, int view, int chapter, float evaluate, String nameStory, String category, String linkImage) {
        this.id = id;
        this.view = view;
        this.chapter = chapter;
        this.evaluate = evaluate;
        this.nameStory = nameStory;
        this.category = category;
        this.linkImage = linkImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public float getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(float evaluate) {
        this.evaluate = evaluate;
    }

    public String getNameStory() {
        return nameStory;
    }

    public void setNameStory(String nameStory) {
        this.nameStory = nameStory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
