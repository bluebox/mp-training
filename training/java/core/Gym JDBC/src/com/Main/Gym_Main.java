package com.Main;

import java.time.LocalDate;
import java.util.Scanner;
import com.Service.Gym;
import com.Class.Gym_Member;


public class Gym_Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gym gymService = new Gym();
        boolean loop = true;

        while (loop) {
            System.out.println("\n*** MENU ***");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Assign Plan");
            System.out.println("4. View All Members");
            System.out.println("5. View Plans");
            System.out.println("6. Update Membership Plan Only");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            try {
                switch (choice) {
                    case 1:
                        System.out.print("ID: ");
                        String id = sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Age: ");
                        int age = Integer.parseInt(sc.nextLine());
                        System.out.print("Gender: ");
                        String gender = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Joining Date (YYYY-MM-DD): ");
                        LocalDate joiningDate = LocalDate.parse(sc.nextLine());

                        Gym_Member member = new Gym_Member(id, name, age, gender, email, "No Plan", joiningDate);
                        gymService.addMember(member);
                        break;

                    case 2:
                        System.out.print("Enter ID to remove: ");
                        String removeId = sc.nextLine();
                        gymService.removeMember(removeId);
                        break;

                    case 3:
                        System.out.print("Enter ID to assign plan: ");
                        String assignId = sc.nextLine();
                        gymService.assignPlan(assignId, sc);
                        break;

                    case 4:
                        gymService.displayAllMembers();
                        break;

                    case 5:
                        gymService.displayPlans();
                        break;

                    case 6:
                        System.out.print("Enter ID to update plan: ");
                        String updateId = sc.nextLine();
                        gymService.updateMembershipPlan(updateId, sc);
                        break;

                    case 7:
                        loop = false;
                        System.out.println("Thank you");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
