package com.example.truyenapp.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Comment {

    private int id, idChapter, idAccount;
    private String content, postingDay;
    private int state;
    private String bookName;
    private String chapterNumber;
    private Date createdAt;

    public Comment(int id, int idChapter, int idAccount, String content, String postingDay, int state) {
        this.id = id;
        this.idChapter = idChapter;
        this.idAccount = idAccount;
        this.content = content;
        this.postingDay = postingDay;
        this.state = state;
    }

}
