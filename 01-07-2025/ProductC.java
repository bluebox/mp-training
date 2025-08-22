package com.prana;

public class ProductC extends ProductForSale {
    public ProductC(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.println("Product C - " + getType() + ": " + getDescription() + ", Price: ₹" + getPrice());
    }
}
