package Challenges.GymManagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Gym Management System ---");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Member ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    if(age < 0){
                        System.out.println("Age should be a positive number and above zero");
                        break;
                    }
                    gym.addMember(id, name, age);
                    break;
                case 2:
                    System.out.print("Enter Member ID to assign plan: ");
                    int memId = scanner.nextInt();
                    System.out.println("Available Plans:");
                    gym.showAvailablePlans();
                    System.out.print("Enter Plan Index: ");
                    int planIndex = scanner.nextInt();
                    gym.assignPlan(memId, planIndex);
                    break;
                case 3:
                    gym.viewAllMembers();
                    break;
                case 4:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        scanner.close();
    }
}

