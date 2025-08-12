package com.libraryManagementSystem.utilities;

public class Validations {

	public static boolean isValidTitle(String title) {

		return title.trim().matches("[a-zA-Z0-9 :\\-.'&/,?!+]{0,50}");
	}

	public static boolean isValidIssueTitle(String title) {

		return title.trim().matches("[a-zA-Z0-9 -'.()]{0,50}");
	}

	public static boolean isValidName(String name) {

		return name.trim().matches("[a-zA-Z .'-]{0,50}");
	}

	public static boolean isValidString(String name) {

		return name.trim().matches("^[A-Z a-z,:]{3,50}$");
	}

	public static boolean isValidMobile(long mobile) {

		return String.valueOf(mobile).matches("^\\d{10}$");

	}

	public static boolean isValidEmail(String email) {
		return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}

	public static boolean isValidAdress(String adress) {
		return adress.matches("^[A-Za-z0-9\\s,./#-]{5,100}$");
	}

}
