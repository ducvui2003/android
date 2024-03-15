package com.example.truyenapp.model;

public class Truyen {
    private int id;
    private String tentruyen, tacgia, mota, theloai, linkhanh;
    private int trangthai;
    private String key_search;


    public Truyen(int id, String tentruyen, String tacgia, String mota, String theloai, String linkhanh, int trangthai, String key_search) {
        this.id = id;
        this.tentruyen = tentruyen;
        this.tacgia = tacgia;
        this.mota = mota;
        this.theloai = theloai;
        this.linkhanh = linkhanh;
        this.trangthai = trangthai;
        this.key_search=key_search;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getLinkhanh() {
        return linkhanh;
    }

    public void setLinkhanh(String linkhanh) {
        this.linkhanh = linkhanh;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getKey_search() {
        return key_search;
    }

    public void setKey_search(String key_search) {
        this.key_search = key_search;
    }
}