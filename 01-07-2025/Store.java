package com.prana;
import java.util.ArrayList;

public class Store {

    private ArrayList<ProductForSale> productCatalog = new ArrayList<>();
    private ArrayList<OrderItem> orderList = new ArrayList<>();

    public static void main(String[] args) {
        Store store = new Store();
        store.initializeStore();

        System.out.println(" PRODUCT CATALOG:");
        store.showCatalog();
        store.addItemToOrder(0, 2); 
        store.addItemToOrder(1, 1); 
        store.addItemToOrder(2, 3); 

        System.out.println("\n ORDER RECEIPT:");
        store.printOrder();
    }

    public void initializeStore() {
        productCatalog.add(new ProductA("Laptop", 75000, "Gaming laptop with 16GB RAM and RTX 3060"));
        productCatalog.add(new ProductB("Headphones", 2999.99, "Wireless noise-cancelling headphones"));
        productCatalog.add(new ProductC("Smartphone", 25999.49, "Android flagship with AMOLED display"));
    }

    public void showCatalog() {
        for (ProductForSale product : productCatalog) {
            product.showDetails();
        }
    }

    public void addItemToOrder(int index, int qty) {
        if (index >= 0 && index < productCatalog.size()) {
            orderList.add(new OrderItem(productCatalog.get(index), qty));
        } else {
            System.out.println("Invalid product index: " + index);
        }
    }

    public void printOrder() {
        double total = 0;
        for (OrderItem item : orderList) {
            item.getProduct().printPricedItem(item.getQuantity());
            total += item.getProduct().getSalesPrice(item.getQuantity());
        }
        System.out.printf("Total Amount: ₹%.2f%n", total);
    }
}
