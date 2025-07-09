package Project;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        // Products
        Product p1 = new Product("A101", "Milk", "DairyCo", "Dairy");
        Product p2 = new Product("B202", "Bread", "BakeHouse", "Bakery");

        // Inventory
        InventoryItem i1 = new InventoryItem(p1, 100, 50, 20, 1.99);
        InventoryItem i2 = new InventoryItem(p2, 50, 30, 10, 2.49);

        store.addInventoryItem(i1);
        store.addInventoryItem(i2);

        // Cart
        Cart cart = new Cart("C001", LocalDate.now(), CartType.PHYSICAL);
        cart.addItem(p1, 2);
        cart.addItem(p2, 1);

        store.manageStoreCarts(cart);

        System.out.println("\n--- Cart Contents ---");
        cart.printSalesSlip();

        System.out.println("\n--- Checking Out Cart ---");
        store.checkOutCart(cart);

        System.out.println("\n--- Dairy Products ---");
        store.listProductsByCategory("Dairy");
    }
}
