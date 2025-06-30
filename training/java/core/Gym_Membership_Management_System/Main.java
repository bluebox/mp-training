package Gym_Membership_Management_System;
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
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(scanner.nextLine());

                        if (age <= 0) throw new IllegalArgumentException("Age must be positive.");

                        gym.addMember(new Member(name, age,id));
                        break;

                    case 2:
                        System.out.print("Enter Member ID to assign plan: ");
                        int memberId = Integer.parseInt(scanner.nextLine());
                        Member member = gym.getMemberById(memberId);
                        if (member == null) {
                            System.out.println("Member not found.");
                            break;
                        }

                        System.out.println("Available Plans:");
                        int i = 1;
                        for (MembershiPlan plan : gym.getPlans()) {
                            System.out.print(i + ". ");
                            plan.showPlanDetails();
                            i++;
                        }

                        System.out.print("Choose plan number: ");
                        int planChoice = Integer.parseInt(scanner.nextLine());
                        if (planChoice < 1 || planChoice > gym.getPlans().size()) {
                            System.out.println("Invalid choice.");
                        } else {
                            member.assignPlan(gym.getPlans().get(planChoice - 1));
                            System.out.println("Plan assigned successfully.");
                        }
                        gym.saveMembersToFile();
                        break;

                    case 3:
                        System.out.println("\n--- All Members ---");
                        for (Member m : gym.getMembers()) {
                            m.showDetails();
                        }
                        break;

                    case 4:
                        System.out.println("Exiting system. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 4);

    }
}

