package library.validation;

import library.exception.LibraryException;
import library.model.Member;
import library.model.enums.Gender;

public class MemberValidator {

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
    }

    public static void validateMemberEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new LibraryException("Member email cannot be empty.");
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
        if (phoneNumberStr.length() < 7 || phoneNumberStr.length() > 15) {
            throw new LibraryException("Phone number length is invalid. Must be between 7 and 15 digits.");
        }
    }

    public static void validateMemberAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new LibraryException("Member address cannot be empty.");
        }
    }

    public static void validateMemberGender(Gender gender) {
        if (gender == null) {
            throw new LibraryException("Gender cannot be null.");
        }
        if (gender != Gender.MALE && gender != Gender.FEMALE && gender != Gender.OTHER) {
             throw new LibraryException("Gender must be 'MALE', 'FEMALE', or 'OTHER'.");

        }
    }
}