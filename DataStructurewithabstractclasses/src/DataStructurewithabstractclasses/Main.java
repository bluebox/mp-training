package DataStructurewithabstractclasses;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        NodeList list = null;

        // Step 1: Choose data structure
        System.out.println("Choose data structure:");
        System.out.println("1 - Linked List");
        System.out.println("2 - Search Tree");
        System.out.print("Enter choice: ");

        int dsChoice = Integer.parseInt(scanner.nextLine());

        if (dsChoice == 1) {
            list = new MyLinkedList(null);
            System.out.println("Linked List selected.");
        } else if (dsChoice == 2) {
            list = new SearchTree(null);
            System.out.println("Search Tree selected.");
        } else {
            System.out.println("Invalid choice. Exiting.");
            return;
        }

        // Step 2: Interactive menu for add/remove/traverse
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Display contents");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter item to add: ");
                    String addValue = scanner.nextLine();
                    boolean added = list.addItem(new Node(addValue));
                    if (added) {
                        System.out.println(addValue + " added.");
                    } else {
                        System.out.println(addValue + " already exists or could not be added.");
                    }
                    break;

                case 2:
                    System.out.print("Enter item to remove: ");
                    String removeValue = scanner.nextLine();
                    boolean removed = list.removeItem(new Node(removeValue));
                    if (removed) {
                        System.out.println(removeValue + " removed.");
                    } else {
                        System.out.println(removeValue + " not found.");
                    }
                    break;

                case 3:
                    System.out.println("Contents:");
                    System.out.print(list.traverse(list.getRoot()));
                    break;

                case 4:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
