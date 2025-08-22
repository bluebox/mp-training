import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListChal {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            System.out.println("Available actions:");
            System.out.println("0 - to shutdown");
            System.out.println("1 - to add item(s) to list (comma delimited list)");
            System.out.println("2 - to remove any items (comma delimited list)");
            System.out.print("Enter a number for which action you want to do: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Shutting down...");
                    return;
                case 1:
                    System.out.print("Enter item(s) to add (comma delimited list): ");
                    String[] itemsToAdd = scanner.nextLine().split(",");
                    for (String item : itemsToAdd) {
                        list.add(item.trim());
                    }
                    System.out.println("List: " + list);
                    break;
                case 2:
                    System.out.print("Enter item(s) to remove (comma delimited list): ");
                    String[] itemsToRemove = scanner.nextLine().split(",");
                    for (String item : itemsToRemove) {
                        list.remove(item.trim());
                    }
                    System.out.println("List: " + list);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}