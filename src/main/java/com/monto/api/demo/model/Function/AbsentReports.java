package com.monto.api.demo.model.Function;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Absent Reports")
public class AbsentReports {

    @Id
    private String id;



    private String absentdate;
    private String reason;
    private String parentusername;
    private String studentname;



    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getParentusername() {
        return parentusername;
    }

    public void setParentusername(String parentusername) {
        this.parentusername = parentusername;
    }

    public String getAbsentdate() {
        return absentdate;
    }

    public void setAbsentdate(String absentdate) {
        this.absentdate = absentdate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public AbsentReports() {
    }

    public AbsentReports(String studentname, String absentdate, String reason) {
        this.studentname = studentname;
        this.absentdate = absentdate;
        this.reason = reason;
    }


}
