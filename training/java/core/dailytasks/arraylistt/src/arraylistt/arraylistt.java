package arraylistt;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class arraylistt {

    private static ArrayList<String> groceryList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        boolean quit = false;
        while (!quit) {
            printMenu();
            System.out.print("Enter a number for which action you want to do: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 0:
                    quit = true;
                    System.out.println("Shuting down...");
                    break;
                case 1:
                    addItems();
                    break;
                case 2:
                    removeItems();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 0, 1, or 2.");
                    break;
            }
            if (!quit) {
                printGroceryList();
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nAvailable options:");
        System.out.println("0 - to shutdown");
        System.out.println("1 - to add item(s) to list (comma delimited list)");
        System.out.println("2 - to remove any items (comma delimited list)");
    }

    private static void addItems() {
        System.out.print("Enter item(s) to add (comma delimited list): ");
        String itemsString = scanner.nextLine();
        String[] items = itemsString.split(",");

        for (String item : items) {
            String trimmedItem = item.trim();
            if (!trimmedItem.isEmpty()) {
                if (!groceryList.contains(trimmedItem)) {
                    groceryList.add(trimmedItem);
                } else {
                    System.out.println(trimmedItem + " is already in the list. Not adding duplicate.");
                }
            }
        }
        Collections.sort(groceryList); // Keep the list sorted
    }

    private static void removeItems() { 
        System.out.print("Enter item(s) to remove (comma delimited list): ");
        String itemsString = scanner.nextLine();
        String[] items = itemsString.split(",");

        for (String item : items) {
            String trimmedItem = item.trim();
            if (!trimmedItem.isEmpty()) {
                if (groceryList.contains(trimmedItem)) {
                    groceryList.remove(trimmedItem);
                } else {
                    System.out.println(trimmedItem + " is not in the list. Cannot remove.");
                }
            }
        }
        Collections.sort(groceryList); // Keep the list sorted
    }

    private static void printGroceryList() {
        if (groceryList.isEmpty()) {
            System.out.println("The grocery list is empty.");
            return;
        }
        System.out.println("\nYour current grocery list:");
        for (int i = 0; i < groceryList.size(); i++) {
            System.out.println((i + 1) + ". " + groceryList.get(i));
        }
    }
}
