package challenges_30th_june;

import java.util.*;

public class ItineraryManager {

    private static LinkedList<Place> placesToVisit = new LinkedList<>();

    public static void main(String[] args) {
        addPlace(new Place("Sydney", 0));
        addPlace(new Place("Melbourne", 877));
        addPlace(new Place("Brisbane", 917));
        addPlace(new Place("Adelaide", 1374));
        addPlace(new Place("Alice Springs", 2771));
        addPlace(new Place("Perth", 3923));
        addPlace(new Place("Darwin", 3972));

        Scanner scanner = new Scanner(System.in);
        ListIterator<Place> iterator = placesToVisit.listIterator();
        boolean quit = false;
        boolean goingForward = true;

        printMenu();

        if (!placesToVisit.isEmpty()) {
            System.out.println("Now visiting: " + iterator.next());
        }

        while (!quit) {
            System.out.print("\nEnter action: ");
            String action = scanner.nextLine().toUpperCase();

            switch (action) {
                case "F":
                case "FORWARD":
                    if (!goingForward) {
                        if (iterator.hasNext()) iterator.next(); // fix direction
                        goingForward = true;
                    }
                    if (iterator.hasNext()) {
                        System.out.println("Now visiting: " + iterator.next());
                    } else {
                        System.out.println("Reached the end of the list.");
                    }
                    break;

                case "B":
                case "BACKWARD":
                    if (goingForward) {
                        if (iterator.hasPrevious()) iterator.previous(); // fix direction
                        goingForward = false;
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println("Now visiting: " + iterator.previous());
                    } else {
                        System.out.println("We are at the start of the list.");
                    }
                    break;

                case "L":
                case "LIST":
                    printList();
                    break;

                case "M":
                case "MENU":
                    printMenu();
                    break;

                case "Q":
                case "QUIT":
                    System.out.println("Exiting itinerary...");
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid option. Press M to see the menu.");
            }
        }

        scanner.close();
    }

    // Adds a place in order based on distance (no duplicates allowed)
    private static void addPlace(Place place) {
        ListIterator<Place> iterator = placesToVisit.listIterator();
        while (iterator.hasNext()) {
            Place current = iterator.next();
            if (current.getName().equalsIgnoreCase(place.getName())) {
                // Duplicate place
                System.out.println(place.getName() + " already exists, not added.");
                return;
            } else if (current.getDistance() > place.getDistance()) {
                iterator.previous();
                iterator.add(place);
                return;
            }
        }
        iterator.add(place);
    }

    private static void printMenu() {
        System.out.println("\nAvailable actions (select word or letter):");
        System.out.println("(F)orward");
        System.out.println("(B)ackward");
        System.out.println("(L)ist Places");
        System.out.println("(M)enu");
        System.out.println("(Q)uit");
    }

    private static void printList() {
        System.out.println("\nPlaces in itinerary:");
        for (Place place : placesToVisit) {
            System.out.println(place);
        }
    }

    // Nested static class to represent a place
    static class Place {
        private final String name;
        private final int distance; // from Sydney

        public Place(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }

        public String getName() {
            return name;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return name + " (" + distance + " km)";
        }
    }
}

