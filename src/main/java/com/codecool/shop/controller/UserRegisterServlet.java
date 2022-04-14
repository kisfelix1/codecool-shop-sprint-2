package com.codecool.shop.controller;

import com.codecool.shop.model.User;
import com.codecool.shop.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userRegister", urlPatterns = {"/api/user/register"})
public class UserRegisterServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User userTemplate = new Gson().fromJson(request.getReader(), User.class);
            String userName = userTemplate.getName();
            String email = userTemplate.getEmail();
            String password = userTemplate.getPassword();

            UserService userService = UserService.getInstance();

            Gson gson = new Gson();
            String json = gson.toJson(userName + " has been registered in the Database");

            userService.add(new User(userName, email, password));

            PrintWriter out = response.getWriter();
            out.println(json);
            out.flush();
        }
    }
