package com.example.beans;

import org.springframework.stereotype.Component;

@Component
public class PaymentMethod {
    private String type; //card, PayPal,Gpay
    private String details;

    @Override
	public String toString() {
		return "PaymentMethod [type=" + type + ", details=" + details + "]";
	}
	public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

}