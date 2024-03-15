package com.example.truyenapp.model;

public class DoiThuong {
    private int id, idvatpham, idtaikhoan;
    private String ngaydoi;

    public DoiThuong(int id, int idvatpham, int idtaikhoan, String ngaydoi) {
        this.id = id;
        this.idvatpham = idvatpham;
        this.idtaikhoan = idtaikhoan;
        this.ngaydoi = ngaydoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdvatpham() {
        return idvatpham;
    }

    public void setIdvatpham(int idvatpham) {
        this.idvatpham = idvatpham;
    }

    public int getIdtaikhoan() {
        return idtaikhoan;
    }

    public void setIdtaikhoan(int idtaikhoan) {
        this.idtaikhoan = idtaikhoan;
    }

    public String getNgaydoi() {
        return ngaydoi;
    }

    public void setNgaydoi(String ngaydoi) {
        this.ngaydoi = ngaydoi;
    }
}
