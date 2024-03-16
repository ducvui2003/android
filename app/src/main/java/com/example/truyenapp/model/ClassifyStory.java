package com.example.truyenapp.model;

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

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
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
