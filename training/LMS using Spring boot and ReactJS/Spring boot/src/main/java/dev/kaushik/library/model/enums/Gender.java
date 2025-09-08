package dev.kaushik.library.model.enums;

public enum Gender {
    MALE('M'),
    FEMALE('F'),
    OTHER('O');

    private final char code;

    Gender(char code) {
        this.code = code;
    }

    public char getCode() {
        return code;
    }

    public static Gender fromCode(char code) {
        for (Gender gender : Gender.values()) {
            if (gender.code == code) {
                return gender;
            }
        }
        throw new IllegalArgumentException("No gender with code: " + code);
    }

    public static Gender fromCodeString(String codeString) {
        if (codeString != null && !codeString.isEmpty()) {
            return fromCode(codeString.charAt(0));
        }
        throw new IllegalArgumentException("Gender code string cannot be null or empty.");
    }
}