package com.example.truyenapp.model;

public class Evaluate {
    private int id, idChapter, idAccount;
    private float star;
    private String evaluateDate;

    public Evaluate(int id, int idChapter, int idAccount, float star, String evaluateDate) {
        this.id = id;
        this.idChapter = idChapter;
        this.idAccount = idAccount;
        this.star = star;
        this.evaluateDate = evaluateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }
}
