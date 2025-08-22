package linkedlistchallenge;

import java.util.LinkedList;
import java.util.ListIterator;

public class ItineraryManager {
    private LinkedList<Place> places;
    private ListIterator<Place> iterator;

    public ItineraryManager() {
        places = new LinkedList<>();
    }

    public void addPlace(Place newPlace) {
        for (Place place : places) {
            if (place.getName().equalsIgnoreCase(newPlace.getName())) {
                System.out.println("Duplicate place not added: " + newPlace.getName());
                return;
            }
        }
        places.add(newPlace);
        sortPlaces();
    }

    private void sortPlaces() {
        places.sort((p1, p2) -> Integer.compare(p1.getDistanceFromHyderabad(), p2.getDistanceFromHyderabad()));
    }

    public void start() {
        iterator = places.listIterator();
        if (places.isEmpty()) {
            System.out.println("No places in the itinerary.");
        } else {
            System.out.println("Itinerary ready. Starting point: " + places.getFirst());
        }
    }

    public void moveForward() {
        if (iterator.hasNext()) {
            System.out.println("Next: " + iterator.next());
        } else {
            System.out.println("You are at the end of the list.");
        }
    }

    public void moveBackward() {
        if (iterator.hasPrevious()) {
            System.out.println("Previous: " + iterator.previous());
        } else {
            System.out.println("You are at the beginning of the list.");
        }
    }

    public void listPlaces() {
        System.out.println("\nItinerary:");
        for (Place place : places) {
            System.out.println(" - " + place);
        }
    }

    public void printMenu() {
        System.out.println("\nAvailable actions:");
        System.out.println("(F)orward - Go to next place");
        System.out.println("(B)ackward - Go to previous place");
        System.out.println("(L)ist Places - Show all places in itinerary");
        System.out.println("(M)enu - Show menu options");
        System.out.println("(Q)uit - Exit the program");
    }
}
