package gymCaseStudyWithJdbc;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gym gym = new Gym();

        while (true) {
            System.out.println("\n--- Gym Membership Management System ---");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan to a Member");
            System.out.println("3. View All Registered Members");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMember(scanner, gym);
                    break;
                case 2:
                    assignPlan(scanner, gym);
                    break;
                case 3:
                    gym.displayAllMembers();
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    gym.closeConnection();
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMember(Scanner scanner, Gym gym) {
        try {
            System.out.print("Enter Member ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            Member newMember = new Member(id, name, age);
            gym.addMember(newMember);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct data types.");
            scanner.nextLine();
        }
    }

    private static void assignPlan(Scanner scanner, Gym gym) {
        try {
            System.out.print("Enter Member ID to assign a plan: ");
            int memberId = scanner.nextInt();
            scanner.nextLine();

            Member member = gym.findMemberById(memberId);
            if (member == null) {
                System.out.println("Member with ID " + memberId + " not found.");
                return;
            }

            System.out.println("\n--- Available Membership Plans ---");
            List<MembershipPlan> plans = gym.getAvailablePlans();
            if (plans.isEmpty()) {
                System.out.println("No membership plans available in the database.");
                return;
            }

            for (MembershipPlan plan : plans) {
                System.out.println(plan);
            }

            System.out.print("Enter the Plan ID to assign: ");
            int planId = scanner.nextInt();
            scanner.nextLine();

            boolean isValidPlanId = false;
            for (MembershipPlan plan : plans) {
                if (plan.getPlanId() == planId) {
                    isValidPlanId = true;
                    break;
                }
            }

            if (isValidPlanId) {
                gym.assignPlanToMember(memberId, planId);
            } else {
                System.out.println("Invalid Plan ID.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers for ID fields.");
            scanner.nextLine();
        }
    }
}