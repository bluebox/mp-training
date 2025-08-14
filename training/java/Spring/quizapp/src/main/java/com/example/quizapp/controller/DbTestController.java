package com.example.quizapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/db")
public class DbTestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/check")
    public String checkDbConnection() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        return "✅ DB Connection Successful: " + result;
    }
}
