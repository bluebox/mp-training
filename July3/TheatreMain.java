package July3;

import java.util.Scanner;

public class TheatreMain {
	public static void main(String[] args) {
        Theatre theatre = new Theatre("Sahithi", 26, 10);
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Theatre!");
        System.out.println("Type a seat label to book  A001, B005, Type 'exit' to quit.");

        while (true) {
            theatre.printSeatMap();
            System.out.print("\nEnter seat to reserve: ");
            String input = sc.nextLine().trim().toUpperCase();

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
