package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document("Homework data")
public class Homework {

    @Id
    private String id;

    private String teachername;
    private String description;
    private String Note;
    private String date;
    private String time;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
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

    public Homework() {
    }

    public Homework(String teachername, String description, String note, String date, String time) {
        this.teachername = teachername;
        this.description = description;
        Note = note;
        this.date = date;
        this.time = time;
    }
}
