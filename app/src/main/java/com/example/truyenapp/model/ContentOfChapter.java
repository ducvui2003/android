package com.example.truyenapp.model;

public class ContentOfChapter {
    private int id, idChapter;
    private String linkImage;

    public ContentOfChapter(int id, int idChapter, String linkImage) {
        this.id = id;
        this.idChapter = idChapter;
        this.linkImage = linkImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
