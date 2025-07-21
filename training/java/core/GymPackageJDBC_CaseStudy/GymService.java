package GymPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class GymService {
    static Gym gym = new Gym();
    static Scanner scanner = new Scanner(System.in);

    public static void addMember() {
        int id;
        String name;
        int age;
        
        while (true) {
            try {
                System.out.print("Enter Member ID: ");
                id = Integer.parseInt(scanner.nextLine());
                if (gym.getMemberById(id) != null) {
                    System.out.println("Error: A member with this ID already exists. Please use a unique ID.");
                } else {
                    break; // ID is unique, exit loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number for the ID.");
            }
        }
        
        System.out.print("Enter Name: ");
        name = scanner.nextLine();

        while (true) {
            try {
                System.out.print("Enter Age: ");
                age = Integer.parseInt(scanner.nextLine());
                if (age > 0 && age < 100) {
                    break;
                } else {
                    System.out.println("Please enter a valid age (1-99).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number for the age.");
            }
        }

        gym.addMember(new Member(name, age, id));
    }

    public static void assignPlan() {
        System.out.print("Enter Member ID to assign plan: ");
        int memberId = Integer.parseInt(scanner.nextLine());
        Member member = gym.getMemberById(memberId);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("Available Plans:");
        int i = 1;
        for (MembershipPlan plan : gym.getPlans()) {
            System.out.print(i + ". ");
            plan.showPlanDetails();
            i++;
        }

        int planChoice = -1;
        while (planChoice < 1 || planChoice > gym.getPlans().size()) {
            System.out.print("Choose plan number: ");
            planChoice = Integer.parseInt(scanner.nextLine());
            if (planChoice < 1 || planChoice > gym.getPlans().size()) {
                System.out.println("Invalid plan choice selected.");
            }
        }

        member.assignPlan(gym.getPlans().get(planChoice - 1));
        

        gym.updateMemberPlan(member);
        
        System.out.println("Plan assigned successfully.");
    }

    public static void getAllMembers() {
        System.out.println("\n--- All Members ---");
        ArrayList<Member> allMembers = gym.getMembers();
        if(allMembers.isEmpty()){
            System.out.println("No members found in the system.");
        } else {
            for (Member m : allMembers) {
                m.showDetails();
            }
        }
    }

    public static void updatePlan() {
        System.out.print("Enter Member ID to update plan: ");
        int memberUpdateId = Integer.parseInt(scanner.nextLine());
        Member memberUpdate = gym.getMemberById(memberUpdateId);

        if (memberUpdate == null) {
            System.out.println("Member not found.");
            return;
        }

        System.out.println("Current plan for " + memberUpdate.getName() + ":");
        memberUpdate.getPlan().showPlanDetails();
        System.out.println("----------------------------------");


        System.out.println("Available Plans:");
        int j = 1;
        for (MembershipPlan planUpdate : gym.getPlans()) {
            System.out.print(j + ". ");
            planUpdate.showPlanDetails();
            j++;
        }

        System.out.print("Choose a new plan number (or enter 0 to go back): ");
        int planChoiceUpdate = Integer.parseInt(scanner.nextLine());

        if (planChoiceUpdate == 0) {
            return;
        }
        
        if (planChoiceUpdate < 1 || planChoiceUpdate > gym.getPlans().size()) {
            System.out.println("Invalid choice.");
        } else {
            MembershipPlan newPlan = gym.getPlans().get(planChoiceUpdate - 1);
            if(newPlan.getPlanName().equals(memberUpdate.getPlan().getPlanName())) {
                System.out.println("You chose the same plan. No update was made.");
            } else {
                memberUpdate.assignPlan(newPlan);
                gym.updateMemberPlan(memberUpdate);
                System.out.println("Plan updated successfully.");
            }
        }
    }
}