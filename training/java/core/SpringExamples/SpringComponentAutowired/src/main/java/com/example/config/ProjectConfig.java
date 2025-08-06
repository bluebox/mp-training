package com.example.config;

import com.example.beans.Address;
import com.example.beans.PaymentMethod;
import com.example.beans.Person;
import com.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.example.beans")
public class ProjectConfig {
//    @Bean
//    public Address address() {
//        Address addr = new Address();
//        addr.setStreet("123 Main St");
//        addr.setCity("Anytown");
//        addr.setZipCode("12345");
//        return addr;
//    }
//
//    @Bean
//    public PaymentMethod paymentMethod() {
//        PaymentMethod pm = new PaymentMethod();
//        pm.setType("Credit Card");
//        pm.setDetails("**** **** **** 1234");
//        return pm;
//    }

}
