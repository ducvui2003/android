package com.example.truyenapp.model;

public class LichSuDocTruyen {
    private int id, idtaikhoan, idchapter;

    public LichSuDocTruyen(int id, int idtaikhoan, int idchapter) {
        this.id = id;
        this.idtaikhoan = idtaikhoan;
        this.idchapter = idchapter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public int getIdchapter() {
        return idchapter;
    }

    public void setIdchapter(int idchapter) {
        this.idchapter = idchapter;
    }
}
