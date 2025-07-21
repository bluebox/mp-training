package GymManagementSystem.service;

import java.time.LocalDate;
import java.util.Scanner;

public class InputValidation {
	private static Scanner scanner = new Scanner(System.in);

	public int getIntInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid number. Try again.");
			}
		}
	}

	public double getDoubleInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				return Double.parseDouble(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid number. Try again.");
			}
		}
	}

	public String getStringInput(String prompt) {
		while (true) {
			System.out.print(prompt);
			String name = scanner.nextLine();
			if (!name.matches(".*\\d.*")) {
				return name;
			} else {
				System.out.println("Invalid name. Name should not contain numbers.");
			}
		}
	}

	public int getPositiveIntInput(String prompt) {
		while (true) {
			try {
				System.out.print(prompt);
				int value = Integer.parseInt(scanner.nextLine());
				if (value > 0 && value <= 99) {
					return value;
				} else {
					System.out.println("Invalid age. Please enter an age between 1 and 100.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}
	}

	public LocalDate getDateInput(String prompt) {
		LocalDate startDate;
		while (true) {
			System.out.print(prompt);
			String dateStr = scanner.next();
			try {
				startDate = LocalDate.parse(dateStr); // Validates format
				if (startDate.isAfter(LocalDate.now())) {
					System.out.println("Start date cannot be in the future.");
				}else {
				return startDate;
				}
			} catch (Exception e) {
				System.out.println("Invalid date format. Please enter date as yyyy-mm-dd.");
				// return;
			}
		}
	}
}
