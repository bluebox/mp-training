package com.day4_;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Distance_Linked_LIst {

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
            return String.format("%s (%d)", name, distance);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Place other = (Place) obj;
            return name.equalsIgnoreCase(other.name) && distance == other.distance;
        }
    }

    public static void main(String[] args) {

        LinkedList<Place> placesToVisit = new LinkedList<>();

        Place hyderabad = new Place("Miyapur", 15);
        addPlace(placesToVisit, hyderabad);
        addPlace(placesToVisit, new Place("Uppal", 27));
        addPlace(placesToVisit, new Place("Warangal", 130));
        addPlace(placesToVisit, new Place("Khammam", 160));
        addPlace(placesToVisit, new Place("Vijayawada", 300));
        addPlace(placesToVisit, new Place("Vishakapatnam", 875));

        placesToVisit.addFirst(new Place("Hyderabad", 0));

        ListIterator<Place> iterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);
        boolean quitLoop = false;
        boolean forward = true;

        printMenu();

        while (!quitLoop) {
            if (!iterator.hasPrevious()) {
                System.out.println("Originating : " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("Final : " + iterator.previous());
                forward = false;
            }

            System.out.print("Enter Value: ");
            String menuItem = scanner.nextLine().toUpperCase().substring(0, 1);

            switch (menuItem) {
                case "F":
                    System.out.println("User wants to go forward");
                    if (!forward) {
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next();
                        }
                    }

                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                    break;

                case "B":
                    System.out.println("User wants to go backwards");
                    if (forward) {
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous();
                        }
                    }

                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    }
                    break;

                case "M":
                    printMenu();
                    break;

                case "L":
                    System.out.println(placesToVisit);
                    break;

                default:
                    quitLoop = true;
                    break;
            }
        }

        scanner.close();
    }

    private static void addPlace(LinkedList<Place> list, Place place) {
        if (list.contains(place)) {
            System.out.println("Found duplicate: " + place);
            return;
        }

        for (Place p : list) {
            if (p.getName().equalsIgnoreCase(place.getName())) {
                System.out.println("Found duplicate: " + place);
                return;
            }
        }

        int matchedIndex = 0;
        for (Place p : list) {
            if (place.getDistance() < p.getDistance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }

        list.add(place);
    }

    private static void printMenu() {
    	System.out.println("!!!You can Select the Word or a Letter!!!");
        System.out.println("Available actions");
        System.out.println("(F)orward");
        System.out.println("(B)ackwards");
        System.out.println("(L)ist Places");
        System.out.println("(M)enu");
        System.out.println("(Q)uit");
    }
}