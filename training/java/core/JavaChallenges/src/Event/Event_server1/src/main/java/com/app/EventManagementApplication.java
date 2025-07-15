package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.service.EmailService;

@SpringBootApplication
public class EventManagementApplication implements CommandLineRunner {

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(EventManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Optional: to test email is working or not
        // emailService.sendEmail("test@example.com", "Test Email", "This is a test email from Spring Boot application.");
    }
}
