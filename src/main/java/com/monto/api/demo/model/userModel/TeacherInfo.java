package com.monto.api.demo.model.userModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document("TeacherInfo")
public class TeacherInfo {


    private String teacherid;
    @Id
    private String id;
    private String teacher_name;
    private String Dob;
    private String gender;
    private String address;
    private String teacherusername;
    private String qualification;


    public String getTeacherusername() {
        return teacherusername;
    }

    public void setTeacherusername(String teacherusername) {
        this.teacherusername = teacherusername;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacher_id() {
        return teacherid;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacherid = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public TeacherInfo() {
    }

    public TeacherInfo( String teacher_id, String teacher_name, String dob, String gender, String address,String qualification) {
        this.teacherid = teacher_id;
        this.teacher_name = teacher_name;
        Dob = dob;
        this.gender = gender;
        this.address = address;
        this.qualification =qualification;
    }
}
