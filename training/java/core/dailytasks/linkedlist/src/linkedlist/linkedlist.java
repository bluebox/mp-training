package linkedlist;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Collections; 


class Place implements Comparable<Place> {
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
    public int compareTo(Place other) {
        return Integer.compare(this.distance, other.distance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Place place = (Place) obj;
        return name.equalsIgnoreCase(place.name); 
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }
}

public class linkedlist {

    public static void main(String[] args) {
        LinkedList<Place> itinerary = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        
        addPlace(itinerary, new Place("Sydney", 0)); 
        addPlace(itinerary, new Place("Melbourne", 877));
        addPlace(itinerary, new Place("Brisbane", 917));
        addPlace(itinerary, new Place("Adelaide", 1374));
        addPlace(itinerary, new Place("Alice Springs", 2771));
        addPlace(itinerary, new Place("Perth", 3923));
        addPlace(itinerary, new Place("Darwin", 3972));
        
        addPlace(itinerary, new Place("Melbourne", 877));

        printMenu();

        ListIterator<Place> listIterator = itinerary.listIterator();
        boolean goingForward = true;
        boolean quit = false;

        if (itinerary.isEmpty()) {
            System.out.println("No places in the itinerary.");
            return;
        } else {
            System.out.println("Now visiting: " + listIterator.next());
        }

        while (!quit) {
            System.out.print("Enter your choice: ");
            String action = scanner.nextLine().toUpperCase();

            switch (action) {
                case "F":
                case "FORWARD":
                    if (!goingForward) {
                        if (listIterator.hasNext()) {
                            listIterator.next(); 
                        }
                        goingForward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now visiting: " + listIterator.next());
                    } else {
                        System.out.println("Reached the end of the itinerary.");
                        goingForward = false; 
                    }
                    break;
                case "B":
                case "BACKWARD":
                    if (goingForward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous(); // Adjust iterator direction
                        }
                        goingForward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now visiting: " + listIterator.previous());
                    } else {
                        System.out.println("Reached the start of the itinerary (Sydney).");
                        goingForward = true; 
                    }
                    break;
                case "L":
                case "LIST PLACES":
                    printPlaces(itinerary);
                    break;
                case "M":
                case "MENU":
                    printMenu();
                    break;
                case "Q":
                case "QUIT":
                    quit = true;
                    System.out.println("Exiting itinerary program.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose from the menu.");
                    break;
            }
        }

        
    }

  
    private static void addPlace(LinkedList<Place> itinerary, Place newPlace) {
       
        if (newPlace.getDistance() == 0 && newPlace.getName().equalsIgnoreCase("Sydney")) {
            if (itinerary.isEmpty() || !itinerary.getFirst().getName().equalsIgnoreCase("Sydney")) {
                itinerary.addFirst(newPlace);
            } else {
                System.out.println(newPlace.getName() + " already exists at the start of the itinerary.");
            }
            return;
        }

        ListIterator<Place> i = itinerary.listIterator();
        while (i.hasNext()) {
            Place currentPlace = i.next();
            
            if (currentPlace.equals(newPlace)) {
                System.out.println(newPlace.getName() + " is already in the itinerary. Not adding duplicate.");
                return;
            }
            
            if (newPlace.compareTo(currentPlace) < 0) {
                i.previous(); 
                i.add(newPlace);
                System.out.println("Added " + newPlace.getName() + " to the itinerary.");
                return;
            }
        }
        
        i.add(newPlace);
        System.out.println("Added " + newPlace.getName() + " to the itinerary.");
    }


    private static void printMenu() {
        System.out.println("\nAvailable actions (select word or letter):");
        System.out.println("(F)orward");
        System.out.println("(B)ackward");
        System.out.println("(L)ist Places");
        System.out.println("(M)enu");
        System.out.println("(Q)uit");
    }

    private static void printPlaces(LinkedList<Place> itinerary) {
        System.out.println("\n--- Itinerary ---");
        if (itinerary.isEmpty()) {
            System.out.println("The itinerary is empty.");
            return;
        }
        for (Place place : itinerary) {
            System.out.println(place);
        }
        System.out.println("-----------------\n");
    }
}