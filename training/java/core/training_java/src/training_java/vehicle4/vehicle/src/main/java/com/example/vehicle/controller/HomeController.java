package com.example.vehicle.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Welcome to public home!";
    }
    @GetMapping("/admin/dashboard")
    public String admin() {
        return "Welcome ADMIN!";
    }
    @GetMapping("/user/profile")
    public String user() {
        return "Welcome USER!";
    }
}