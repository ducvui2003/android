package com.example.truyenapp.model;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameStory() {
        return nameStory;
    }

    public void setNameStory(String nameStory) {
        this.nameStory = nameStory;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }
}