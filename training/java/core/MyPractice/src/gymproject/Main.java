package gymproject;

import java.util.HashSet;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InvalidPlanException{
        Scanner sc = new Scanner(System.in);
        GymService gymService = new GymService();
        HashSet<String>plans=new HashSet<>();
        plans.add("Premium");
        plans.add("Gold");
        plans.add("Basic");
        

        while (true) {
            System.out.println("\n===== Gym Membership System =====");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. View All Plans");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine()); // Fix: read entire line and parse
            } catch (Exception e) {
                System.out.println("Check the input format again! Please enter only numeric data.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.println("Enter Name: ");
                    int age = 0;
                    try {
                    String name = sc.nextLine();
                    if(!name.matches("[a-zA-Z]+"))
                    	throw new InvalidPlanException("Enter the correct name");
                    System.out.print("Enter Age: ");
                    
                   
                        age = Integer.parseInt(sc.nextLine());
                        System.out.println();
                        gymService.addMember(id, name, age);
                        break;
                        
                    } catch (NumberFormatException e) {
                        System.out.println("\nInvalid age. Please enter a number.");
                        break;
                    }
                    catch(InvalidPlanException t) {
                    	System.out.println("\nenter the correct name");
                    	break;
                    }
                   

                case 2:
                    System.out.print("Enter ID: ");
                    id = sc.nextLine();
                    System.out.print("Enter Plan Name (Basic/Premium/Gold): ");
                    String plan = sc.nextLine();
                    System.out.println();
                    try {
                    if(!plans.contains(plan))
                    	throw new InvalidPlanException("invalid plan");
                    } 
                    catch(InvalidPlanException e) {
                    	System.out.println("Enter the valid plan");
                    	break;
                    }
                    gymService.assignPlan(id, plan);
                    break;

                case 3:
                	System.out.println();
                    gymService.viewAllMembers();
                    break;

                case 4:
                    gymService.viewAllPlans();
                    break;

                case 5:
                    System.out.println("\nExiting the system. Goodbye!");
                    return;

                default:
                    System.out.println("\nInvalid choice. Please select a valid option.");
            }
            System.out.println();       
        }
    }
}