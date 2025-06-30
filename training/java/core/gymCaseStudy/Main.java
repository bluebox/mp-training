package gymCaseStudy;

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
	
	         if (gym.findMemberById(id) != null) {
	             System.out.println("Error: Member with this ID already exists.");
	             return;
	         }
	
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
	         System.out.print("Enter Member ID or Name to assign a plan: ");
	         String input = scanner.nextLine();
	         Member member = null;
	         try {
	             int memberId = Integer.parseInt(input);
	             member = gym.findMemberById(memberId);
	         } catch (NumberFormatException e) {
	             member = gym.findMemberByName(input);
	         }

	         if (member == null) {
	             System.out.println("Member with ID/Name '" + input + "' not found.");
	             return;
	         }
	         
	         if (member.getMembershipPlan() != null) {
	             System.out.println(member.getName() + " already has a plan: " + member.getMembershipPlan().getPlanName());
	             System.out.print("Do you want to change it? (yes/no): ");
	             String response = scanner.nextLine();
	             if (!response.equalsIgnoreCase("yes")) {
	                 System.out.println("Operation cancelled.");
	                 return;
	             }
	         }

	         System.out.println("\n--- Available Membership Plans ---");
	         List<MembershipPlan> plans = gym.getAvailablePlans();
	         for (int i = 0; i < plans.size(); i++) {
	             System.out.println((i + 1) + ". " + plans.get(i));
	         }

	         System.out.print("Choose a plan number: ");
	         int planChoice = scanner.nextInt();
	         scanner.nextLine();

	         if (planChoice > 0 && planChoice <= plans.size()) {
	             MembershipPlan selectedPlan = plans.get(planChoice - 1);
	             member.setMembershipPlan(selectedPlan);
	             System.out.println("Plan '" + selectedPlan.getPlanName() + "' assigned to " + member.getName() + " successfully.");
	         } else {
	             System.out.println("Invalid plan choice.");
	         }
	     } catch (InputMismatchException e) {
	         System.out.println("Invalid input. Please enter a number for plan choice.");
	         scanner.nextLine();
	     }
	 }
}