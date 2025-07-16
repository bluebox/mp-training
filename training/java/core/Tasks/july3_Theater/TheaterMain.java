package corejava.july3_Theater;

import java.util.Scanner;

public class TheaterMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Theater theatre = new Theater("Majestic", 3, 15);
        theatre.printSeatMap();
        int choice;
        Book_My_Show[] option=Book_My_Show.values();
        do {
            System.out.println("========= Menu =========");
            System.out.println("1. View seat map");
            System.out.println("2. Reserve a seat");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            choice = sc.nextInt();

            switch (option[choice-1]) {
                case PRINT_SEATS:
                    theatre.printSeatMap();
                    break;
                case BOOK_A_SEAT:
                    System.out.print("Enter seat ID to reserve (e.g., A003): ");
                    String seatId = sc.next().toUpperCase();
                    theatre.reserveSeat(seatId);
                    break;
                case QUIT:
                    System.out.println("Thank You!");
                    sc.close();
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }while(choice!=3);
        sc.close();
    }
}


