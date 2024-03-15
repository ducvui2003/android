package com.example.truyenapp.model;

public class PLTruyen {
    private int id, luotxem;
    private float danhgia;
    private String tentruyen, ngaydang, theloai, linkanh;

    public PLTruyen(int id, int luotxem, float danhgia, String tentruyen, String ngaydang, String theloai, String linkanh) {
        this.id = id;
        this.luotxem = luotxem;
        this.danhgia = danhgia;
        this.tentruyen = tentruyen;
        this.ngaydang = ngaydang;
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

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
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
