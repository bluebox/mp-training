package com.casestudy.spring.library.beans;


public enum Availability {
	AVAILABLE("A"), ISSUED("I");

	private final String code;

	Availability(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static Availability fromCode(String code) {
		if (code == null) {
			throw new IllegalArgumentException("Code cannot be null");
		}

		switch (code.toUpperCase()) {
		case "A":
			return AVAILABLE;
		case "I":
			return ISSUED;
		default:
			throw new IllegalArgumentException("Invalid gender code: " + code);
		}
	}

}
