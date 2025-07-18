package com.app.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.username}")
    private String emailFrom;

    public void sendRegistrationEmail(String toEmail, String userId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom); 
        message.setTo(toEmail);
        message.setSubject("Register Your Account");
        message.setText("Welcome!Please complete your registration using this user ID: " + userId +" by visiting the site at "+"http://10.129.241.112:3000/register");
        mailSender.send(message);
    }
    
    public void sendUpdationEmail(String toEmail, String userId) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom); 
        message.setTo(toEmail);
        message.setSubject("Updation of Details");
        message.setText("HI"+userId+"your Details has been Update");
        mailSender.send(message);
    }

  
}

