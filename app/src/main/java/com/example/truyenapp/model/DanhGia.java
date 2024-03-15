package com.example.truyenapp.model;

public class DanhGia {
    private int id,idchapter,idtaikhoan;
    private float sosao;
    private String ngaydanhgia;

    public DanhGia(int id, int idchapter, int idtaikhoan, float sosao, String ngaydanhgia) {
        this.id = id;
        this.idchapter = idchapter;
        this.idtaikhoan = idtaikhoan;
        this.sosao = sosao;
        this.ngaydanhgia = ngaydanhgia;
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

    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public float getSosao() {
        return sosao;
    }

    public void setSosao(float sosao) {
        this.sosao = sosao;
    }

    public String getNgaydanhgia() {
        return ngaydanhgia;
    }

    public void setNgaydanhgia(String ngaydanhgia) {
        this.ngaydanhgia = ngaydanhgia;
    }
}
