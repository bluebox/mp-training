package library.validation;

import library.exception.LibraryException;
import library.model.Member;
import library.model.enums.Gender;

import java.util.regex.Pattern;

public class MemberValidator {

    private static final Pattern ALLOWED_CHARS_PATTERN = Pattern.compile("^[a-zA-Z0-9\\s.,'\\-:&()!/?\"\\\\]*$");
    private static final Pattern CONTAINS_LETTER_PATTERN = Pattern.compile(".*[a-zA-Z].*");
    public static final int MAX_NAME_LENGTH = 50; 
    public static final int MAX_EMAIL_LENGTH = 50;
    public static final int MAX_ADDRESS_LENGTH = 100;

    public static void validateMember(Member member) {
        if (member == null) {
            throw new LibraryException("Member cannot be null.");
        }
        validateMemberName(member.getName());
        validateMemberEmail(member.getEmail());
        validateMemberPhoneNumber(member.getPhoneNumber());
        validateMemberAddress(member.getAddress());
        validateMemberGender(member.getGender());
    }

    public static void validateMemberForUpdate(Member member) {
        validateMember(member);
        validateMemberId(member.getMemberID());
    }


    public static void validateMemberForDelete(Member member) {
        if (member == null) {
            throw new LibraryException("Member cannot be null.");
        }
        validateMemberId(member.getMemberID());
    }

    public static void validateMemberId(int id) {
        if (id <= 0) {
            throw new LibraryException("Member ID must be positive.");
        }
    }

    public static void validateMemberName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new LibraryException("Member name cannot be empty.");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new LibraryException("Member name cannot exceed " + MAX_NAME_LENGTH + " characters.");
        }
        if (!ALLOWED_CHARS_PATTERN.matcher(name).matches()) {
            throw new LibraryException("Member name contains invalid characters.");
        }
        if (!CONTAINS_LETTER_PATTERN.matcher(name).matches()) {
            throw new LibraryException("Member name must contain at least one letter.");
        }
    }

    public static void validateMemberEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new LibraryException("Member email cannot be empty.");
        }
        if (email.length() > MAX_EMAIL_LENGTH) {
            throw new LibraryException("Member email cannot exceed " + MAX_EMAIL_LENGTH + " characters.");
        }
        if (!email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            throw new LibraryException("Invalid email format.");
        }
    }

    public static void validateMemberPhoneNumber(long phoneNumber) {
        if (phoneNumber <= 0) {
            throw new LibraryException("Phone number cannot be zero or negative.");
        }
        String phoneNumberStr = String.valueOf(phoneNumber);
        if (phoneNumberStr.length()!=10) {
            throw new LibraryException("Phone number length is invalid. Must be 10 digits.");
        }
    }

    public static void validateMemberAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new LibraryException("Member address cannot be empty.");
        }
        if (address.length() > MAX_ADDRESS_LENGTH) {
            throw new LibraryException("Member address cannot exceed " + MAX_ADDRESS_LENGTH + " characters.");
        }
        if (!ALLOWED_CHARS_PATTERN.matcher(address).matches()) {
            throw new LibraryException("Member address contains invalid characters.");
        }
    }

    public static void validateMemberGender(Gender gender) {
        if (gender == null) {
            throw new LibraryException("Gender cannot be null.");
        }
    }
}