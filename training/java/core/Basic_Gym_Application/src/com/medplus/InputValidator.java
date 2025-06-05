package com.medplus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class InputValidator {

	public static int getValidatedInteger(String prompt, int x, int y) {
		Scanner scanner = new Scanner(System.in);
		String regex = "-?\\d+";

		int input;
		while (true) {
			System.out.print(prompt);
			String userInput = scanner.nextLine();

			if (userInput.matches(regex)) {
				input = Integer.parseInt(userInput);
				if (input >= x && input <= y) {
					return input;
				} else {
					System.out.println("Input must be between " + x + " and " + y + ".");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid integer.");
			}
		}
	}

	public static LocalDate getValidatedDate(String prompt, LocalDate startDate, LocalDate endDate) {
		Scanner scanner = new Scanner(System.in);
		String regex = "\\d{2}-\\d{2}-\\d{4}"; // Matches dd-MM-yyyy format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		while (true) {
			System.out.print(prompt); // Print user-defined prompt
			String userInput = scanner.nextLine();

			if (userInput.matches(regex)) {
				try {
					LocalDate date = LocalDate.parse(userInput, formatter);
					if ((date.isEqual(startDate) || date.isAfter(startDate))
							&& (date.isEqual(endDate) || date.isBefore(endDate))) {
						return date;
					} else {
						System.out.println("Date must be between " + startDate.format(formatter) + " and "
								+ endDate.format(formatter) + ".");
					}
				} catch (DateTimeParseException e) {
					System.out.println("Invalid date. Please follow the format dd-MM-yyyy.");
				}
			} else {
				System.out.println("Invalid format. Please enter the date in dd-MM-yyyy format.");
			}
		}
	}

	public static String getValidatedString(String prompt) {
		Scanner scanner = new Scanner(System.in);
		String regex = "^[a-zA-Z]*\\.?[a-zA-Z]*$";

		while (true) {
			System.out.print(prompt);
			String userInput = scanner.nextLine();

			if (userInput.length() <= 20 && userInput.matches(regex)) {
				return userInput;
			} else {
				System.out.println(
						"Invalid input. String must be max 20 characters and contain only letters and at most one dot.");
			}
		}
	}

}
