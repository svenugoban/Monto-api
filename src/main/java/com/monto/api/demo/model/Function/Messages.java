package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Messages")
public class Messages {

    @Id
    String id;
    String sender;
    String message;
    String studentname;
    String date;
    String time;


    public Messages() {

    }

    public Messages(String sender, String message, String studentname, String date, String time) {
        this.sender = sender;
        this.message = message;
        this.studentname = studentname;
        this.date = date;
        this.time = time;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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
