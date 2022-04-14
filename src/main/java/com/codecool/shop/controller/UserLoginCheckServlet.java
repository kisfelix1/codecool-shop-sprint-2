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

@WebServlet(name = "userLoginCheck", urlPatterns = {"/api/user/login"})
public class UserLoginCheckServlet extends HttpServlet {

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User userTemplate = new Gson().fromJson(request.getReader(), User.class);
            String email = userTemplate.getEmail();
            String password = userTemplate.getPassword();

            UserService userService = UserService.getInstance();

            Gson gson = new Gson();
            String json = gson.toJson(userService.find(email, password));

            userService.find(email, password);

            PrintWriter out = response.getWriter();
            out.println(json);
            out.flush();
        }
    }
