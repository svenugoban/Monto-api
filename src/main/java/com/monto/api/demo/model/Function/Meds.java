package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "Meds Data")
public class Meds {

    @Id
    public String id;

    public String medid;
    public String studentname;
    public String time;
    public String date;
    public String incident;
    public String firstaid;


    public Meds() {

    }

    public Meds(String studentname, String time, String date, String incident, String firstaid ,String medid) {
        this.studentname = studentname;
        this.time = time;
        this.date = date;
        this.incident = incident;
        this.firstaid = firstaid;
        this.medid = medid;
    }


    public String getMedid() {


        return medid;
    }



    public void setMedid(String medid) {
        this.medid = medid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public String getFirstaid() {
        return firstaid;
    }

    public void setFirstaid(String firstaid) {
        this.firstaid = firstaid;
    }
}



