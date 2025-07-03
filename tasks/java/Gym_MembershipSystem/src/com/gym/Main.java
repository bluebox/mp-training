package com.gym;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.gym.domain.*;
import com.gym.services.GymService;


public class Main {
    int id = 0;
    Scanner scanner = new Scanner(System.in);
    GymService gym = new GymService();
    boolean flag = true;
    int choice;

    
    
    public static void main(String[] args) {
        new Main().run();
    }
    
    

    public void run() {
        do {
            try {
                System.out.println("\n1. Add a member ");
                System.out.println("2. Assign/Update a gym membership plan ");
                System.out.println("3. View all records of registered members ");
                System.out.println("4. Update personal details ");
                System.out.println("5. Remove a member from the gym ");
                System.out.println("6. Quit");
                System.out.print("Enter your choice (Enter number only): ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addMember();
                        break;
                    case 2:
                        assignOrUpdatePlan();
                        break;
                    case 3:
                        viewAllMembers();
                        break;
                    case 4:
                        updatePersonalDetails();
                        break;
                    case 5:
                        removeMember();
                        break;
                    case 6:
                        flag = false;
                        System.out.println("Quitting");
                        break;
                    default:
                        System.out.println("Invalid choice ! Try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        } while (flag);
    }

    
    
    public void addMember() {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            id += 1;
            gym.addMember(id, name, age);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for age. Please enter a number.");
            scanner.nextLine();
        }
    }

    
    
    
    public void assignOrUpdatePlan() {
    	
    	
        try {
            System.out.print("Enter the Id of the member to assign/update a membership plan: ");
            int memberId = scanner.nextInt();
            scanner.nextLine();

            Member member = gym.findMember(memberId);
            if (member == null) {
                System.out.println("Id not found.");
                return;
            }

            System.out.println("Available Membership Plans with Pricing:");
            MembershipPlan.Plan[] plansAvbl = MembershipPlan.Plan.values();
            
            for(MembershipPlan.Plan p : plansAvbl) {
            	System.out.println(p + " -> " + p.getMonthlyFee() + " for " + p.getDuration() + " month!");
            }
            
            
            

            System.out.print("\nEnter the required plan. Choose from 1.Basic 2.Premium 3.Gold : ");
            
            String planChoice = scanner.nextLine().toUpperCase();
            
            String plan;
            switch(planChoice) {
            	
            	case "1", "BASIC":
            		plan = "BASIC";
            		break;
            	case "2", "PREMIUM":
            		plan = "PREMIUM";
            		break;
            	case "3", "GOLD":
            		plan = "GOLD";
            		break;
            	
            	default:
            		return ;
            }
            
            

            System.out.println("\nIf you select the duration between 3 and 5(incl), you'll get 5% discount, 10% discount for more than 5 months");
            System.out.print("Enter the duration in months: ");
            int duration = scanner.nextInt();
            scanner.nextLine();

            gym.assignPlan(memberId, plan, duration);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! input must be a number.");
            scanner.nextLine();
        }
    }
    
    
    

    public void viewAllMembers() {
        gym.displayRecords();
    }

    
    
    
    public void updatePersonalDetails() {
        try {
            System.out.print("Enter the ID of the member to update personal details: ");
            int memberId = scanner.nextInt();
            scanner.nextLine();

            Member member = gym.findMember(memberId);
            if (member != null) {
                gym.updatePersonalDetails(member);
            } else {
                System.out.println("Enter a valid member ID to update the details!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric member ID.");
            scanner.nextLine();
        }
    }
    
    
    

    public void removeMember() {
        try {
            System.out.print("Enter the ID of the member to be removed: ");
            int memberId = scanner.nextInt();
            scanner.nextLine();

            Member member = gym.findMember(memberId);
            if (member != null) {
                gym.removeRecord(member);
            } else {
                System.out.println("Enter a valid ID to be removed!");
            }
        } 
        catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a numeric ID.");
            scanner.nextLine();
        }
    }
    
    
}