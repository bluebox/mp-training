package dev.kaushik.userManagement.model.enums;

public enum Status {
	ACTIVE('A'), INACTIVE('I');

	private final char code;

	Status(char code) {
		this.code = code;
	}

	public char getCode() {
		return code;
	}

	public static Status fromCode(char code) {
		for (Status status : Status.values()) {
			if (status.code == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("No status with code: " + code);
	}

	public static Status fromCodeString(String codeString) {
		if (codeString != null && !codeString.isEmpty()) {
			return fromCode(codeString.charAt(0));
		}
		throw new IllegalArgumentException("status code string cannot be null or empty");
	}
}