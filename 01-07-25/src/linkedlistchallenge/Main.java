package linkedlistchallenge;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ItineraryManager manager = new ItineraryManager();

        manager.addPlace(new Place("hanumakonda", 374));
        manager.addPlace(new Place("nalgonda", 771));
        manager.addPlace(new Place("medak", 17));
        manager.addPlace(new Place("medchal", 972));
        manager.addPlace(new Place("warangal", 77));
        manager.addPlace(new Place("karimnagar", 923));
        manager.addPlace(new Place("Hyderabad", 0)); 

        manager.start();
        manager.printMenu();

        Scanner scanner = new Scanner(System.in);
        boolean quit=false;

        while (!quit) {
            System.out.print("\nEnter action (F, B, L, M, Q): ");
            String action = scanner.nextLine().trim().toUpperCase();

            switch (action) {
                case "F":
                    manager.moveForward();
                    break;
                case "B":
                    manager.moveBackward();
                    break;
                case "L":
                    manager.listPlaces();
                    break;
                case "M":
                    manager.printMenu();
                    break;
                case "Q":
                    quit = true;
                    System.out.println("Exiting itinerary...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }
}
