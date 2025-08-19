package com.example.library.constants;

import java.util.stream.Stream;

public enum BookStatus {
	ACTIVE("Active", "A"), INACTIVE("Inactive", "I");

	private String displayName;
	private String dbName;

	BookStatus(String displayName, String dbName) {
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

	public static BookStatus getEnumConstant(String value) {
	    return Stream.of(BookStatus.values())
	                 .filter(e -> e.getStringValue().equals(value))
	                 .findFirst()
	                 .orElseThrow(() -> 
	                     new IllegalArgumentException("Invalid status value from DB: " + value));
	}

}
