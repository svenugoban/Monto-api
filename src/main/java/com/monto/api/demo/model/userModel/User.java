package com.monto.api.demo.model.userModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "user")

public class User{



   @Id
    private String id;
    private String username;
    @Email
    private String email;
    private String name;
    private String password;
    private boolean isEnabled;
    private  String Address;
    private String Dob;
    private String gender;
    private String ImageUrl;
    private Set<Role> roles = new HashSet<>();



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }












    public User() {}

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public User(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public User(String username, String email, String password) {

        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(  String username, @Email String email, String password, String address, String dob) {


        this.username = username;
        this.email = email;
        this.password = password;
        this.Address = address;
       this.Dob = dob;

    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }





    public String getEmail() {
        return email;
    }
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
