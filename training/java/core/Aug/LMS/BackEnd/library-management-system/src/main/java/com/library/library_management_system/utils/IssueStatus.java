package com.library.library_management_system.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.library.library_management_system.exception.InvalidDataException;

public enum IssueStatus {

	ISSUED("Issued", "I"), RETURNED("Returned", "R");

	private String displayName;
	private String dbName;

	IssueStatus(String displayName, String dbName) {
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
	public static IssueStatus fromDisplayName(String displayName) {
		for (IssueStatus status : IssueStatus.values()) {
			if (status.displayName.equalsIgnoreCase(displayName)) {
				return status;
			}
		}
		throw new InvalidDataException("Select Issue Status from Options");
	}

	public static IssueStatus fromDbName(String dbName) {
		for (IssueStatus status : IssueStatus.values()) {
			if (status.dbName.equalsIgnoreCase(dbName)) {
				return status;
			}
		}
		throw new InvalidDataException("Select Issue Status from Options");
	}
}
