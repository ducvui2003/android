package com.example.truyenapp.model;

public class TheLoai {
    private int id,luotxem;
    private float danhgia;
    private String theloai;

    public TheLoai(int id, int luotxem, float danhgia, String theloai) {
        this.id = id;
        this.luotxem = luotxem;
        this.danhgia = danhgia;
        this.theloai = theloai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(int luotxem) {
        this.luotxem = luotxem;
    }

    public float getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(float danhgia) {
        this.danhgia = danhgia;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }
}
