package june30_collections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GroceryList {
    ArrayList<String> items = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public void addItems() {
        System.out.print("Enter item(s) to add (comma separated): ");
        String input = scanner.nextLine();
        String[] inputItems = input.split(",");

        for (String item : inputItems) {
            String trimmedItem = item.trim().toLowerCase();
            if (!items.contains(trimmedItem)) {
                items.add(trimmedItem);
                System.out.println(trimmedItem + " added.");
            } else {
                System.out.println(trimmedItem + " is already in the list.");
            }
        }
    }

    public void removeItems() {
        System.out.print("Enter item(s) to remove (comma separated): ");
        String input = scanner.nextLine();
        String[] inputItems = input.split(",");

        for (String item : inputItems) {
            String trimmedItem = item.trim().toLowerCase();
            if (items.contains(trimmedItem)) {
                items.remove(trimmedItem);
                System.out.println(trimmedItem + " removed.");
            } else {
                System.out.println(trimmedItem + " is not in the list.");
            }
        }
    }

    public void printSortedList() {
        Collections.sort(items);
        System.out.println("\nGrocery List:");
        if (items.isEmpty()) {
            System.out.println("[List is empty]");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }
}
