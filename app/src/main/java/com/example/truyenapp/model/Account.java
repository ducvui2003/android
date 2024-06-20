package com.example.truyenapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String email, password, name, phone, linkImage;
    private int rewardPoint, accoutType, status;



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


}