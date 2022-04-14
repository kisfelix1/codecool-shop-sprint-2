package com.codecool.shop.dao;
import com.codecool.shop.model.User;

public interface UserDao {

    void add(User user);
    User find(String email, String password);
    String find(String email);
}
