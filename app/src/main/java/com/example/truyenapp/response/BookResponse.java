package com.example.truyenapp.response;

import java.sql.Date;
import java.util.List;

public class BookResponse {
    Integer id;
    String name;
    String author;
    String description;
    Integer view;
    Double rating;
    String thumbnail;
    Integer quantityChapter;

    List<String> categoryNames;
    Date publishDate;

    public BookResponse() {
    }

    public BookResponse(Integer id, String name, String author, String description, Integer view, Double rating, String thumbnail, Integer quantityChapter, List<String> categoryNames, Date publishDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.view = view;
        this.rating = rating;
        this.thumbnail = thumbnail;
        this.quantityChapter = quantityChapter;
        this.categoryNames = categoryNames;
        this.publishDate = publishDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getQuantityChapter() {
        return quantityChapter;
    }

    public void setQuantityChapter(Integer quantityChapter) {
        this.quantityChapter = quantityChapter;
    }

}
