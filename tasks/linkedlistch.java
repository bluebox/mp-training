import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

class Town {
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Town other = (Town) obj;
        return name.equalsIgnoreCase(other.name);
    }
}

public class linkedlistch {
    private static LinkedList<Town> ll = new LinkedList<>();

    public static void addTownInOrder(Town newt) {
        for (Town t : ll) {
            if (t.equals(newt)) {
                System.out.println(newt.getName() + " is already there");
                return;
            }
        }

        for (Town t : ll) {
            if (newt.getDistance() == t.getDistance()) {
                System.out.println("Town at distance " + t.getDistance() + " already exists.");
                break;
            }
        }

        ll.add(newt);
        System.out.println(newt.getName() + " added.");
    }

    public static void printMenu() {
        System.out.println("\nAvailable actions:");
        System.out.println("(F)orward");
        System.out.println("(B)ackward");
        System.out.println("(L)ist Places");
        System.out.println("(M)enu");
        System.out.println("(Q)uit");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        addTownInOrder(new Town("sydney", 0));
        addTownInOrder(new Town("Adelaide", 1374));
        addTownInOrder(new Town("Alice springs", 2771));
        addTownInOrder(new Town("brisbane", 917));
        addTownInOrder(new Town("Darwin", 3972));
        addTownInOrder(new Town("melbourne", 877));
        addTownInOrder(new Town("perth", 917));

        printMenu();

        boolean quit = false;
        boolean movingForward = true;
        ListIterator<Town> iterator = ll.listIterator();

        while (!quit) {
            System.out.print("\nEnter option: ");
            String option = sc.nextLine();

            switch (option) {
                case "F":
                    if (!movingForward && iterator.hasNext()) {
                        iterator.next();
                    }
                    movingForward = true;
                    if (iterator.hasNext()) {
                        Town nextTown = iterator.next();
                        System.out.println("Now visiting: " + nextTown.getName());
                    } else {
                        System.out.println("Reached the end of the list.");
                    }
                    break;

                case "B":
                    if (movingForward && iterator.hasPrevious()) {
                        iterator.previous();
                    }
                    movingForward = false;
                    if (iterator.hasPrevious()) {
                        Town prevTown = iterator.previous();
                        System.out.println("Now visiting: " + prevTown.getName());
                    } else {
                        System.out.println("We are at the start of the list.");
                    }
                    break;

                case "L":
                    System.out.println("List of towns:");
                    for (Town t : ll) {
                        System.out.println(t.getName() + " (" + t.getDistance() + " km)");
                    }
                    break;

                case "M":
                    printMenu();
                    break;

                case "Q":
                    System.out.println("Exiting... Goodbye!");
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid option. Type 'M' to see menu.");
                    break;
            }
        }

        sc.close();
    }
}
