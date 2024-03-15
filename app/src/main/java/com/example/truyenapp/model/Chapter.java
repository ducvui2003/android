package com.example.truyenapp.model;

public class Chapter {
    private int id,idtruyen,soluotxem;
    private float danhgia;
    private String tenchapter,ngaydang;

    public Chapter(int id, int idtruyen, String tenchapter, String ngaydang, int soluotxem, float danhgia) {
        this.id = id;
        this.idtruyen = idtruyen;
        this.tenchapter = tenchapter;
        this.ngaydang = ngaydang;
        this.soluotxem = soluotxem;
        this.danhgia = danhgia;
    }

    public int getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(int idtruyen) {
        this.idtruyen = idtruyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(float danhgia) {
        this.danhgia = danhgia;
    }

    public String getTenchapter() {
        return tenchapter;
    }

    public void setTenchapter(String tenchapter) {
        this.tenchapter = tenchapter;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public int getSoluotxem() {
        return soluotxem;
    }

    public void setSoluotxem(int soluotxem) {
        this.soluotxem = soluotxem;
    }
}
