package com.example.truyenapp.model;

public class Account {
    private int id;
    private String email, password, name, phone;
    private int rewardPoint, accoutType;
    private String linkImage;


    public Account(int id, String email, String password, String name, String phone, int rewardPoint, int accoutType, String linkImage) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.rewardPoint = rewardPoint;
        this.accoutType = accoutType;
        this.linkImage = linkImage;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public int getAccoutType() {
        return accoutType;
    }

    public void setAccoutType(int accoutType) {
        this.accoutType = accoutType;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}