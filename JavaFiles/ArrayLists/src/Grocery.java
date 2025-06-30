import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Grocery {
    private static ArrayList<String> groceryList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("\nChoose an option: ");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Print grocery list");
            System.out.println("4. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item to add: ");
                    String addItem = scanner.nextLine();
                    addItem(addItem);
                    break;
                case 2:
                    System.out.print("Enter item to remove: ");
                    String removeItem = scanner.nextLine();
                    removeItem(removeItem);
                    break;
                case 3:
                    printGroceryList();
                    break;
                case 4:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private static void addItem(String item) {
        if (groceryList.contains(item.toLowerCase())) {
            System.out.println(item + " is already in the list.");
        } else {
            groceryList.add(item.toLowerCase());
            System.out.println(item + " added.");
            printGroceryList();
        }
    }

    private static void removeItem(String item) {
        if (groceryList.contains(item.toLowerCase())) {
            groceryList.remove(item.toLowerCase());
            System.out.println(item + " removed.");
            printGroceryList();
        } else {
            System.out.println(item + " is not in the list.");
        }
    }

    private static void printGroceryList() {
        Collections.sort(groceryList);
        System.out.println("Your grocery list:");
        if(groceryList.isEmpty()) {
        	System.out.println("List is Empty");
        }else {
        	
        for (String item : groceryList) {
            System.out.println("- " + item);
        }
        }
    }
}

