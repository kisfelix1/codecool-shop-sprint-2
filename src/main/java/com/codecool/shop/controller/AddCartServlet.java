package com.codecool.shop.controller;

import com.codecool.shop.model.Product;
import com.codecool.shop.service.CartService;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "addCart", urlPatterns = {"/api/add_cart"})
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String itemId = req.getParameter("itemId");

        ProductService productService = ProductService.getInstance();
        Product item = productService.getProductById(Integer.parseInt(itemId));
        CartService cartService = CartService.getInstance();

        if (cartService.find(item.getId()) != null) {
            cartService.edit(1, item.getId());
        } else {
            cartService.add(item, 1);
        }
        String json = new Gson().toJson(item.getName() + "added to cart");

        PrintWriter out = resp.getWriter();
        out.println(json);
        out.flush();
    }
}
