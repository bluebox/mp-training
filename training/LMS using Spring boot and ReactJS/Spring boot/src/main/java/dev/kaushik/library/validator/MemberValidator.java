package dev.kaushik.library.validator;

import java.util.regex.Pattern;

import dev.kaushik.library.exception.LibraryException;
import dev.kaushik.library.model.Member;

public class MemberValidator {
	public static final Pattern emailregEx = Pattern.compile("^[a-zA-Z0-9.]+@[a-zA-Z]+.[a-zA-Z.]{2,}$");;
	public static final Pattern nameRegEx=Pattern.compile("^[A-Za-z .]+$"); 
	public static void validate(Member member) {
		if (member == null) {
			throw new LibraryException("Member cant be null");
		}
		if (member.getName() == null) {
			throw new LibraryException("Member name cant be null");
		}
		int nameLength = member.getName().trim().length();
		if (nameLength < 2 || nameLength > 100) {
			throw new LibraryException("member name must be between 2 and 100 characters");
		}
		if (!nameRegEx.matcher(member.getName()).matches()) {
			throw new LibraryException("Name cany only contain alphabets, spaces and dots");
		}
		if (!emailregEx.matcher(member.getEmail()).matches()) {
			throw new LibraryException("Email should be in valid format");
		}
		if (member.getPhoneNumber() < 1000000000L || member.getPhoneNumber() > 9999999999L) {
			throw new LibraryException("Phone number must have exactly 10 digits");
		}
		if (member.getGender() == null) {
			throw new LibraryException("Gender cant be null");
		}
		if (member.getAddress() == null) {
			throw new LibraryException("Address cant be null");
		}
		int addressLength = member.getAddress().trim().length();
		if (addressLength < 2 || addressLength > 500) {
			throw new LibraryException("Address must be 2 to 500 characters");
		}
	}
}
