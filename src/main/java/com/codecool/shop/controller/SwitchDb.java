package com.codecool.shop.controller;

import com.codecool.shop.config.DbConfig;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "switchDb", urlPatterns = {"/api/switch_db"})
public class SwitchDb extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        DbConfig.switchDb();
    }
}
