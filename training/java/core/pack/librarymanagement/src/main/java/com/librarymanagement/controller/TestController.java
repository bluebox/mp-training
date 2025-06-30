package com.librarymanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController 
{
    @GetMapping("/test")
    public String hello() {
        return "Hello, your REST API is working!";
    }
}
