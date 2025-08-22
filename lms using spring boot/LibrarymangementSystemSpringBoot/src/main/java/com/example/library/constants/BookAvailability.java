package com.example.library.constants;

import java.util.stream.Stream;

public enum BookAvailability {
	AVAILABLE("Available", "A"), ISSUED("Issued", "I");

	private String displayName;
	private String dbName;

	BookAvailability(String displayName, String dbName) {
		this.displayName = displayName;
		this.dbName = dbName;
	}

	public String getStringValue() {
		return this.dbName;
	}

	@Override
	public String toString() {
		return this.displayName;
	}

	public static BookAvailability getEnumConstant(String value) {
		return Stream.of(BookAvailability.values()).filter(e -> e.getStringValue().equals(value)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid availability value from DB: " + value));
	}

}
