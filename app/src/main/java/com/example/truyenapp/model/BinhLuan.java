package com.example.truyenapp.model;

public class BinhLuan {
    private int id,idchapter,idtaikhoan;
    private String noidung,ngaydang;
    private int trangthai;

    public BinhLuan(int id, int idchapter, int idtaikhoan, String noidung, String ngaydang,int trangthai) {
        this.id = id;
        this.idchapter = idchapter;
        this.idtaikhoan = idtaikhoan;
        this.noidung = noidung;
        this.ngaydang = ngaydang;
        this.trangthai=trangthai;
    }

    public int getIdchapter() {
        return idchapter;
    }

    public void setIdchapter(int idchapter) {
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

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgaydang() {
        return ngaydang;
    }

    public void setNgaydang(String ngaydang) {
        this.ngaydang = ngaydang;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
