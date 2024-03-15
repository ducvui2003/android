package com.example.truyenapp.model;

public class ThongBao {
    private int id;
    private String tieude, noidung,ngaydang;

    public ThongBao(int id, String tieude, String noidung, String ngaydang) {
        this.id = id;
        this.tieude = tieude;
        this.noidung = noidung;
        this.ngaydang = ngaydang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
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
}
