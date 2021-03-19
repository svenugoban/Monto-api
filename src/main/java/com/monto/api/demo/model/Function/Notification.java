package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Notification")
public class Notification {

    @Id
    private String id;

    private String sender;
    private String message;
    private String studentname;
    private String date;
    private String time;

    private String webUrl;
    private String androidurl;


    public Notification() {
    }

    public Notification(String sender, String message, String studentname, String date, String time, String webUrl, String androidurl) {
        this.sender = sender;
        this.message = message;
        this.studentname = studentname;
        this.date = date;
        this.time = time;
        this.webUrl = webUrl;
        this.androidurl = androidurl;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getAndroidurl() {
        return androidurl;
    }

    public void setAndroidurl(String androidurl) {
        this.androidurl = androidurl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
