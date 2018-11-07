package com.uekiwg.ngchat.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.SimpleDateFormat;
import java.util.Date;

@IgnoreExtraProperties
public class Comment {

    public String email = "";
    public String initial = "";
    public String content = "";
    public Long date;

    public Comment() {
    }
    public Comment(String email, String initial, String content, Long date) {
        this.email = email;
        this.initial = initial;
        this.content = content;
        this.date = date;
    }
    public Comment(String email, String content) {
        this.email = email;
        this.initial = email.substring(0, 1);
        this.content = content;
        this.date = new Date().getTime();
    }
    public String getDateTimeStr() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date(date));
    }
}

