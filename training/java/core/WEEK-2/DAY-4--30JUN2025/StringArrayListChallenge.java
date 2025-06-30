import java.util.ArrayList;
import java.util.Scanner;
public class StringArrayListChallenge {
    public static void main(String[] args) {
        int choice;
        ArrayList<String> groceryList = new ArrayList<>();
        System.out.println("1. Add item to grocery list\n2. Remove item from grocery list\n3. Exit");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter your choice: ");
            try{
                choice = scanner.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }
            scanner.nextLine();  // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter item to add: ");
                    String itemToAdd = scanner.nextLine();
                    if (groceryList.contains(itemToAdd)) {
                        System.out.println("Item already exists in the list.");
                        continue;
                    }
                    groceryList.add(itemToAdd);
                    System.out.println("Item added.");
                    groceryList.sort(String::compareToIgnoreCase);
                    for (String item : groceryList) {
                        System.out.println("- " + item);
                    }
                    break;
                case 2:
                    System.out.print("Enter item to remove: ");
                    String itemToRemove = scanner.nextLine();
                    if (!groceryList.contains(itemToRemove)) {
                        System.out.println("Item not found in the list.");
                        continue;
                    }
                    groceryList.remove(itemToRemove);
                    System.out.println("Item removed.");
                    groceryList.sort(String::compareToIgnoreCase);
                    for (String item : groceryList) {
                        System.out.println("- " + item);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}