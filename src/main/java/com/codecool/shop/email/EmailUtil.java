package com.codecool.shop.email;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailUtil {
    public static void sendEmail (String recipient, String htmlContent) throws Exception {
        System.out.println("Sending Email...");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myEmail = "runtime.error.team.404@gmail.com";
        String myPassword = "asd123.?!";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPassword);
            }
        });

        Message message = prepareMessage(session, myEmail, recipient, htmlContent);
        Transport.send(message);
        System.out.println("Email sent.");
    }

    private static Message prepareMessage(Session session, String myEmail, String recipient, String htmlContent) throws Exception {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject("Order Confirmation");
        message.setContent(htmlContent, "text/html");
        return message;
    }
}
