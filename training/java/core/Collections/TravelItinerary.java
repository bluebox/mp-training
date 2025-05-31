package Collections;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class TravelItinerary {

    static class Place {
        private String name;
        private int distance;

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

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Place place = (Place) obj;
            return name.equalsIgnoreCase(place.name);
        }
    }

    public static void main(String[] args) {
        LinkedList<Place> places = new LinkedList<>();
        addPlace(places, new Place("Sydney", 0));
        addPlace(places, new Place("Adelaide", 1374));
        addPlace(places, new Place("Alice Springs", 2771));
        addPlace(places, new Place("Brisbane", 917));
        addPlace(places, new Place("Darwin", 3972));
        addPlace(places, new Place("Melbourne", 877));
        addPlace(places, new Place("Perth", 3923));

        Scanner scanner = new Scanner(System.in);
        ListIterator<Place> iterator = places.listIterator();
        boolean quit = false;
        boolean goingForward = true;

        printMenu();

        while (!quit) {
            System.out.print("Enter action: ");
            String action = scanner.nextLine().trim().toLowerCase();

            switch (action) {
                case "f":
                case "forward":
                    if (!goingForward) {
                        if (iterator.hasNext()) iterator.next();
                        goingForward = true;
                    }
                    if (iterator.hasNext()) {
                        System.out.println("Now visiting: " + iterator.next());
                    } else {
                        System.out.println("Reached the end of the list.");
                        goingForward = false;
                    }
                    break;

                case "b":
                case "backward":
                    if (goingForward) {
                        if (iterator.hasPrevious()) iterator.previous();
                        goingForward = false;
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println("Now visiting: " + iterator.previous());
                    } else {
                        System.out.println("We are at the start of the list.");
                        goingForward = true;
                    }
                    break;

                case "l":
                case "list":
                    listPlaces(places);
                    break;

                case "m":
                case "menu":
                    printMenu();
                    break;

                case "0":
                case "quit":
                    quit = true;
                    System.out.println("Exiting itinerary.");
                    break;

                default:
                    System.out.println("Invalid option. Type 'm' for menu.");
                    break;
            }
        }

        scanner.close();
    }

    private static void addPlace(LinkedList<Place> list, Place newPlace) {
        ListIterator<Place> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Place current = iterator.next();
            if (current.equals(newPlace)) return;
            if (newPlace.getDistance() < current.getDistance()) {
                iterator.previous();
                iterator.add(newPlace);
                return;
            }
        }
        list.add(newPlace);
    }

    private static void listPlaces(LinkedList<Place> list) {
        for (Place place : list) {
            System.out.println(place);
        }
    }

    private static void printMenu() {
        System.out.println("Available actions (select word or letter):");
        System.out.println("(F)orward");
        System.out.println("(B)ackward");
        System.out.println("(L)ist Places");
        System.out.println("(M)enu");
        System.out.println("(0)uit");
    }
}

