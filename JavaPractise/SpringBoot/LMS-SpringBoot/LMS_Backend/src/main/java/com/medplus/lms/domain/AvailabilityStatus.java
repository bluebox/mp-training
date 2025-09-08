package com.medplus.lms.domain;

public enum AvailabilityStatus {
	AVAILABLE("A", "Available"), ISSUED("I", "Issued");

	private final String code;
	private final String description;

	AvailabilityStatus(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static AvailabilityStatus fromCode(String code) {
		for (AvailabilityStatus a : values()) {
			if (a.getCode().equalsIgnoreCase(code))
				return a;
		}
		return null;
	}

	
}
