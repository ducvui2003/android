package com.example.truyenapp.model;

public class Statistical {
    private int id, idStory, totalView;
    private float averageOfStar;

    public Statistical(int id, int idStory, int totalView, float averageOfStar) {
        this.id = id;
        this.idStory = idStory;
        this.totalView = totalView;
        this.averageOfStar = averageOfStar;
    }

    public int getIdStory() {
        return idStory;
    }

    public void setIdStory(int idStory) {
        this.idStory = idStory;
    }

    public float getAverageOfStar() {
        return averageOfStar;
    }

    public void setAverageOfStar(float averageOfStar) {
        this.averageOfStar = averageOfStar;
    }

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
