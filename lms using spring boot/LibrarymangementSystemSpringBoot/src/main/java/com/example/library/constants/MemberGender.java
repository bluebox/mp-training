package com.example.library.constants;

import java.util.Arrays;

public enum MemberGender {
	MALE("Male", "M"), FEMALE("Female", "F") ;

	private final String displayGender;
	private final String dbValue;

	public String getDisplayGender() {
		return displayGender;
	}

	public String getDbValue() {
		return dbValue;
	}

	@Override
	public String toString() {
		return displayGender;
	}

	private MemberGender(String displayGender, String dbValue) {
		this.displayGender = displayGender;
		this.dbValue = dbValue;
	}

	public static MemberGender fromAny(String value) {
		if (value == null) {
			return null;
		}
		String v = value.trim();
		return Arrays.stream(values()).filter(e -> e.displayGender.equalsIgnoreCase(v) || e.dbValue.equalsIgnoreCase(v)
				|| e.name().equalsIgnoreCase(v)).findFirst().orElse(null);
	}

}
