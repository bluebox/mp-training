package challenges_30th_june.GroceryListManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GroceryList groceryList = new GroceryList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 0 -> {
                    System.out.println("Shutting down...");
                    running = false;
                }
                case 1 -> {
                    System.out.print("Enter items to add (comma separated): ");
                    String input = scanner.nextLine();
                    groceryList.addItems(input);
                }
                case 2 -> {
                    System.out.print("Enter items to remove (comma separated): ");
                    String input = scanner.nextLine();
                    groceryList.removeItems(input);
                }
                default -> System.out.println("Invalid choice. Please enter 0, 1, or 2.");
            }
        }

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\nAvailable actions:");
        System.out.println("0 - to shutdown");
        System.out.println("1 - to add item(s) to list (comma delimited)");
        System.out.println("2 - to remove item(s) (comma delimited)");
    }
}

