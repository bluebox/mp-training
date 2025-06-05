package gymproject;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws InvalidPlanException {
        Scanner sc = new Scanner(System.in);
        GymService gymService = new GymService();
        try {
            while (true) {
                System.out.println("\n===== Gym Membership System =====");
                System.out.println("1. Add New Member using Scanner Class");
                System.out.println("2. Add New Member using files");
                System.out.println("3. Update Member");
                System.out.println("4. Delete Member");
                System.out.println("5. Assign Membership Plan");
                System.out.println("6. Update Membership Plan");
                System.out.println("7. Delete Membership Plan");
                System.out.println("8. View All Members");
                System.out.println("9. View All Plans");
                System.out.println("10. Exit");
                System.out.print("Enter your choice: ");

                int choice = 0;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Check the input format again! Please enter only numeric data.");
                    continue;
                }
                try {
                    switch (choice) {

                        case 1:
                            System.out.println("Enter ID: ");
                            String id = sc.nextLine();

                            if (gymService.existsById(id)) {
                                System.out.println("Member already exists");
                                break;
                            }
                            System.out.println("Enter Name: ");
                            String name = sc.nextLine();
                            
                            System.out.print("Enter Age: ");
                            int age = Integer.parseInt(sc.nextLine());

                            gymService.addMember(id, name, age);
                            break;

                        case 2:
                            System.out.println("\nEnter file name:");
                            String line;
                            String filePath = sc.nextLine();
                            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                                int i = 0;
                                while ((line = br.readLine()) != null) {
                                    String[] text = line.split(",");

                                    if (i != 0) {
                                        id = text[0];
                                        String nameFromFile = text[1];
                                        int ageFromFile = Integer.parseInt(text[2]);
                                        gymService.addMember(id, nameFromFile, ageFromFile);
                                    }
                                    i++;
                                }
                            } catch (Exception e) {
                                System.out.println("Error reading file: " + e.getMessage());
                                System.out.println("Please check the file ");
                            }
                            break;
                        case 3:
                            System.out.print("Enter ID to update: ");
                            id = sc.nextLine();

                            if (!gymService.existsById(id)) {
                                System.out.println("User Doesn't exist");
                                break;
                            }
                            System.out.println("Enter new name to update: ");
                            String newName = sc.nextLine();

                            gymService.updateMember(id, newName);
                            System.out.println("Member updated Successfully");
                            break;

                        case 4:
                            System.out.print("Enter ID to delete: ");
                            id = sc.nextLine();
                            if (!gymService.existsById(id)) {
                                System.out.println("User Doesn't exist");
                                break;
                            }
                            gymService.deleteMember(id);
                            System.out.println("Member Deleted Successfully");
                            break;

                        case 5:
                            System.out.print("Enter ID: ");
                            id = sc.nextLine();

                            if (!gymService.existsById(id)) {
                                System.out.println("User Doesn't exist");
                                break;
                            }

                            System.out.print("Enter Plan Name (Basic/Premium/Gold): ");
                            String plan = sc.nextLine().toLowerCase();

                            if (isValidPlan(plan, gymService)) {
                                gymService.assignPlan(id, plan);
                            } else {
                                System.out.println("\nPlease enter valid plan: (Gold/Premium/Basic)");
                            }
                            break;
                            
                        case 6:
                            System.out.print("Enter ID to Update Membership: ");
                            id = sc.nextLine();

                            if (!gymService.existsById(id)) {
                                System.out.println("User Doesn't exist");
                                break;
                            }

                            System.out.println("Enter Existing plan:");
                            String oldPlan = sc.nextLine().toLowerCase();

                            if (isValidPlan(oldPlan, gymService)) {
                                System.out.println("Enter New plan:");
                                String newPlan = sc.nextLine().toLowerCase();

                                if (isValidPlan(newPlan, gymService)) {
                                    gymService.updateMembershipPlan(id, oldPlan, newPlan);
                                } else {
                                    System.out.println("Invalid new plan");
                                }
                            } else {
                                System.out.println("Invalid existing plan");
                            }
                            break;
                        case 7:
                            System.out.print("Enter ID to Delete Membership: ");
                            id = sc.nextLine();

                            if (!gymService.existsById(id)) {
                                System.out.println("User Doesn't exist");
                                break;
                            }

                            System.out.print("Enter Plan Name to delete: ");
                            String planToDelete = sc.nextLine().toLowerCase();

                            if (isValidPlan(planToDelete, gymService)) {
                                gymService.deleteMembershipPlan(id, planToDelete);
                            } else {
                                System.out.println("Invalid plan name");
                            }
                            break;
                        case 8:
                            gymService.viewAllMembers();
                            break;

                        case 9:
                            gymService.viewAllPlans();
                            break;

                        case 10:
                            System.out.println("\nExiting the system. Goodbye!");
                            return;

                        default:
                            System.out.println("\nInvalid choice. Please select a valid option.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Error occurred: " + e.getMessage());
                    e.printStackTrace();
                }
            }

        } finally {
            sc.close();
        }
    }

    public static boolean isValidPlan(String plan, GymService gymService) {
        return gymService.getPlans().containsKey(plan.toLowerCase());
    }
}
