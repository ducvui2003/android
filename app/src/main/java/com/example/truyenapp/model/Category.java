package com.example.truyenapp.model;

public class Category {
    private int id, view;
    private float evaluate;
    private String category;

    public Category(int id, int view, float evaluate, String category) {
        this.id = id;
        this.view = view;
        this.evaluate = evaluate;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public float getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(float evaluate) {
        this.evaluate = evaluate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
