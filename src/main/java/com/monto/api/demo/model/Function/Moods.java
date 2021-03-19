package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Moods")
public class Moods {

    @Id
    public String id;

    public String moodname;
    public  String studentname;
    public String date;
    public String time;
    public String sender;

    public String getMoodname() {
        return moodname;
    }

    public void setMoodname(String moodname) {
        this.moodname = moodname;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Moods() {
    }

    public Moods(String moodname, String studentname, String date, String time, String sender) {
        this.moodname = moodname;
        this.studentname = studentname;
        this.date = date;
        this.time = time;
        this.sender = sender;
    }
}
