package com.example.truyenapp.model;

public class VatPham {
    private int id;
    private String tenvatpham;
    private int diem;
    private String linkanh;

    public VatPham(int id, String tenvatpham, int diem, String linkanh) {
        this.id = id;
        this.tenvatpham = tenvatpham;
        this.diem = diem;
        this.linkanh=linkanh;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getTenvatpham() {
        return tenvatpham;
    }

    public void setTenvatpham(String tenvatpham) {
        this.tenvatpham = tenvatpham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }
}
