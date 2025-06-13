package CaseStudy_Gym;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
        Gym gym = new Gym();
        Scanner scanner = new Scanner(System.in);
        
        boolean willRun = true;

        while (willRun) {
            System.out.println("\n---- Gym Membership Management System ----");
            System.out.println("1. Add New Gym Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(scanner.nextLine());
                        Member member = new Member(id, name, age);
                        gym.addMember(member);
                        System.out.println("Member added successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();
                        System.out.println("Available Plans:");
                        gym.showAvailablePlans();
                        System.out.print("Enter Plan Index: ");
                        int index = Integer.parseInt(scanner.nextLine());
                        gym.assignPlan(memberId, index);
                        break;

                    case 3:
                        gym.showAllMembers();
                        break;

                    case 4:
                        System.out.println("Exiting system. Goodbye!");
                        willRun = false;
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers where required.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
