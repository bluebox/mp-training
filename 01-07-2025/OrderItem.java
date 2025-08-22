package com.prana;
public class OrderItem {
    private ProductForSale product;
    private int quantity;

    public OrderItem(ProductForSale product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductForSale getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
