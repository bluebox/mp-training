package com.medplus.lms.util;

import com.medplus.lms.domain.Book;
import com.medplus.lms.domain.Member;
import com.medplus.lms.exceptions.ManagementException;

import java.util.regex.Pattern;

public class ValidationUtil {

	
	public static void validateRequired(String fieldName, Object value) {
		if (value == null) {
			throw new ManagementException(fieldName + " is required.");
		}
	}

	public static void validateString(String fieldName, String value, int maxLength) {
		validateRequired(fieldName, value);
		if (value.trim().isEmpty()) {
			throw new ManagementException(fieldName+" cannot be empty.");
		}
		if (value.length() > maxLength) {
			throw new ManagementException(fieldName+" cannot exceed "+maxLength+" characters.");
		}
	}

	public static void validateStringFixedLength(String fieldName, String value, int length) {
		validateRequired(fieldName, value);
		if (value.trim().isEmpty()) {
			throw new ManagementException(fieldName+" cannot be empty.");
		}
		if (value.length() != length) {
			throw new ManagementException(fieldName+" must be exactly "+length+" characters.");
		}
	}

	public static void validateEnum(String fieldName, Enum<?> value) {
		validateRequired(fieldName, value);
	}

	public static void validateId(String fieldName, int id) {
		if (id <= 0) {
			throw new ManagementException(fieldName + " must be greater than 0.");
		}
	}


	public static void validateBookId(int bookId) {
		validateId("BookId", bookId);
	}
	public static void validateTitle(String title) {
		validateString("Title", title, 35);
	}
	public static void validateAuthor(String author) {
		validateString("Author", author, 35);
	}
	public static void validateCategory(Enum<?> category) {
		validateEnum("Category", category);
	}
	public static void validateStatus(Enum<?> status) {
		validateEnum("Status", status);
	}
	public static void validateAvailability(Enum<?> availability) {
		validateEnum("Availability", availability);
	}
	public static void validateBook(Book book) {
		validateRequired("Book", book);

		validateTitle(book.getTitle());
		validateAuthor(book.getAuthor());
		validateCategory(book.getCategory());
		validateStatus(book.getStatus());
		validateAvailability(book.getAvailability());
	}

	public static void validateMemberId(int memberId) {
		validateId("MemberId", memberId);
	}
	public static void validateName(String name) {
		validateString("Name", name, 35);
	}
	public static void validateEmail(String email) {
		validateRequired("Email", email);
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		if (!Pattern.matches(emailRegex, email)) {
			throw new ManagementException("Invalid email format.");
		}
	}
	public static void validateMobile(String mobile) {
		validateStringFixedLength("Mobile", mobile, 10);
		if (!mobile.matches("\\d{10}")) {
			throw new ManagementException("Mobile must contain only digits.");
		}
	}
	public static void validateGender(Enum<?> gender) {
		validateEnum("Gender", gender);
	}
	public static void validateAddress(String address) {
		validateString("Address", address, 100);
	}

	public static void validateMember(Member member) {
		validateRequired("Member", member);
		validateName(member.getName());
		validateEmail(member.getEmail());
		validateMobile(member.getMobile());
		validateGender(member.getGender());
		validateAddress(member.getAddress());
	}
}
