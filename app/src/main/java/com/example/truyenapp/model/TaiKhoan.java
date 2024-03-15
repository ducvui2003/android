package com.example.truyenapp.model;

public class TaiKhoan {
    private int id;
    private String email,matkhau,hoten,dienthoai;
    private int diemthuong,loaitk;
    private String linkanh;


    public TaiKhoan(int id, String email, String matkhau, String hoten, String dienthoai, int diemthuong, int loaitk, String linkanh) {
        this.id = id;
        this.email = email;
        this.matkhau = matkhau;
        this.hoten = hoten;
        this.dienthoai = dienthoai;
        this.diemthuong = diemthuong;
        this.loaitk = loaitk;
        this.linkanh=linkanh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public int getDiemthuong() {
        return diemthuong;
    }

    public void setDiemthuong(int diemthuong) {
        this.diemthuong = diemthuong;
    }

    public int getLoaitk() {
        return loaitk;
    }

    public void setLoaitk(int loaitk) {
        this.loaitk = loaitk;
    }

    public String getLinkanh() {
        return linkanh;
    }

    public void setLinkanh(String linkanh) {
        this.linkanh = linkanh;
    }
}