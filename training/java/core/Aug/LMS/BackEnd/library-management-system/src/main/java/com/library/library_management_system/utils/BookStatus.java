package com.library.library_management_system.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.library.library_management_system.exception.InvalidDataException;

public enum BookStatus {

	ACTIVE("Active", "A"), INACTIVE("Inactive", "I");

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
		throw new InvalidDataException("Book Select Status from Options");
	}

	public static BookStatus fromDbName(String dbName) {
		for (BookStatus status : BookStatus.values()) {
			if (status.dbName.equalsIgnoreCase(dbName)) {
				return status;
			}
		}
		throw new InvalidDataException("Book Select Status from Options");
	}

}
