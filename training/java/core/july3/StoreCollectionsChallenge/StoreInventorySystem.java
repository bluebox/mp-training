package dev.tulasidhar.july3.StoreCollectionsChallenge;

import java.util.*;
import java.time.LocalDate;

public class StoreInventorySystem {
    
    public static void main(String[] args) {
        Store store = new Store();
        
        Product p1 = new Product("P001", "Laptop", "Dell", "Electronics");
        Product p2 = new Product("P002", "Mouse", "Logitech", "Electronics");
        Product p3 = new Product("P003", "Rasgulla", "Homemade", "Food");
        
        InventoryItem i1 = new InventoryItem(p1, 50, 5, 10, 5, 999.99);
        InventoryItem i2 = new InventoryItem(p2, 200, 15, 20, 10, 29.99);
        InventoryItem i3 = new InventoryItem(p3, 100, 2, 5, 2, 2.99);
        
        store.inventory.add(i1);
        store.inventory.add(i2);
        store.inventory.add(i3);
        
        store.aisleInventory.put("Electronics", Arrays.asList(i1, i2));
        store.aisleInventory.put("Food", Arrays.asList(i3));
        
        Cart cart1 = new Cart("C001", LocalDate.now(), "physical");
        Cart cart2 = new Cart("C002", LocalDate.now(), "virtual");
        
        store.carts.add(cart1);
        store.carts.add(cart2);
        
        cart1.addItem(i1);
        cart1.addItem(i2);
        cart2.addItem(i3);
        
        System.out.println("Store Inventory:");
        store.listProductsByCategory("Electronics");
        System.out.println();
        
        System.out.println("Cart 1:");
        cart1.printSalesSlip();
        System.out.println();
        
        System.out.println("Checking out cart 1:");
        store.checkOutCart(cart1);
        System.out.println();
        
        System.out.println("Abandoning old carts:");
        store.abandonCarts();
        System.out.println();
        
        System.out.println("Managing store carts:");
        store.manageStoreCarts();
    }
}

class Product {
    String sku;
    String name;
    String manufacturer;
    String category;
    
    Product(String sku, String name, String manufacturer, String category) {
        this.sku = sku;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
    }
}

class InventoryItem {
    Product product;
    int qtyTotal;
    int qtyReserved;
    int qtyReorder;
    int qtyLow;
    double salesPrice;
    
    InventoryItem(Product product, int qtyTotal, int qtyReserved, int qtyReorder, int qtyLow, double salesPrice) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReserved = qtyReserved;
        this.qtyReorder = qtyReorder;
        this.qtyLow = qtyLow;
        this.salesPrice = salesPrice;
    }
    
    void reserveItem(int qty) {
        qtyReserved += qty;
    }
    
    void releaseItem(int qty) {
        qtyReserved -= qty;
    }
    
    void sellItem(int qty) {
        qtyTotal -= qty;
        qtyReserved -= qty;
    }
    
    void placeInventoryOrder() {
        if (qtyTotal <= qtyLow) {
            qtyTotal += qtyReorder;
            System.out.println("Ordered " + qtyReorder + " units of " + product.name);
        }
    }
}

class Cart {
    String id;
    List<InventoryItem> products;
    LocalDate date;
    String type;
    
    Cart(String id, LocalDate date, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.products = new ArrayList<>();
    }
    
    void addItem(InventoryItem item) {
        products.add(item);
        item.reserveItem(1);
    }
    
    void removeItem(InventoryItem item) {
        products.remove(item);
        item.releaseItem(1);
    }
    
    void printSalesSlip() {
        System.out.println("Cart ID: " + id);
        System.out.println("Date: " + date);
        System.out.println("Type: " + type);
        double total = 0;
        for (InventoryItem item : products) {
            System.out.println(item.product.name + " - $" + item.salesPrice);
            total += item.salesPrice;
        }
        System.out.println("Total: $" + total);
    }
}

class Store {
    List<InventoryItem> inventory;
    List<Cart> carts;
    Map<String, List<InventoryItem>> aisleInventory;
    
    Store() {
        inventory = new ArrayList<>();
        carts = new ArrayList<>();
        aisleInventory = new HashMap<>();
    }
    
    void manageStoreCarts() {
        System.out.println("Managing " + carts.size() + " carts");
        for (Cart cart : carts) {
            System.out.println("Cart " + cart.id + " has " + cart.products.size() + " items");
        }
    }
    
    void checkOutCart(Cart cart) {
        for (InventoryItem item : cart.products) {
            item.sellItem(1);
            item.placeInventoryOrder();
        }
        carts.remove(cart);
        System.out.println("Cart " + cart.id + " checked out");
    }
    
    void abandonCarts() {
        LocalDate cutoffDate = LocalDate.now().minusDays(30);
        List<Cart> toRemove = new ArrayList<>();
        
        for (Cart cart : carts) {
            if (cart.date.isBefore(cutoffDate)) {
                for (InventoryItem item : cart.products) {
                    item.releaseItem(1);
                }
                toRemove.add(cart);
            }
        }
        
        for (Cart cart : toRemove) {
            carts.remove(cart);
            System.out.println("Abandoned cart " + cart.id);
        }
    }
    
    void listProductsByCategory(String category) {
        System.out.println("Products in " + category + ":");
        if (aisleInventory.containsKey(category)) {
            for (InventoryItem item : aisleInventory.get(category)) {
                System.out.println(item.product.name + " - Qty: " + item.qtyTotal + " - $" + item.salesPrice);
            }
        }
    }
}