package com.libraryManagementSystem.utilities;

public class Validations {

	public static boolean isValidString(String name) {

		return name.trim().matches("^[A-Z a-z,:]{2,255}$");
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
