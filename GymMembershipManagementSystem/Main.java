package GymMembershipManagementSystem;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		Gym gym = new Gym();
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        boolean exit = false;
        while(true) {
            System.out.println("\n Gym Membership Management System");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine());
                        gym.addMember(id, name, age);
                        break;

                    case 2:
                        System.out.print("Enter Member ID: ");
                        int mId = Integer.parseInt(sc.nextLine());
                        System.out.println("Available Plans:");
                        gym.listPlans();
                        System.out.print("Enter Plan Index (0/1/2): ");
                        int planIndex = Integer.parseInt(sc.nextLine());
                        gym.assignPlanToMember(mId, planIndex);
                        break;

                    case 3:
                        gym.viewAllMembers();
                        break;

                    case 4:
                        System.out.println("Exiting the system. Goodbye!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            if(exit) break;
        }
	}
}
