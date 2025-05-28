package Collections;
import java.util.*;

public class LinkedListChallenge {

    static class Town {
        private String name;
        private int distance;

        public Town(String name, int distance) {
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

    public static void main(String[] args) {
        LinkedList<Town> itinerary = new LinkedList<>();

        // Add towns ordered by distance
        addInOrder(itinerary, new Town("Adelaide", 1374));
        addInOrder(itinerary, new Town("Alice Springs", 2771));
        addInOrder(itinerary, new Town("Brisbane", 917));
        addInOrder(itinerary, new Town("Darwin", 3972));
        addInOrder(itinerary, new Town("Melbourne", 877));
        addInOrder(itinerary, new Town("Perth", 3923));

        runMenu(itinerary);
    }

    private static void addInOrder(LinkedList<Town> list, Town newTown) {
        ListIterator<Town> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getDistance() > newTown.getDistance()) {
                iterator.previous();
                iterator.add(newTown);
                return;
            }
        }
        list.add(newTown); // add at end
    }

    private static void runMenu(LinkedList<Town> itinerary) {
        Scanner scanner = new Scanner(System.in);
        ListIterator<Town> iterator = itinerary.listIterator();
        boolean goingForward = true;

        printMenu();

        if (itinerary.isEmpty()) {
            System.out.println("No towns in the itinerary.");
            return;
        } else {
            System.out.println("Currently visiting: " + iterator.next());
        }

        boolean quit = false;
        while (!quit) {
            System.out.print("Enter action: ");
            String action = scanner.nextLine().toUpperCase();

            switch (action) {
                case "F":
                case "FORWARD":
                    if (!goingForward) {
                        if (iterator.hasNext()) iterator.next();
                        goingForward = true;
                    }
                    if (iterator.hasNext()) {
                        System.out.println("Currently visiting: " + iterator.next());
                    } else {
                        System.out.println("Reached the end of the itinerary.");
                        goingForward = false;
                    }
                    break;

                case "B":
                case "BACKWARD":
                    if (goingForward) {
                        if (iterator.hasPrevious()) iterator.previous();
                        goingForward = false;
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println("Currently visiting: " + iterator.previous());
                    } else {
                        System.out.println("We are at the start of the itinerary.");
                        goingForward = true;
                    }
                    break;

                case "L":
                case "LIST":
                    printItinerary(itinerary);
                    break;

                case "M":
                case "MENU":
                    printMenu();
                    break;

                case "Q":
                case "QUIT":
                    quit = true;
                    System.out.println("Exiting itinerary navigation.");
                    break;

                default:
                    System.out.println("Invalid action. Type M to show menu.");
            }
        }

        scanner.close();
    }

    private static void printItinerary(LinkedList<Town> itinerary) {
        System.out.println("Itinerary:");
        for (Town town : itinerary) {
            System.out.println(town);
        }
    }

    private static void printMenu() {
        System.out.println("\nAvailable actions (select word or letter):");
        System.out.println("(F)orward");
        System.out.println("(B)ackward");
        System.out.println("(L)ist Places");
        System.out.println("(M)enu");
        System.out.println("(Q)uit\n");
    }
}

