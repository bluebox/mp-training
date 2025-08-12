package com.libraryManagementSystem.utilities;

import com.libraryManagementSystem.exceptions.InvalidEnumValueException;

public enum BookStatus {

	ACTIVE("Active", "A"), INACTIVE("Inactive", "I");
	;

	private String displayName;
	private String dbName;

	BookStatus(String displayName, String dbName) {
		this.displayName = displayName;
		this.dbName = dbName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDbName() {
		return dbName;
	}

	public static BookStatus fromDisplayName(String displayName) {
		for (BookStatus status : BookStatus.values()) {
			if (status.displayName.equalsIgnoreCase(displayName)) {
				return status;
			}
		}
		throw new InvalidEnumValueException("book status");
	}

	public static BookStatus fromDbName(String dbName) {
		for (BookStatus status : BookStatus.values()) {
			if (status.dbName.equalsIgnoreCase(dbName)) {
				return status;
			}
		}
		throw new InvalidEnumValueException("book status");
	}

}
