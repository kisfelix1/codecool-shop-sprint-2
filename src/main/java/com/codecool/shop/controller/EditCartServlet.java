package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
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

@WebServlet(name = "editCart", urlPatterns = {"/api/cart/edit"})
public class EditCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartProduct cartProductTemplate = new Gson().fromJson(request.getReader(), CartProduct.class);
        int productId = cartProductTemplate.getId();
        int amount = cartProductTemplate.getAmount();

        CartService cartService = CartService.getInstance();

        Gson gson = new Gson();
        String json = gson.toJson(cartService.find(productId).getName() + " edited by " + amount);

        cartService.edit(amount, productId);

        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
    }
}
