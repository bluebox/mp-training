package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.PaymentMethod;

@Service
public class PaymentService {

    private final PaymentMethod paymentMethod;

    @Autowired
    public PaymentService(@Qualifier("cardPayment") PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void makePayment() {
        paymentMethod.processPayment();
    }
}
