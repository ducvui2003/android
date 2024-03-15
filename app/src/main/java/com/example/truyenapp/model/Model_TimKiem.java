package com.example.truyenapp.model;

public class Model_TimKiem {
    private int id, luotxem, chapter;
    private float danhgia;
    private String tentruyen, theloai, linkanh;

    public Model_TimKiem(int id, int luotxem, int chapter, float danhgia, String tentruyen, String theloai, String linkanh) {
        this.id = id;
        this.luotxem = luotxem;
        this.chapter = chapter;
        this.danhgia = danhgia;
        this.tentruyen = tentruyen;
        this.theloai = theloai;
        this.linkanh = linkanh;
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

    public int getChapter() {
        return chapter;
    }

    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    public float getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(float danhgia) {
        this.danhgia = danhgia;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }
}
