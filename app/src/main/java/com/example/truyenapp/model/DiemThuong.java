package com.example.truyenapp.model;

public class DiemThuong {
    private  int id, idtaikhoan,diem;
    private String ngaynhan;
    private int thu;

    public DiemThuong(int id, int idtaikhoan, int diem, String ngaynhan, int thu) {
        this.id = id;
        this.idtaikhoan = idtaikhoan;
        this.diem = diem;
        this.ngaynhan = ngaynhan;
        this.thu = thu;
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

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getNgaynhan() {
        return ngaynhan;
    }

    public void setNgaynhan(String ngaynhan) {
        this.ngaynhan = ngaynhan;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }
}
