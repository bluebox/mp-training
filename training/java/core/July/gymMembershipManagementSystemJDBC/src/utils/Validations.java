package utils;

public class Validations {

	public static boolean isValidName(String name) {
		return name.matches("^[A-Za-z]{2,30}$");
	}

	public static boolean isValidAge(String age) {
		return age.matches("^[0-9]+$") && Integer.parseInt(age) >= 1 && Integer.parseInt(age) <= 99;
	}

	public static boolean isValidId(String id) {
		return id.matches("^[0-9]+$");
	}

	public static boolean isValidContact(String contact) {
		return contact.matches("^\\d{10}$"); // Validates exactly 10-digit number
	}

}
