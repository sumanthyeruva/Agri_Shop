package com.example.agri_shop.models;

public class UserModel {
    String name;
    String email;
    String password;
    public UserModel()
    {

    }
    public UserModel(String name , String email, String password)
    {
        this.email=email;
        this.name=name;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
