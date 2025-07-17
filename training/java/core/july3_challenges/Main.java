package july_3Challenges;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("Grand Theatre", 3, 30); 

        theatre.printSeatMap();
        Scanner sc=new Scanner(System.in);
        System.out.println("enter seat number");
        String s=sc.next();
        boolean success = theatre.reserveSeat(s);
        System.out.println(success ? "Reserved!" : "Already reserved or not found.");

        System.out.println("\nUpdated seat map:");
        theatre.printSeatMap();
    }
}
