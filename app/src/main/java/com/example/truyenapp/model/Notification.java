package com.example.truyenapp.model;

public class Notification {
    private int id;
    private String title, content, postingDate;

    public Notification(int id, String title, String content, String postingDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postingDate = postingDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }
}
