package com.example.truyenapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Evaluate {
    private int id, idChapter, idAccount;
    private float star;
    private String evaluateDate;

    public Evaluate() {
    }

    public Evaluate(int id, int idChapter, int idAccount, float star, String evaluateDate) {
        this.id = id;
        this.idChapter = idChapter;
        this.idAccount = idAccount;
        this.star = star;
        this.evaluateDate = evaluateDate;
    }
}
