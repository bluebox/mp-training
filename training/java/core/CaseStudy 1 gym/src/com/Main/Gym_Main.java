package com.Main;

import java.util.Scanner;
import com.Service.Gym;
import com.Exceptions.*;
import com.Gym_Dao.Gym_Dao;
import com.Class.Gym_Member;

public class Gym_Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gym gym = new Gym();
        boolean loop = true;

        while (loop) {
            System.out.println("\n*** MENU ***");
            System.out.println("1. Add Member");
            System.out.println("2. Remove Member");
            System.out.println("3. Assign Plan");
            System.out.println("4. View All Members");
            System.out.println("5. View Plans");
            System.out.println("6. Upgrade Membership");
            System.out.println("7. Exit");

            int choice = -1;
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    String id, name, gender, email;
                    int age;

                    
                    while (true) {
                        System.out.print("ID: ");
                        id = sc.nextLine();
                        if (id.matches("^[0-9]")) break;
                        System.out.println("ID Must Contain Numbers");
                    } if (!id.isEmpty()) break; {
						System.out.println("ID cannot be empty.");
					}
						
					

                    while (true) {
                        System.out.print("Name: ");
                        name = sc.nextLine();
                        if (name.matches("^[A-Za-z ]+$")) break;
                        System.out.println("Name must contain only alphabets.");
                    }

                    while (true) {
                        try {
                            System.out.print("Age: ");
                            age = Integer.parseInt(sc.nextLine());
                            if (age >= 16 && age <= 100) break;
                            else System.out.println("Age must be between 16 and 100.");
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number for age.");
                        }
                    }

                    while (true) {
                        System.out.print("Gender (Male/Female): ");
                        gender = sc.nextLine().trim().toLowerCase();
                        if (gender.equals("male") || gender.equals("female")) {
                            gender = gender.substring(0, 1).toUpperCase() + gender.substring(1);
                            break;
                        }
                        System.out.println("Gender must be 'Male' or 'Female'.");
                    }
                    String emailRegex = "^[A-Za-z]+@[a-z]+.[a-z]{2,6}$";
                    while (true) {
                        System.out.print("Email: ");
                        email = sc.nextLine();
                        if (email.matches(emailRegex)) break;
                        System.out.println("Invalid email format. Try again like name@example.com");
                    }

                    try {
                        gym.addMember(id, name, age, gender, email);
                        Gym_Member savedMember = gym.findMember(id);
                        if (savedMember != null) {
                        	Gym_Dao.saveAllMembersToCSV(gym.getAllMembers());

                        }
                    } catch (Gym_Member_Already_Exists e) {
                        System.out.println(" Error" + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter Member ID to remove: ");
                        String removeId = sc.nextLine();
                        gym.removeMember(removeId);
                    } catch (Gym_Member_Not_Found e) {
                        System.out.println(" Member Not Found " + e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Member ID: ");
                        String memId = sc.nextLine();
                        gym.showPlans();
                        System.out.print("Choose plan number: ");
                        int planIdx = Integer.parseInt(sc.nextLine()) - 1;
                        gym.assignPlan(memId, planIdx);

                        Gym_Member updatedMember = gym.findMember(memId);
                        if (updatedMember != null) {
                        	Gym_Dao.saveAllMembersToCSV(gym.getAllMembers());

                        }
                    } catch (Gym_Member_Not_Found e) {
                        System.out.println(" Member Not Found " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid plan number.");
                    }
                    break;

                case 4:
                    gym.showAllMembers();
                    break;

                case 5:
                    gym.showPlans();
                    break;

                case 6:
                    try {
                        System.out.print("Enter Member ID for upgrade: ");
                        String upgradeId = sc.nextLine();
                        gym.showPlans();
                        System.out.print("Select new plan number: ");
                        int newPlanIdx = Integer.parseInt(sc.nextLine()) - 1;

                        boolean upgraded = gym.upgradeMembership(upgradeId, newPlanIdx);
                        if (upgraded) {
                            System.out.println("✓ Membership upgraded successfully.");
                            Gym_Member upgradedMember = gym.findMember(upgradeId);
                            Gym_Dao.saveAllMembersToCSV(gym.getAllMembers());

                        } else {
                            System.out.println("Upgrade failed: New plan is not better than current plan.");
                        }
                    } catch (Gym_Member_Not_Found e) {
                        System.out.println("" + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input.");
                    }
                    break;

                case 7:
                    System.out.println("*** THANK YOU ***");
                    loop = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
