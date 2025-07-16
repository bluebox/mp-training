package corejava.july1_InterfaceClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Mappable> mappableItems = new ArrayList<>();

        System.out.println("Welcome to The Interface Challenge!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add a Building");
            System.out.println("2. Add a Utility Line");
            System.out.println("3. View all Mappable Items");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

          
            int  choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            

            switch (choice) {
                case 1:
                    System.out.println("\n--- Adding a Building ---");
                    Building newBuilding = new Building(); 
                    mappableItems.add(newBuilding);
                    System.out.println("Building added successfully!");
                    break;
                case 2:
                    System.out.println("\n--- Adding a Utility Line ---");
                    UtilityLine newUtilityLine = new UtilityLine(); 
                    mappableItems.add(newUtilityLine);
                    System.out.println("Utility Line added successfully!");
                    break;
                case 3:
                    System.out.println("\n--- All Mappable Items ---");
                    if (mappableItems.isEmpty()) {
                        System.out.println("No items to display yet.");
                    } else {
                        for (Mappable item : mappableItems) {
                            Mappable.printProperties(item); 
                            System.out.println("Outputting the geometry type, the icon information, and the label:");
                            System.out.println("  Geometry Type: " + item.getGeometryType());
                            System.out.println("  Icon: " + item.getIconType());
                            System.out.println("  Label: " + item.getLabel());
                            System.out.println("JSON Representation:");
                            System.out.println(item.toJSON());
                            System.out.println("=====================================");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
