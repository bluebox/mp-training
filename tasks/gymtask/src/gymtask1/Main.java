package gymtask1;

import java.util.Scanner;

public class Main {
	private static Scanner scanner;
	
	private static Gym gym;

	public static void main(String[] args) {
		gym = new Gym("Get Fit");
        scanner = new Scanner(System.in);
        
        System.out.println("Welcome");
        
        boolean exitProgram = false;
        while (!exitProgram) {
            try {
                displayMainMenu();
                int choice = getUserChoice();
                exitProgram = processUserChoice(choice);
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
        
        System.out.println("Thank you");
        scanner.close();
	}
	
	private static void displayMainMenu() {
        System.out.println("\nMAIN MENU");
        System.out.println("1. Add New Gym Member");
        System.out.println("2. Assign Membership Plan to Member");
        System.out.println("3. View All Registered Members");
        System.out.println("4. Exit System");
        System.out.print("Please select an option (1-4): ");
    }
	
	private static int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            return choice;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number.");
        }
    }
	
	private static boolean processUserChoice(int choice) {
        switch (choice) {
            case 1:
                addNewMember();
                break;
            case 2:
                assignMembershipPlan();
                break;
            case 3:
                viewAllMembers();
                break;
            case 4:
                return true;
            default:
                System.out.println("Invalid choice. Please select option 1-4.");
        }
        return false;
    }
	
	private static void addNewMember() {
        try {
            System.out.println("\n=== Add New Gym Member ===");
            
            System.out.print("Enter Member ID: ");
            String memberId = scanner.nextLine().trim();
            
            System.out.print("Enter Member Name: ");
            String name = scanner.nextLine().trim();
            
            System.out.print("Enter Member Age: ");
            int age = Integer.parseInt(scanner.nextLine().trim());
            
            gym.addMember(memberId, name, age);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid age (number).");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while adding member: " + e.getMessage());
        }
    }
	
	private static void assignMembershipPlan() {
        try {
            if (gym.getTotalMembers() == 0) {
                System.out.println("No members registered yet. Please add members first.");
                return;
            }
            
            System.out.println("\n=== Assign Membership Plan   ===");
            
            System.out.print("Enter Member ID: ");
            String memberId = scanner.nextLine().trim();


            
            Member member = gym.findMemberById(memberId);
            if (member == null) {
                System.out.println("Error: Member not found with ID: " + memberId);
                return;
            }
           
            
            System.out.println("Name: " + member.getName());
            if (member.hasMembershipPlan()) {
                System.out.println("Current Plan: " + member.getMembershipPlan().toString());
            } else {
                System.out.println("Current Plan: No plan assigned");
            }
            
            gym.displayAvailablePlans();
            
            System.out.print("Select a plan (1-3): ");
            int planChoice = Integer.parseInt(scanner.nextLine().trim());
            
           
            gym.assignMembershipPlan(memberId, planChoice - 1);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid plan number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred while assigning plan: " + e.getMessage());
        }
    }
	
	private static void viewAllMembers() {
        try {
            System.out.println("\n View All Registered Members:");
            gym.displayAllMembers();
            System.out.println("Total Members are " + gym.getTotalMembers());
        } catch (Exception e) {
            System.out.println("An error occurred while displaying members: " + e.getMessage());
        }
    }
}
