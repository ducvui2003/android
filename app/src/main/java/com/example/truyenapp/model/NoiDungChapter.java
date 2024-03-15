package com.example.truyenapp.model;

public class NoiDungChapter {
    private int id,idchapter;
    private String linkanh;

    public NoiDungChapter(int id, int idchapter, String linkanh) {
        this.id = id;
        this.idchapter = idchapter;
        this.linkanh = linkanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdchapter() {
        return idchapter;
    }

    public void setIdchapter(int idchapter) {
        this.idchapter = idchapter;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }
}
