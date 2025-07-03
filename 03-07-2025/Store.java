package com.prana;

import java.time.LocalDate;
import java.util.*;

public class Store {
    private final Map<String, InventoryItem> inventory = new HashMap<>();
    private final List<Cart> carts = new ArrayList<>();
    private final Map<String, List<InventoryItem>> aisleInventory = new HashMap<>();

    public void addProductToInventory(InventoryItem item) {
        inventory.put(item.getProduct().getSku(), item);
        aisleInventory.computeIfAbsent(item.getProduct().getCategory(), k -> new ArrayList<>()).add(item);
    }

    public void addCart(Cart cart) {
        carts.add(cart);
    }

    public void checkoutCart(Cart cart) {
        System.out.println("\n Checking out cart...");
        for (var entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();
            InventoryItem item = inventory.get(product.getSku());
            if (item != null) item.sellItem(qty);
        }
        cart.printSlip();
        carts.remove(cart);
    }

    public void abandonOldCarts() {
        LocalDate today = LocalDate.now();
        carts.removeIf(cart -> !cart.getDate().equals(today));
    }

    public void listProductsByCategory() {
        System.out.println("\n🛒 Products by Category:");
        aisleInventory.forEach((category, items) -> {
            System.out.println("[" + category + "]");
            items.forEach(item -> System.out.println("   " + item));
        });
    }
}
