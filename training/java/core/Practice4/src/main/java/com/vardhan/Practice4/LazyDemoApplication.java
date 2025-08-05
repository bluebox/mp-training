package com.vardhan.Practice4;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class LazyDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        System.out.println("Application Context initialized.");

        UserService userService = context.getBean(UserService.class); 
        userService.registerUser("Saketh", "saketh@gmail.com");

        EmailService emailService = context.getBean(EmailService.class); 
        emailService.sendEmail("vardhan@gmail.com", "Alert", "Something happened!");
    }
}
