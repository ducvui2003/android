package com.example.truyenapp.model;

public class RewardPoints {
    private  int id, idAccount, point;
    private String recievedDate;
    private int date;

    public RewardPoints(int id, int idAccount, int point, String recievedDate, int date) {
        this.id = id;
        this.idAccount = idAccount;
        this.point = point;
        this.recievedDate = recievedDate;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(String recievedDate) {
        this.recievedDate = recievedDate;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
