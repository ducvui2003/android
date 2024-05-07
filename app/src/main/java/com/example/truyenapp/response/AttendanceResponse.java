package com.example.truyenapp.response;

import java.sql.Date;

import lombok.Data;

public class AttendanceResponse {
    private Double score;
    private Date date;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
