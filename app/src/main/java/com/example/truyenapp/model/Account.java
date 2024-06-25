package com.example.truyenapp.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
    private int id;
    private String email, password, fullName, phone, linkImage, role;
    private int point, accoutType, status;
}