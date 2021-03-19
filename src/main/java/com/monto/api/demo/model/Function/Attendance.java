package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Attendance Data")
public class Attendance {

    @Id
    private String id;

    private String studentname;
    private boolean present;
    private String teachername;

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }


    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public Attendance() {
    }

    public Attendance(String studentname, boolean present) {
        this.studentname = studentname;
        this.present = present;
    }

    public Attendance(String studentname) {
        this.studentname = studentname;
    }

    
}
