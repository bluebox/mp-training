package Theatrechallange;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("Niharika", 5, 10);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Niharika's Theatre!");
        System.out.println("Type a seat label to book  A01, B05, Type 'exit' to quit.");

        while (true) {
            theatre.printSeatMap();
            System.out.print("\nEnter seat to reserve: ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("EXIT")) {
                System.out.println("Thank you........");
                break;
            }

            boolean reserved = theatre.reserveSeat(input);
            if (reserved) {
                System.out.println("Seat " + input + " reserved successfully.");
            } else {
                System.out.println("Seat " + input + " is not available or invalid.");
            }
        }

    }
}
