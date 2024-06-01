package com.example.truyenapp.model;

import java.io.Serializable;
import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Comment {

    private int id, idChapter, idAccount;
    private String content, postingDay;
    private int state;
    private String bookName;
    private String chapterName;
    private Date createdAt;
    private String thumbnail;
    private String email;
    private String username;
    private String avatar;
    public Comment(int id, int idChapter, int idAccount, String content, String postingDay, int state) {
        this.id = id;
        this.idChapter = idChapter;
        this.idAccount = idAccount;
        this.content = content;
        this.postingDay = postingDay;
        this.state = state;
    }

}
