package gymproject;

import java.util.HashSet;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InvalidPlanException{
        Scanner sc = new Scanner(System.in);
        GymService gymService = new GymService();
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
                choice = Integer.parseInt(sc.nextLine()); 
            } catch (Exception e) {
                System.out.println("Check the input format again! Please enter only numeric data.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter ID: ");
                    String id = sc.nextLine();

                    System.out.println("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = Integer.parseInt(sc.nextLine());

                    gymService.addMember(id, name, age);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    id = sc.nextLine();
                    if(!gymService.existsById(id)){
                        System.out.println("User Doesn't exists");
                        break;
                    }
                    System.out.print("Enter Plan Name (Basic/Premium/Gold): ");
                    String plan = sc.nextLine();
                    String lowerPlan=plan.toLowerCase();
                    if(lowerPlan.equals("basic") || (lowerPlan.equals("premium")) || (lowerPlan.equals("gold"))){
                        gymService.assignPlan(id, lowerPlan);
                    }
                    else{
                        System.out.println("Please enter valid plan : (Gold/Premium/Basic)");
                    }
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