package com.example.truyenapp.model;

public class Item {
    private int id;
    private String nameItem;
    private int point;
    private String linkImage;

    public Item(int id, String nameItem, int point, String linkImage) {
        this.id = id;
        this.nameItem = nameItem;
        this.point = point;
        this.linkImage = linkImage;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
