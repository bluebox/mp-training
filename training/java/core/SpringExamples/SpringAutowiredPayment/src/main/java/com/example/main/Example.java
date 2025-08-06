package com.example.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.beans.PaymentService;
import com.example.config.ProjectConfig;

public class Example {

	public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
        
        PaymentService ps=context.getBean(PaymentService.class);
        ps.makePayment();
    }
}
