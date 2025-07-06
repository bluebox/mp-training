package storesinven;
import java.util.*;

public class Store {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product p1 = new Product("AC");
        Product p2 = new Product("TV");
        Product p3 = new Product("Fridge");
        Product p4 = new Product("Washing Machine");
        Product p5 = new Product("Oven");
        Product p6 = new Product("Microwave");
        InventoryItem i1 = new InventoryItem(p1, 10);  
        InventoryItem i2 = new InventoryItem(p2, 5);
        InventoryItem i3 = new InventoryItem(p3, 7);
        InventoryItem i4 = new InventoryItem(p4, 4);
        InventoryItem i5 = new InventoryItem(p5, 8);
        InventoryItem i6 = new InventoryItem(p6, 6);

        Map<String, InventoryItem> inventoryMap = new HashMap<>();
        inventoryMap.put(p1.getName().toLowerCase(), i1);
        inventoryMap.put(p2.getName().toLowerCase(), i2);
        inventoryMap.put(p3.getName().toLowerCase(), i3);
        inventoryMap.put(p4.getName().toLowerCase(), i4);
        inventoryMap.put(p5.getName().toLowerCase(), i5);
        inventoryMap.put(p6.getName().toLowerCase(), i6);

        Cart cart = new Cart(new Date());

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add item to cart");
            System.out.println("2. Remove item from cart");
            System.out.println("3. Show cart");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter product name to add: ");
                String name = sc.nextLine().toLowerCase();

                if (!inventoryMap.containsKey(name)) {
                    System.out.println("Product not found in inventory.");
                    continue;
                }

                InventoryItem item = inventoryMap.get(name);
                System.out.print("Enter quantity to add: ");
                int qty = sc.nextInt();
                sc.nextLine();

                if (qty <= 0) {
                    System.out.println("Quantity must be positive.");
                    continue;
                }

                boolean success = cart.addItem(item.getProduct(), qty, item);
                if (success) {
                    System.out.println("Added " + qty + " " + item.getProduct().getName() + "(s) to cart.");
                } else {
                    System.out.println("Not enough stock to add that quantity.");
                }

            } else if (choice == 2) {
                System.out.print("Enter product name to remove: ");
                String name = sc.nextLine().toLowerCase();

                if (!inventoryMap.containsKey(name)) {
                    System.out.println("Product not found in inventory.");
                    continue;
                }

                InventoryItem item = inventoryMap.get(name);
                System.out.print("Enter quantity to remove: ");
                int qty = sc.nextInt();
                sc.nextLine();

                if (qty <= 0) {
                    System.out.println("Quantity must be positive.");
                    continue;
                }

                boolean success = cart.removeItem(item.getProduct(), qty, item);
                if (success) {
                    System.out.println("Removed " + qty + " " + item.getProduct().getName() + "(s) from cart.");
                } else {
                    System.out.println("You don't have that many items in your cart.");
                }

            } else if (choice == 3) {
                cart.printCart();

            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}

