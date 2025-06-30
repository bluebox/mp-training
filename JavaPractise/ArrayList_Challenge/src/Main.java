import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main{

    private static ArrayList<String> grocList = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = false;

        while (!running) {
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Shutting down...");
                    running = true;
                    break;
                case 1:
                    System.out.print("Enter items to add (comma-separated): ");
                    String string_input = scanner.nextLine();
                    String[] items = string_input.split(",");
                    for (String item : items) {
                        addItem(item.trim().toLowerCase());
                    }
                    printList();
                    break;
                case 2:
                    System.out.print("Enter items to remove (comma-separated): ");
                    String remove_line = scanner.nextLine();
                    String[] removeItems = remove_line.split(",");
                    for (String item : removeItems) {
                        removeItem(item.trim().toLowerCase());
                    }
                    printList();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 0, 1, or 2.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Available actions:");
        System.out.println("0 - to shutdown");
        System.out.println("1 - to add items to list (comma delimited)");
        System.out.println("2 - to remove item (comma delimited)");
    }

    private static void addItem(String item) {
        if (!grocList.contains(item)) {
            grocList.add(item);
            System.out.println("Added: " + item);
        } else {
            System.out.println("Item already in list: " + item);
        }
    }

    private static void removeItem(String item) {
        if (grocList.contains(item)) {
            grocList.remove(item);
            System.out.println("Removed: " + item);
        } else {
            System.out.println("Item not found in list: " + item);
        }
    }

    private static void printList() {
        Collections.sort(grocList);
        System.out.println("Current Grocery List (sorted):");
        for (int i = 0; i < grocList.size(); i++) {
            System.out.println((i + 1) + ". " + grocList.get(i));
        }
    }
}