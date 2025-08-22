import java.util.*;

public class ItineraryApp {
    private static LinkedList<Place> places = new LinkedList<>();

    public static void main(String[] args) {
        addPlace(new Place("Sydney", 0));
        addPlace(new Place("Melbourne", 877));
        addPlace(new Place("Brisbane", 917));
        addPlace(new Place("Adelaide", 1374));
        addPlace(new Place("Alice Springs", 2771));
        addPlace(new Place("Perth", 3923));
        addPlace(new Place("Darwin", 3972));

        runItinerary();
    }

    private static void addPlace(Place newPlace) {
        ListIterator<Place> iterator = places.listIterator();

        while (iterator.hasNext()) {
            Place current = iterator.next();
            if (current.equals(newPlace)) {
                System.out.println(newPlace.getName() + " is already in the list.");
                return;
            }
            if (newPlace.getDistance() < current.getDistance()) {
                iterator.previous();
                iterator.add(newPlace);
                return;
            }
        }

        iterator.add(newPlace);
    }
    private static void runItinerary() {
        Scanner scanner = new Scanner(System.in);
        ListIterator<Place> iterator = places.listIterator();
        boolean goingForward = true;
        printMenu();

        while (true) {
            System.out.print("Enter action: ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "F":
                case "FORWARD":
                    if (!goingForward && iterator.hasNext()) iterator.next();
                    if (iterator.hasNext()) {
                        System.out.println("Now visiting → " + iterator.next());
                        goingForward = true;
                    } else {
                        System.out.println("You're at the end of the itinerary.");
                    }
                    break;

                case "B":
                case "BACKWARD":
                    if (goingForward && iterator.hasPrevious()) iterator.previous();
                    if (iterator.hasPrevious()) {
                        System.out.println("Now visiting ← " + iterator.previous());
                        goingForward = false;
                    } else {
                        System.out.println("You're at the beginning of the itinerary.");
                    }
                    break;

                case "L":
                case "LIST":
                    printPlaces();
                    break;

                case "M":
                case "MENU":
                    printMenu();
                    break;

                case "Q":
                case "QUIT":
                    System.out.println("Exiting itinerary. Safe travels!");
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
        Available actions:
        (F)orward
        (B)ackward
        (L)ist Places
        (M)enu
        (Q)uit
        """);
    }

    private static void printPlaces() {
        System.out.println("=== Itinerary ===");
        for (Place place : places) {
            System.out.println("• " + place);
        }
    }
}