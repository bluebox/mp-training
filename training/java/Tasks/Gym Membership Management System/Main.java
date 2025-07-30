package GymMembershipManagementSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Gym gym = new Gym();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        MAIN_MENU:
        while (!exit) {
            System.out.println("\n--- Gym Membership Management System ---");
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan");
            System.out.println("3. View All Members");
            System.out.println("4. Exit\n");

            System.out.print("Enter your choice: ");
            String choiceInput = sc.nextLine();
            if (checkForMenu(choiceInput)) continue MAIN_MENU;

            if (choiceInput.isEmpty()) {
                System.out.println("No input provided. Please enter a number from the menu.");
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid menu option. Please choose 1–4.");
                    continue;
                }
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1–4).");
                continue;
            }

            switch (choice) {
                case 1:
                    int id = 0, age = 0;
                    String name = "";

                    while (true) {
                        System.out.print("Enter Member ID: ");
                        String idInput = sc.nextLine().trim();
                        if (checkForMenu(idInput)) continue MAIN_MENU;
                        try {
                            id = Integer.parseInt(idInput);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid ID. Please enter a number.");
                        }
                    }

                    while (true) {
                        System.out.print("Enter Name: ");
                        name = sc.nextLine();
                        if (checkForMenu(name)) continue MAIN_MENU;
                        if (!name.matches(".*\\d.*")) {
                            break;
                        } else {
                            System.out.println("Invalid name. Name should not contain numbers.");
                        }
                    }

                    while (true) {
                        System.out.print("Enter Age: ");
                        String ageInput = sc.nextLine().trim();
                        if (checkForMenu(ageInput)) continue MAIN_MENU;
                        try {
                            age = Integer.parseInt(ageInput);
                            if (age <= 0) {
                                System.out.println("Invalid age. Age must be a positive number.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid age. Please enter a number.");
                        }
                    }

                    gym.addMember(id, name, age);
                    break;

                case 2:
                    int mId = 0, planIndex = -1;
                    while (true) {
                        System.out.print("Enter Member ID: ");
                        String mIdInput = sc.nextLine().trim();
                        if (checkForMenu(mIdInput)) continue MAIN_MENU;
                        try {
                            mId = Integer.parseInt(mIdInput);
                            if (gym.findMemberById(mId) == null) {
                                System.out.println("No member found with ID: " + mId);
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Member ID. Please enter a number.");
                        }
                    }

                    System.out.println("\nAvailable Plans:");
                    gym.listPlans();

                    while (true) {
                        System.out.print("\nEnter Plan Index (0/1/2): ");
                        String planInput = sc.nextLine().trim();
                        if (checkForMenu(planInput)) continue MAIN_MENU;
                        try {
                            planIndex = Integer.parseInt(planInput);
                            if (planIndex < 0 || planIndex > 2) {
                                System.out.println("Invalid Plan Index. Please select 0, 1, or 2 only.");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid Plan Index. Please enter a number.");
                        }
                    }

                    gym.assignPlanToMember(mId, planIndex);
                    break;

                case 3:
                    gym.viewAllMembers();
                    break;

                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                    break;
            }
        }

        sc.close();
    }
    
    private static boolean checkForMenu(String input) {
        return input.equalsIgnoreCase("menu") || input.equalsIgnoreCase("main");
    }
}

