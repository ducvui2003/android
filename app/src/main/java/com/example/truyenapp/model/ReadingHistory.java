package com.example.truyenapp.model;

public class ReadingHistory {
    private int id, idAccount, idChapter;

    public ReadingHistory(int id, int idAccount, int idChapter) {
        this.id = id;
        this.idAccount = idAccount;
        this.idChapter = idChapter;
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

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }
}
