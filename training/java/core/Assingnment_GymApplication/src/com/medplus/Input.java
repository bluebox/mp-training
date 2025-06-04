package com.medplus;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getValidatedName() {
        try {
            System.out.print("\nEnter  Name: ");
            String name = scanner.nextLine().trim();
            if (!Pattern.matches("^[A-Za-z]+(\\.[A-Za-z]+)?$", name)) {
                throw new IllegalArgumentException("Name must contain only letters and at most one dot.");
            }
            return name;
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
            return getValidatedName(); // retry
        }
    }

    public static LocalDate getValidatedDOB() {
        try {
            System.out.print("Enter your Date of Birth (YYYY-MM-DD): ");
            String dobInput = scanner.nextLine().trim();
            LocalDate dob = LocalDate.parse(dobInput);
            int age = Period.between(dob, LocalDate.now()).getYears();
            if (age < 7 || age > 120) {
                throw new IllegalArgumentException("Age must be between 7 and 120.");
            }
            return dob;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return getValidatedDOB(); // retry
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
            return getValidatedDOB(); // retry
        }
    }

    public static MembershipPlans getValidatedMembershipPlan() {
        try {
            MembershipPlans.showPlans();
            int planNumber = Integer.parseInt(scanner.nextLine().trim());
            switch (planNumber) {
                case 1: return MembershipPlans.BASIC;
                case 2: return MembershipPlans.GOLD;
                case 3: return MembershipPlans.PREMIUM;
                default:
                    throw new IllegalArgumentException("Plan must be a number between 1 and 3.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid number.");
            return getValidatedMembershipPlan(); // retry
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
            return getValidatedMembershipPlan(); // retry
        }
    }

    public static int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }
    
    public static int getNumberInRange(int a, int b) {
        while (true) {
            try {
                System.out.print("Enter a number : ");
                String input = scanner.nextLine().trim();

                // Define number pattern inside the method
                Pattern numberPattern = Pattern.compile("^\\d+$"); // matches only digits

                if (!numberPattern.matcher(input).matches()) {
                    throw new IllegalArgumentException("Input must be a valid whole number.");
                }

                int number = Integer.parseInt(input);

                if (number < a || number > b) {
                    throw new IllegalArgumentException("Number must be between " + a + " and " + b + ".");
                }

                return number; // Valid input
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
}
    public static LocalDate getValidDateFromUser() {
        while (true) {
            try {
                System.out.print("Enter a date (YYYY-MM-DD): ");
                String input = scanner.nextLine().trim();

                // Regex to match YYYY-MM-DD
                Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
                if (!datePattern.matcher(input).matches()) {
                    throw new IllegalArgumentException("Date must be in YYYY-MM-DD format.");
                }

                // Try parsing the date
                LocalDate date = LocalDate.parse(input);
                return date;

            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("Invalid date: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
}
    public static int getNumberInRange1(int a, int b) {
        while (true) {
            try {
                System.out.print("Enter yourId : ");
                String input = scanner.nextLine().trim();

                // Define number pattern inside the method
                Pattern numberPattern = Pattern.compile("^\\d+$"); // matches only digits

                if (!numberPattern.matcher(input).matches()) {
                    throw new IllegalArgumentException("Input must be a valid whole number.");
                }

                int number = Integer.parseInt(input);

                if (number < a || number > b) {
                    throw new IllegalArgumentException("Number must be between " + a + " and " + b + ".");
                }

                return number; // Valid input
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
}
}
