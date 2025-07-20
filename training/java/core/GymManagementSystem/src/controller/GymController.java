package controller;

import model.Member;
import model.MembershipPlan;
import service.GymService;

import java.util.List;
import java.util.Scanner;

public class GymController {
    private final GymService gymService = new GymService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
        	System.out.println("\n1. Add Member\n2. Assign Plan\n3. View Members\n4. Delete Member\n5. Exit\nChoose option:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
            	switch (choice) {
                case 1 -> addMember();
                case 2 -> assignPlan();
                case 3 -> viewAllMembers();
                case 4 -> deleteMember(); // 👈 new case
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addMember() throws Exception {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        gymService.addMember(name, age);
        System.out.println("Member added successfully.");
    }

    private void assignPlan() throws Exception {
    	
    	System.out.println("these are all the available members");
    	viewAllMembers();
    	System.out.println("--------------");
        System.out.print("Enter Member ID: ");
        int memberId = scanner.nextInt();

        List<MembershipPlan> plans = gymService.getAllPlans();
        for (MembershipPlan plan : plans) {
            plan.showDetails();
            System.out.println("ID: " + plan.getId());
        }

        System.out.print("Enter Plan ID: ");
        int planId = scanner.nextInt();

        gymService.assignPlanToMember(memberId, planId);
        System.out.println("Plan assigned successfully.");
    }

    private void viewAllMembers() throws Exception {
        List<Member> members = gymService.getAllMembers();
        for (Member m : members) {
            m.showDetails();
            System.out.println("------");
        }
    }
    private void deleteMember() throws Exception {
        System.out.print("Enter Member ID to delete: ");
        int memberId = scanner.nextInt();

        boolean success = gymService.deleteMemberById(memberId);
        if (success) {
            System.out.println("Member deleted successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

}
