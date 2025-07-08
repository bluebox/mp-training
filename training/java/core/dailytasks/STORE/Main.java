import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store myStore = new Store();

        myStore.addProductToStore(new ProductA("Laptop", 1200.00, "High-performance laptop for professionals", "Electronics"));
        myStore.addProductToStore(new ProductB("Smartphone", 800.00, "Latest model with advanced camera", "TechGadget"));
        myStore.addProductToStore(new ProductC("T-Shirt", 25.00, "100% Cotton, comfortable fit", "Cotton"));
        myStore.addProductToStore(new ProductA("Mouse", 30.00, "Wireless ergonomic mouse", "Accessories"));
        myStore.addProductToStore(new ProductB("Headphones", 150.00, "Noise-cancelling over-ear headphones", "AudioTech"));

        int choice;
        do {
            System.out.println("\n--- Store Menu ---");
            System.out.println("1. Display Products");
            System.out.println("2. Add Item to Order");
            System.out.println("3. View Current Order");
            System.out.println("4. Finalize Order");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    myStore.displayProducts();
                    break;
                case 2:
                    myStore.displayProducts(); 
                    System.out.print("Enter Product ID to add: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                        System.out.print("Enter Product ID to add: ");
                    }
                    int productId = scanner.nextInt();
                    System.out.print("Enter Quantity: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next();
                        System.out.print("Enter Quantity: ");
                    }
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); 
                    myStore.addOrderItem(productId, quantity);
                    break;
                case 3:
                    myStore.printOrder();
                    break;
                case 4:
                    myStore.finalizeOrder();
                    break;
                case 0:
                    System.out.println("Exiting store. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
