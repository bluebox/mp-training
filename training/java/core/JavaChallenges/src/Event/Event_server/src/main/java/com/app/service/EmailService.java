package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String to, String userId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("puliudaykiran1234@gmail.com"); 
        message.setTo(to);
        message.setSubject("Register Your Account");
        message.setText("Welcome!Please complete your registration using this user ID: " + userId +" by visiting the site at "+"http://localhost:8080/api/register");
        mailSender.send(message);
    }
    
    public void sendUpdationEmail(String to, String userId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("puliudaykiran1234@gmail.com"); 
        message.setTo(to);
        message.setSubject("Updation of Details");
        message.setText("HI"+userId+"your Details has been Update");
        mailSender.send(message);
    }

  
}
