package GymPackage;

import java.util.Scanner;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym();
        Scanner scanner = new Scanner(System.in);
        int choice=0;

        do {
            System.out.println("\n==== Gym Membership Management System ====");
            System.out.println("1. Add New Gym Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Update Plan of a member");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        GymService.addMember();
                        break;

                    case 2:
                        GymService.assignPlan();
                        break;

                    case 3:
                       GymService.getAllMembers();
                        break;
                    case 4:
                        GymService.updatePlan();
                        break;
                    case 5:
                        System.out.println("Exiting system. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 5);

    }
}

