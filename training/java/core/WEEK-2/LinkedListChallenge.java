import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
class Place{
    private String town;
    private int distance;

    public Place(String town, int distance) {
        this.town = town;
        this.distance = distance;
    }

    public Place() {
        this("Sydney", 0);
    }

    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }
    public int getDistance() {
        return distance;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    @Override
    public String toString() {
        return "Place{" +
                "town='" + town + '\'' +
                ", distance=" + distance +
                '}';
    }
}
public class LinkedListChallenge {
    public static void printMenu(){
        System.out.println("=======Available Actions : ========\n" +
                "F - Forward\n" +
                "B - Backward\n" +
                "L - List Places\n" +
                "M - Menu\n" +
                "Q - Quit\n");
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        LinkedList<Place> list = new LinkedList<>();
        list.addLast(new Place());
        list.addLast(new Place("Melbourne", 877));
        list.addLast(new Place("Brisbane", 917));
        list.addLast(new Place("Adelaide", 1374));
        list.addLast(new Place("Alice Springs", 2771));
        list.addLast(new Place("Perth", 3923));
        list.addLast(new Place("Darwin", 3972));
        printMenu();
        ListIterator<Place> it=list.listIterator();
        while (true) {
            System.out.print("Enter your choice : ");
            String choice=sc.next();
            switch (choice) {
                case "F":
                    if (it.hasNext()) {
                        Place p=it.next();
                        System.out.println(p);
                    }
                    else{
                        System.out.println("Cannot Move further Forward...");
                    }
                    System.out.println();
                    break;
                case "B":
                    if (it.hasPrevious()) {
                        Place p=it.previous();
                        System.out.println(p);
                    }
                    else{
                        System.out.println("Cannot Move further Backward...");
                    }
                    System.out.println();
                    break;
                case "L":
                    for (Place place : list) {
                        System.out.println(place);
                    }
                    System.out.println();
                    break;
                case "M":
                    printMenu();
                    System.out.println();
                    break;
                case "Q":
                    System.out.println("Quitting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Enter valid choice, Click M for Menu...");
                    break;
            }
        }
    }
}
