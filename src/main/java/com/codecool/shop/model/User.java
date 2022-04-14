package com.codecool.shop.model;

public class User extends BaseModel{

    private String password;
    private String email;

    public User(String name, String email, String password) {
        super(name);
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
