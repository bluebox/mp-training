package com.prana;

import java.util.Scanner;

public class MainSeatArr {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("Drama Palace", 5, 10);
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println(" Welcome to Drama Theatre ");
        while (true) {
            System.out.println("\nHere’s the current seat map:");
            theatre.printSeatMap();
            System.out.print("\nEnter seat to book (e.g., A005), or type 'exit' to quit: ");
            input = scanner.nextLine().toUpperCase();
            if (input.equals("EXIT")) {
                System.out.println(" Thank you for visiting Drama Palace!");
                break;
            }
            if (!input.matches("[A-Z]\\d{3}")) {
                System.out.println(" Invalid format. Please enter like A005.");
                continue;
            }
            boolean booked = theatre.reserveSeat(input);
            if (booked) {
                System.out.println(" Seat " + input + " successfully booked!");
            } else {
                System.out.println(" Seat " + input + " is already booked.");
                System.out.println(" Here are the available seats in that row:");
                theatre.showAvailableInRow(input.charAt(0));
            }
        }
        scanner.close();
    }
}


