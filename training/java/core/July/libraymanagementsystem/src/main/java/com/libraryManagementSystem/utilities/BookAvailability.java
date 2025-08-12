package com.libraryManagementSystem.utilities;

import com.libraryManagementSystem.exceptions.InvalidEnumValueException;

public enum BookAvailability {
	AVAILABLE("Available", "A"), ISSUED("Issued", "I");

	private String displayName;
	private String dbName;

	BookAvailability(String displayName, String dbName) {
		this.displayName = displayName;
		this.dbName = dbName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDbName() {
		return dbName;
	}

	public static BookAvailability fromDisplayName(String displayName) {
		for (BookAvailability availability : BookAvailability.values()) {
			if (availability.displayName.equalsIgnoreCase(displayName)) {
				return availability;
			}
		}
		throw new InvalidEnumValueException("book category");
	}

	public static BookAvailability fromDbName(String dbName) {
		for (BookAvailability availability : BookAvailability.values()) {
			if (availability.dbName.equalsIgnoreCase(dbName)) {
				return availability;
			}
		}
		throw new InvalidEnumValueException("book category");
	}

}
