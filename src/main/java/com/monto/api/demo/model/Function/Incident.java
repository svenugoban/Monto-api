package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Incident")
public class Incident {

    @Id
    private String id;

    private String studentnamee;
    private String incident;
    private String time;
    private String date;
    private String location;
    private String sender;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getStudentnamee() {
        return studentnamee;
    }

    public void setStudentnamee(String studentnamee) {
        this.studentnamee = studentnamee;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }






    public Incident(String sender,String studentnamee, String incident, String time, String location, String date) {
        this.studentnamee = studentnamee;
        this.incident = incident;
        this.time = time;
        this.location = location;
        this.date = date;
        this.sender = sender;
    }
}
