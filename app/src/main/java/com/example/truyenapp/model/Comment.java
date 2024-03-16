package com.example.truyenapp.model;

public class Comment {
    private int id, idChapter, idAccount;
    private String content, postingDate;
    private int status;

    public Comment(int id, int idChapter, int idAccount, String content, String postingDate, int status) {
        this.id = id;
        this.idChapter = idChapter;
        this.idAccount = idAccount;
        this.content = content;
        this.postingDate = postingDate;
        this.status = status;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostingDay() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
