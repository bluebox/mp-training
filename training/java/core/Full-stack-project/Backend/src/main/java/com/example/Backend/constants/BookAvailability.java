package com.example.Backend.constants;

import java.util.stream.Stream;

public enum BookAvailability {
//	Defining all the status constants
	AVAILABLE("Available", "A"), ISSUED("Issued", "I");

	private String displayName;
	private String dbName;

//	Constructor
	BookAvailability(String displayName, String dbName) {
		this.displayName = displayName;
		this.dbName = dbName;
	}

//	Defining all the related functions
	public String getStringValue() {
		return this.dbName;
	}

	@Override
	public String toString() {
		return this.displayName;
	}

	public static BookAvailability getEnumConstant(String value) {
		return Stream.of(BookAvailability.values()).filter(e -> e.getStringValue().equals(value)).findFirst()
				.orElse(null);
	}
}
