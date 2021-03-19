package com.monto.api.demo.model.userModel;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("AndroidLoginDetails")
public class AndroidUser {


    @Id
    private String id;
    private String email;
    private String password;
    private String usertype;
    private  String username;

    private  String webprofileurl;
    private String androidurl;

    public String getAndroidurl() {
        return androidurl;
    }

    public void setAndroidurl(String androidurl) {
        this.androidurl = androidurl;
    }

    public String getWebprofileurl() {
        return webprofileurl;
    }

    public void setWebprofileurl(String webprofileurl) {
        this.webprofileurl = webprofileurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AndroidUser(String email, String password, String usertype , String username) {
        this.email = email;
        this.password = password;
        this.usertype = usertype;
        this.username = username;
    }
}
