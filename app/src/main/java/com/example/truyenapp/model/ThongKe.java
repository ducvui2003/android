package com.example.truyenapp.model;

public class ThongKe {
    private int id,idtruyen,tongluotxem;
    private float sosaotb;

    public ThongKe(int id, int idtruyen, int tongluotxem, float sosaotb) {
        this.id = id;
        this.idtruyen = idtruyen;
        this.tongluotxem = tongluotxem;
        this.sosaotb = sosaotb;
    }

    public int getIdtruyen() {
        return idtruyen;
    }

    public void setIdtruyen(int idtruyen) {
        this.idtruyen = idtruyen;
    }

    public float getSosaotb() {
        return sosaotb;
    }

    public void setSosaotb(float sosaotb) {
        this.sosaotb = sosaotb;
    }

    public int getTongluotxem() {
        return tongluotxem;
    }

    public void setTongluotxem(int tongluotxem) {
        this.tongluotxem = tongluotxem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
