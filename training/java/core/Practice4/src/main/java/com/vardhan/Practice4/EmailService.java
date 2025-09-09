package com.vardhan.Practice4;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class EmailService {

    public EmailService() {
        System.out.println("EmailService bean is being initialized!");
    }
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email to " + to + " with subject: " + subject);
    }
}
