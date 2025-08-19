package com.example.Backend.constants;

import java.util.stream.Stream;

public enum BookStatus {
//	Defining all the status constants
	ACTIVE("Active", "A"), INACTIVE("Inactive", "I");

	private String displayName;
	private String dbName;

//	Constructor
	BookStatus(String displayName, String dbName) {
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

	public static BookStatus getEnumConstant(String value) {
		return Stream.of(BookStatus.values()).filter(e -> e.getStringValue().equals(value)).findFirst().orElse(null);
	}
}
