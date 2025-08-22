package com.prana;

public class InventoryItem {
    private final Product product;
    private int totalQty;
    private int reservedQty;
    private int reorderQty;
    private int lowQty;
    private double price;

    public InventoryItem(Product product, int totalQty, int lowQty, int reorderQty, double price) {
        this.product = product;
        this.totalQty = totalQty;
        this.lowQty = lowQty;
        this.reorderQty = reorderQty;
        this.price = price;
    }

    public void reserveItem(int qty) {
        if (qty <= (totalQty - reservedQty)) {
            reservedQty += qty;
        }
    }

    public void releaseItem(int qty) {
        reservedQty = Math.max(0, reservedQty - qty);
    }

    public void sellItem(int qty) {
        if (qty <= reservedQty) {
            reservedQty -= qty;
            totalQty -= qty;
        }
    }

    public boolean isLowStock() {
        return totalQty <= lowQty;
    }

    public void reorder() {
        totalQty += reorderQty;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "%s | Total: %d | Reserved: %d | ₹%.2f".formatted(product, totalQty, reservedQty, price);
    }
}
