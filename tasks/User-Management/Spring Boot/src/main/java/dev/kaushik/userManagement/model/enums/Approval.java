package dev.kaushik.userManagement.model.enums;

public enum Approval {
	APPROVED('A'), PENDING('P'), REJECTED('R');

	private final char code;

	Approval(char code) {
		this.code = code;
	}

	public char getCode() {
		return code;
	}

	public static Approval fromCode(char code) {
		for (Approval approval : Approval.values()) {
			if (approval.code == code) {
				return approval;
			}
		}
		throw new IllegalArgumentException("No approval with code: " + code);
	}

	public static Approval fromCodeString(String codeString) {
		if (codeString != null && !codeString.isEmpty()) {
			return fromCode(codeString.charAt(0));
		}
		throw new IllegalArgumentException("approval code string cannot be null or empty");
	}

}
