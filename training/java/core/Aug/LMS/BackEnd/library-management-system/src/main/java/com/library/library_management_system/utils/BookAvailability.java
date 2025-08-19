package com.library.library_management_system.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.library.library_management_system.exception.InvalidDataException;

public enum BookAvailability {
	AVAILABLE("Available", "A"), ISSUED("Issued", "I");

	private String displayName;
	private String dbName;

	BookAvailability(String displayName, String dbName) {
		this.displayName = displayName;
		this.dbName = dbName;
	}

	@JsonValue
	public String getDisplayName() {
		return displayName;
	}

	public String getDbName() {
		return dbName;
	}

	@JsonCreator
	public static BookAvailability fromDisplayName(String displayName) {

		for (BookAvailability availability : BookAvailability.values()) {
			if (availability.displayName.equalsIgnoreCase(displayName)) {
				return availability;
			}
		}
		throw new InvalidDataException("Book Select Availability from Options");
	}

	public static BookAvailability fromDbName(String dbName) {
		for (BookAvailability availability : BookAvailability.values()) {
			if (availability.dbName.equalsIgnoreCase(dbName)) {
				return availability;
			}
		}
		throw new InvalidDataException("Book Select Availability from Options");
	}

}
