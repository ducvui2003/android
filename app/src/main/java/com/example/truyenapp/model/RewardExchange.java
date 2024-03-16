package com.example.truyenapp.model;

public class RewardExchange {
    private int id, idItems, idAccount;
    private String exchangeDate;

    public RewardExchange(int id, int idItems, int idAccount, String exchangeDate) {
        this.id = id;
        this.idItems = idItems;
        this.idAccount = idAccount;
        this.exchangeDate = exchangeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdItems() {
        return idItems;
    }

    public void setIdItems(int idItems) {
        this.idItems = idItems;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
}
