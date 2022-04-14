package com.codecool.shop.controller;

import com.codecool.shop.model.CartProduct;
import com.codecool.shop.service.CartService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "reviewCart", urlPatterns = {"/api/review_cart"})
public class ReviewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();

        CartService cartService = CartService.getInstance();
        List<CartProduct> items = cartService.getAll();
        
        out.println(new Gson().toJson(items));
        out.flush();
    }
}
