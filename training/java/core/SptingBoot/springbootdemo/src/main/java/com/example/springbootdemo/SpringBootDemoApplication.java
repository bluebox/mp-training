package com.example.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoApplication implements CommandLineRunner {

    @Autowired
    private MyService myService;

    @Autowired
    private AppConfig appConfig;

    @Override
    public void run(String... args) {
        myService.printProperties();

        System.out.println("AppConfig: ");
        System.out.println("App Name: " + appConfig.getName());
        System.out.println("Timeout: " + appConfig.getTimeout());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
