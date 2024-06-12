package com.example.truyenapp.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private int id;
    private String name;
    private int point;
    private String image;
    private Date date;
    private boolean isExchange;
}
