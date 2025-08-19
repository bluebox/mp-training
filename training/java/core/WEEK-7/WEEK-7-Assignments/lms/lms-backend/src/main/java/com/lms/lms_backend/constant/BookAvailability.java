package com.lms.lms_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lms.lms_backend.exception.InvalidOptionException;

public enum BookAvailability {
//	Defining all the status constants
	AVAILABLE("Available", "A"),
	ISSUED("Issued", "I");
	
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
		throw new InvalidOptionException("Select Book Availability From Options");
	}

	public static BookAvailability fromDbName(String dbName) {
		for (BookAvailability availability : BookAvailability.values()) {
			if (availability.dbName.equalsIgnoreCase(dbName)) {
				return availability;
			}
		}
		throw new InvalidOptionException("Select Book Availability From Options");
	}
}
