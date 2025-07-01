package com.prana;
public abstract class ProductForSale {
    private String type;
    private double price;
    private String description;

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public double getSalesPrice(int qty) {
        return qty * price;
    }

    public void printPricedItem(int qty) {
        System.out.printf("%d x %s @ ₹%.2f = ₹%.2f%n", qty, type, price, getSalesPrice(qty));
    }

    public abstract void showDetails();

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
