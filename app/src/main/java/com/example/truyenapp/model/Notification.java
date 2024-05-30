package com.example.truyenapp.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Notification {
    private int id;
    private String title;
    private String content;

    @SerializedName("date")
    private Date postingDate;

    public Notification(int id, String title, String content, Date postingDate) {
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

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }
}
