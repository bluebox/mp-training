import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class LinkedPlaces{
	public record Town(String name, int distanceFromSydney) {}

    public static void main(String[] args) {
    	
        LinkedList<Town> itinerary = new LinkedList<>();
        
        
        addInOrder(itinerary, new Town("Sydney", 0));
        addInOrder(itinerary, new Town("Adelaide", 1374));
        addInOrder(itinerary, new Town("Alice Springs", 2771));
        addInOrder(itinerary, new Town("Brisbane", 917));
        addInOrder(itinerary, new Town("Darwin", 3972));
        addInOrder(itinerary, new Town("Melbourne", 877));
        addInOrder(itinerary, new Town("Perth", 3923));

        printItinerary(itinerary);
        menu(itinerary);
    }

    private static void addInOrder(LinkedList<Town> itinerary, Town newTown) {
        ListIterator<Town> iterator = itinerary.listIterator();

        while (iterator.hasNext()) {
            Town current = iterator.next();
            if (current.name().equalsIgnoreCase(newTown.name())) {
                System.out.println(newTown.name() + " is already included.");
                return;
            }
            if (current.distanceFromSydney() > newTown.distanceFromSydney()) {
                iterator.previous();
                iterator.add(newTown);
                return;
            }
        }

        iterator.add(newTown);
    }

    private static void printItinerary(LinkedList<Town> itinerary) {
        System.out.println("\nItinerary:");
        for (Town town : itinerary) {
            System.out.println(" - " + town.name() + " (" + town.distanceFromSydney() + " km)");
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

    private static void menu(LinkedList<Town> itinerary) {
        Scanner scanner = new Scanner(System.in);
        ListIterator<Town> iterator = itinerary.listIterator();
        boolean quit = false;
        boolean goingForward = true;

        if (itinerary.isEmpty()) {
            System.out.println("No places in itinerary.");
            return;
        } else {
            System.out.println("Starting at: " + iterator.next());
            printMenu();
        }

        while (!quit) {
            System.out.print("Enter action: ");
            String action = scanner.nextLine().toUpperCase();

            switch (action) {
                case "F" -> {
                    if (!goingForward) {
                        if (iterator.hasNext()) 
                        	iterator.next();
                        
                        goingForward = true;
                    }
                    if (iterator.hasNext()) {
                        System.out.println("Visiting: " + iterator.next());
                    } else {
                        System.out.println("Reached the end of the itinerary.");
                        goingForward = false;
                    }
                }
                case "B" -> {
                    if (goingForward) {
                        if (iterator.hasPrevious()) 
                        	iterator.previous();
                        	goingForward = false;
                    }
                    if (iterator.hasPrevious()) {
                        System.out.println("Visiting: " + iterator.previous());
                    } else {
                        System.out.println("At the start of the itinerary.");
                        goingForward = true;
                    }
                }
                case "L" -> printItinerary(itinerary);
                case "M" -> printMenu();
                case "Q" -> {
                    System.out.println("Trip ended.");
                    quit = true;
                }
                default -> System.out.println("Invalid action. Try again.");
            }
        }

    }

}
