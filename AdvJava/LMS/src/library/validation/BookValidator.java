package library.validation;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import library.exception.LibraryException;
import library.model.enums.BookAvailability;
import library.model.enums.BookStatus;
import library.model.enums.IssueStatus;

public class BookValidator {


	// Allowed characters: letters (a-z, A-Z), numbers (0-9), spaces, and common punctuation (.,',-,:&()!/?")
	private static final Pattern ALLOWED_CHARS_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s.,'\\-:&()!/?\"\\\\]*$");

	// Must contain at least one letter (a-z, A-Z) to prevent purely numeric
	private static final Pattern CONTAINS_LETTER_PATTERN = Pattern.compile(".*[a-zA-Z].*");

	// Limits for string lengths (matching DB schema)
	public static final int MAX_TITLE_LENGTH = 50;
	public static final int MAX_AUTHOR_LENGTH = 50;
	public static final int MAX_CATEGORY_LENGTH = 10;
	public static final int MAX_NAME_LENGTH = 50; 
	public static final int MAX_EMAIL_LENGTH = 50;
	public static final int MAX_ADDRESS_LENGTH = 100;
	public static final int MAX_USERNAME_LENGTH = 50;
	
	

	private BookValidator() {
	}

	//String
	public static void validateString(String value, String fieldName, int maxLength, boolean canBeEmpty, boolean mustContainLetter) {
		
		if (value == null) {
			if (!canBeEmpty) {
				throw new LibraryException(fieldName + " cannot be null.");
			}
			return;
		}
		
		String trimmedValue = value.trim();
		if (trimmedValue.isEmpty()) {
			if (!canBeEmpty) {
				throw new LibraryException(fieldName + " cannot be empty.");
			}
		}
		
		if (trimmedValue.length() > maxLength) {
			throw new LibraryException(
					fieldName + " exceeds maximum length of " + maxLength + " characters.");
		}
		
		if (!ALLOWED_CHARS_PATTERN.matcher(trimmedValue).matches()) {
			throw new LibraryException(fieldName
					+ " contains invalid characters. Allowed: a-z, A-Z, 0-9, spaces, and common punctuation (.,',-,:&()!/?\").");
		}
		
		if (mustContainLetter && !CONTAINS_LETTER_PATTERN.matcher(trimmedValue).matches()) {
			throw new LibraryException(
					fieldName + " must contain at least one letter (a-z, A-Z) and not be purely numeric.");
		}
	}
	
	//numeric
	public static void validateNumericId(int id, String fieldName) {
		if (id <= 0) {
			throw new LibraryException(fieldName + " must be a positive integer.");
		}
	}
	
	public static void validatePositiveLong(long value, String fieldName) {
		if (value <= 0) {
			throw new LibraryException(fieldName + " must be a positive number.");
		}
	}

	//Book
	public static void validateBookTitle(String title) {
		validateString(title, "Book Title", MAX_TITLE_LENGTH, false, true);
	}

	public static void validateBookAuthor(String author) {
		validateString(author, "Book Author", MAX_AUTHOR_LENGTH, false, true);
	}

	public static void validateBookCategory(String categoryDisplayName) {
		validateString(categoryDisplayName, "Book Category", MAX_CATEGORY_LENGTH, false, true);
	}

	public static void validateBookStatus(BookStatus status) {
		if (status == null) {
			throw new LibraryException("Book Status cannot be null.");
		}
	}

	public static void validateBookAvailability(BookAvailability availability) {
		if (availability == null) {
			throw new LibraryException("Book Availability cannot be null.");
		}
	}


	// Issue Record
	public static void validateIssueStatus(IssueStatus status) {
		if (status == null) {
			throw new LibraryException("Issue Status cannot be null.");
		}
	}

	public static void validateIssueDate(LocalDateTime date, String fieldName) {
		if (date == null) {
			throw new LibraryException(fieldName + " cannot be null.");
		}
	}

	public static void validateUser(String user, String fieldName) {
		validateString(user, fieldName, MAX_USERNAME_LENGTH, false, false);
	}

	public static void validateOptionalUser(String user, String fieldName) {
		if (user != null && !user.trim().isEmpty()) {
			validateString(user, fieldName, MAX_USERNAME_LENGTH, true, false);
		}
	}
}