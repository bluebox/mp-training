package com.prana;
public class ProductB extends ProductForSale {
    public ProductB(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.println("Product B - " + getType() + ": " + getDescription() + ", Price: ₹" + getPrice());
    }
}
