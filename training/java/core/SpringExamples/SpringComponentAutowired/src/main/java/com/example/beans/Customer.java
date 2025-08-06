package com.example.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    private final Address address;
    private final PaymentMethod paymentMethod;

    @Autowired
    public Customer(Address address, PaymentMethod paymentMethod) {
        this.address = address;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public Address getAddress() { return address; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }

    @Override
    public String toString() {
        return "Customer{" + "address=" + address + ", paymentMethod=" + paymentMethod + '}';
    }
}

/*
 * import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    @Autowired
    private Address address;
    @Autowired
    private PaymentMethod paymentMethod;

    // Getters and Setters (if needed, though for field injection, often just getters are used)
    public Address getAddress() { return address; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setAddress(Address address) { this.address = address; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    @Override
    public String toString() {
        return "Customer{" + "address=" + address + ", paymentMethod=" + paymentMethod + '}';
    }
}
 */
