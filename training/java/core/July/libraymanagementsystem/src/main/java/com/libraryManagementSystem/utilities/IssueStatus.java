package com.libraryManagementSystem.utilities;

import com.libraryManagementSystem.exceptions.InvalidEnumValueException;

public enum IssueStatus {

	ISSUED("Issued", "I"), RETURNED("Returned", "R");

	private String displayName;
	private String dbName;

	IssueStatus(String displayName, String dbName) {
		this.displayName = displayName;
		this.dbName = dbName;
	}

	public String getDisplayName() {

		return displayName;
	}

	public String getDbName() {

		return dbName;
	}

	public static IssueStatus fromDisplayName(String displayName) {
		for (IssueStatus status : IssueStatus.values()) {
			if (status.displayName.equalsIgnoreCase(displayName)) {
				return status;
			}
		}
		throw new InvalidEnumValueException("issue status");
	}

	public static IssueStatus fromDbName(String dbName) {
		for (IssueStatus status : IssueStatus.values()) {
			if (status.dbName.equalsIgnoreCase(dbName)) {
				return status;
			}
		}
		throw new InvalidEnumValueException("issue status");
	}
}
