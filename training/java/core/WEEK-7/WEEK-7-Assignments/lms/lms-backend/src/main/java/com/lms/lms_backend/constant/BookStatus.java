package com.lms.lms_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lms.lms_backend.exception.InvalidOptionException;

public enum BookStatus {
//	Defining all the status constants
	ACTIVE("Active", "A"),
	INACTIVE("Inactive", "I");
	
	private String displayName;
	private String dbName;

	BookStatus(String displayName, String dbName) {
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
	public static BookStatus fromDisplayName(String displayName) {
		for (BookStatus status : BookStatus.values()) {
			if (status.displayName.equalsIgnoreCase(displayName)) {
				return status;
			}
		}
		throw new InvalidOptionException("Select Book Status From Options");
	}

	public static BookStatus fromDbName(String dbName) {
		for (BookStatus status : BookStatus.values()) {
			if (status.dbName.equalsIgnoreCase(dbName)) {
				return status;
			}
		}
		throw new InvalidOptionException("Select Book Status From Options");
	}
}
