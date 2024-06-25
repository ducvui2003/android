package com.example.truyenapp.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {
    private int id;
    private String title;
    private String content;
    @SerializedName("date")
    private Date postingDate;
}
