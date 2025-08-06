package com.example.beans;

import org.springframework.stereotype.Component;

import com.example.PaymentMethod;

@Component("upiPayment") // Qualifier value for UPI
public class UpiPayment implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("Processing payment via UPI.");
    }
}
