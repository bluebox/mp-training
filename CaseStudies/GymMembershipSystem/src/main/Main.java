package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import Service.Gym;
import util.DBConnectionManager;

public class Main {

	private static final int MIN_GYM_AGE_INPUT = 16;
	private static final int MAX_GYM_AGE_INPUT = 90;
	private static final int MIN_MEMBER_ID_INPUT = 1000;
	private static final int MAX_MEMBER_ID_INPUT = 99999;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Connecting to database...");
		try (Connection testConn = DBConnectionManager.getConnection()) {
			System.out.println("Database connection successful!");
		} catch (SQLException e) {
			System.err.println("Error details: " + e.getMessage());
			e.printStackTrace();
		}

		Gym gym = new Gym();
		boolean running = true;

		while (running) {
			displayMenu();

			try {
				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					int memberId = getValidatedIntInput(
							"Enter Member ID with in range (" + MIN_MEMBER_ID_INPUT + "-" + MAX_MEMBER_ID_INPUT + "): ",
							MIN_MEMBER_ID_INPUT, MAX_MEMBER_ID_INPUT,
							"Member ID must be between " + MIN_MEMBER_ID_INPUT + " and " + MAX_MEMBER_ID_INPUT + ".");

					String name = getValidatedStringInput("Enter Name: ", "Member name cannot be empty.");

					int age = getValidatedIntInput(
							"Enter Age  with in range (" + MIN_GYM_AGE_INPUT + "-" + MAX_GYM_AGE_INPUT + "): ",
							MIN_GYM_AGE_INPUT, MAX_GYM_AGE_INPUT, "Age must be between " + MIN_GYM_AGE_INPUT + " and "
									+ MAX_GYM_AGE_INPUT + " to join the gym.");

					gym.addMember(memberId, name, age);
					break;

				case 2:
					System.out.println("---Choose a Member Id form the Below list  and Enter (Available Members)");
					gym.viewAllMembers();

					int id = getValidatedIntInput(
							"Enter Member ID (" + MIN_MEMBER_ID_INPUT + "-" + MAX_MEMBER_ID_INPUT + "): ",
							MIN_MEMBER_ID_INPUT, MAX_MEMBER_ID_INPUT,
							"Member ID must be between " + MIN_MEMBER_ID_INPUT + " and " + MAX_MEMBER_ID_INPUT + ".");

					gym.displayAvailablePlans();

					String planName = getValidatedStringInput("Enter Plan Name (Basic, Silver, Premium, Gold): ",
							"Plan name cannot be empty.");
					gym.assignPlanToMember(id, planName);
					break;

				case 3:
					gym.viewAllMembers();
					break;

				case 4:
					gym.displayAvailablePlans();
					break;

				case 5:
					running = false;
					System.out.println("Exiting system. Bye!");
					break;

				default:
					System.out.println("Invalid choice. Please enter a number between 1 and 5.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error: Please enter a valid number.");
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	private static void displayMenu() {
		System.out.println("\n=== Gym Membership Management System ===");
		System.out.println("1. Add New Member");
		System.out.println("2. Assign Membership Plan");
		System.out.println("3. View All Members");
		System.out.println("4. Display Available Plans");
		System.out.println("5. Exit");
		System.out.print("Enter your choice (1-5): ");
	}

	private static int getValidatedIntInput(String prompt, int min, int max, String rangeErrorMessage) {
		while (true) {
			try {
				System.out.print(prompt);
				String input = scanner.nextLine().trim();
				int value = Integer.parseInt(input);
				if (value >= min && value <= max) {
					return value;
				} else {
					System.out.println(rangeErrorMessage);
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a whole number.");
			}
		}
	}

	private static String getValidatedStringInput(String prompt, String emptyErrorMessage) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println(emptyErrorMessage);
			} else if (input.matches("\\d+")) {
				System.out.println("Name cannot be entire numeric. Please enter a valid name.");
			} else {
				return input;
			}
		}
	}
}

