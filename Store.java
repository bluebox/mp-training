package abst.lpa;

import java.util.ArrayList;

public class Store {
    public static void main(String[] args) {

        // Step 1: Create products
        ArrayList<ProductForSale> products = new ArrayList<>();
        products.add(new Clothing("T-Shirt", "Cotton, unisex",499));
        products.add(new Electronics("Bluetooth Speaker", "10W, Waterproof",2999));
        products.add(new Food("Chocolate Cake",  "Dark chocolate layered cake",299));

        // Step 2: Display product details
        
        for (ProductForSale p : products) {
            p.showDetails();
            System.out.println();
        }

        // Step 3: Create order
        ArrayList<OrderItem> order = new ArrayList<>();
        order.add(new OrderItem(2, products.get(0)));  // 2 T-Shirts
        order.add(new OrderItem(1, products.get(1)));  // 1 Speaker
        order.add(new OrderItem(3, products.get(2)));  // 3 Cakes

        // Step 4: Print Receipt
        System.out.println("\n=== Receipt ===");
        double total = 0;
        for (OrderItem item : order) {
            item.printItem();
            total += item.getTotalPrice();
        }

        System.out.printf("\nTotal Amount: ₹%.2f%n", total);
    }
}
