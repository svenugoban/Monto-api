package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Badge data")
public class Badges {

    @Id
    public String id;

    public String badgename;
    public  String studentname;
    public String date;
    public String time;
    public String sender;



    public Badges() {
    }

    public Badges(String badgename, String studentname, String date,String time,String sender) {
        this.badgename = badgename;
        this.studentname = studentname;
        this.date = date;
        this.time = time;
        this.sender =sender;

    }


    public String getBadgename() {
        return badgename;
    }

    public void setBadgename(String badgename) {
        this.badgename = badgename;
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


  
}
