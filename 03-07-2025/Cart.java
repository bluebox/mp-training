package com.prana;

import java.time.LocalDate;
import java.util.*;

public class Cart {
    public enum Type { PHYSICAL, VIRTUAL }

    private final int id;
    private final Map<Product, Integer> items = new HashMap<>();
    private final LocalDate date;
    private final Type type;

    public Cart(int id, Type type) {
        this.id = id;
        this.type = type;
        this.date = LocalDate.now();
    }

    public void addItem(Product product, int quantity) {
        items.merge(product, quantity, Integer::sum);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public LocalDate getDate() { return date; }
    public Type getType() { return type; }

    public void printSlip() {
        System.out.println(" Receipt for Cart #" + id);
        for (var entry : items.entrySet()) {
            System.out.println(" - " + entry.getKey().getName() + ": " + entry.getValue() + " pcs");
        }
    }
}
