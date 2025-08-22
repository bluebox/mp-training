package com.prana;

import Controller.MemberController;
import Controller.MembershipController;
import DBSetup.TableCreator;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        TableCreator.createTables();
        MemberController memberController = new MemberController();
        MembershipController membershipController = new MembershipController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== GYM MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Member");
            System.out.println("2. View Members");
            System.out.println("3. Update Member");
            System.out.println("4. Delete Member");
            System.out.println("5. Add Membership");
            System.out.println("6. View Memberships");
            System.out.println("7. Update Membership");
            System.out.println("8. Delete Membership");
            System.out.println("9. Exit");
            System.out.print("Choose option: ");

            int choice = readInt(scanner, "");

            switch (choice) {
                case 1: {
                    String name = readValidName(scanner, "Enter Member Name: ");
                    String joinDate = readDate(scanner, "Enter Join Date (YYYY-MM-DD): ");
                    String type = readMembershipType(scanner, "Enter Membership Type");
                    memberController.addNewMember(name, joinDate, type);
                    break;
                }
                case 2:
                    memberController.showAllMembers();
                    break;
                case 3: {
                    int id = readInt(scanner, "Enter Member ID to update: ");
                    String name = readValidName(scanner, "Enter new Name: ");
                    String joinDate = readDate(scanner, "Enter new Join Date (YYYY-MM-DD): ");
                    String type = readMembershipType(scanner, "Enter new Membership Type");
                    memberController.updateMember(id, name, joinDate, type);
                    break;
                }
                case 4: {
                    int id = readInt(scanner, "Enter Member ID to delete: ");
                    memberController.deleteMember(id);
                    break;
                }
                case 5: {
                    String planName = readValidName(scanner, "Enter Membership Plan Name: ");
                    int duration = readInt(scanner, "Enter Duration (months): ");
                    double price = readDouble(scanner, "Enter Price: ");
                    membershipController.addNewMembership(planName, duration, price);
                    break;
                }
                case 6:
                    membershipController.showAllMemberships();
                    break;
                case 7: {
                    int id = readInt(scanner, "Enter Membership ID to update: ");
                    String planName = readValidName(scanner, "Enter new Plan Name: ");
                    int duration = readInt(scanner, "Enter new Duration (months): ");
                    double price = readDouble(scanner, "Enter new Price: ");
                    membershipController.updateMembership(id, planName, duration, price);
                    break;
                }
                case 8: {
                    int id = readInt(scanner, "Enter Membership ID to delete: ");
                    membershipController.deleteMembership(id);
                    break;
                }
                case 9:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        int number;
        while (true) {
            if (!prompt.isEmpty()) System.out.print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer number.");
            }
        }
        return number;
    }

    private static double readDouble(Scanner scanner, String prompt) {
        double number;
        while (true) {
            System.out.print(prompt);
            try {
                number = Double.parseDouble(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
        return number;
    }

    private static String readDate(Scanner scanner, String prompt) {
        String dateStr;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(prompt);
            dateStr = scanner.nextLine().trim();
            try {
                LocalDate.parse(dateStr, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a valid date (YYYY-MM-DD).");
            }
        }
        return dateStr;
    }

    private static String readNonEmptyString(Scanner scanner, String prompt) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println("This field cannot be empty. Please enter a valid value.");
            } else {
                break;
            }
        }
        return value;
    }

    private static String readValidName(Scanner scanner, String prompt) {
        String name;
        while (true) {
            System.out.print(prompt);
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
            } else if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Name must contain only letters and spaces. Try again.");
            } else {
                break;
            }
        }
        return name;
    }

    private static String readMembershipType(Scanner scanner, String prompt) {
        String[] validPlans = {"Basic", "Standard", "Premium", "VIP"};
        String type;
        while (true) {
            System.out.print(prompt + " (Options: Basic, Standard, Premium, VIP): ");
            type = scanner.nextLine().trim();
            boolean valid = false;
            for (String plan : validPlans) {
                if (plan.equalsIgnoreCase(type)) {
                    type = plan; 
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                System.out.println("Invalid plan type. Please choose one of: Basic, Standard, Premium, VIP.");
            } else {
                break;
            }
        }
        return type;
    }
}
