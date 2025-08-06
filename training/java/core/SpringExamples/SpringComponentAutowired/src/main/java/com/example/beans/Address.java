package com.example.beans;

import org.springframework.stereotype.Component;

@Component
public class Address {
    private String street;
    private String city;
    private String zipCode;

    // Getters and Setters
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    @Override
    public String toString() {
        return "Address{" + "street='" + street + '\'' + ", city='" + city + '\'' + ", zipCode='" + zipCode + '\'' + '}';
    }
}