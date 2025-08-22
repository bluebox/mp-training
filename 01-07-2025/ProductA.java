package com.prana;
public class ProductA extends ProductForSale {
    public ProductA(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.println("Product A - " + getType() + ": " + getDescription() + ", Price: ₹" + getPrice());
    }
}
