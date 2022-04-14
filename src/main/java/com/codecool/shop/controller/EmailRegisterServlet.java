package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.email.EmailUtil;
import com.codecool.shop.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/sendRegisterEmail"})
public class EmailRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String data = buffer.toString();
        try {
            String htmlTemplate = templateEngine(req, resp, data);
            EmailUtil.sendEmail(data, htmlTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String templateEngine(HttpServletRequest req, HttpServletResponse resp, String email) throws IOException {

        String correctEmail = email.replace("\"", "").replace("\n", "");
        UserService userService = UserService.getInstance();
        String name = userService.find(correctEmail);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("username", name);
        return engine.process("register_confirmation.html", context);
    }
}
