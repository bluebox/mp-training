package com.example.beans;

import org.springframework.stereotype.Component;

import com.example.PaymentMethod;

@Component("cardPayment") // Qualifier value for Card
public class CardPayment implements PaymentMethod {
    @Override
    public void processPayment() {
        System.out.println("Processing payment via Card.");
    }
}