package com.medplus.exptracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medplus.exptracker.Model.Category;
import com.medplus.exptracker.Service.ExpenseService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "http://localhost:3000/",allowedHeaders = "*",allowCredentials = "true")
@Validated
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    

    @GetMapping(value = "/categories", produces = "application/json")
    public List<Category> getCategories() {
        return expenseService.getCategories();
    }
}