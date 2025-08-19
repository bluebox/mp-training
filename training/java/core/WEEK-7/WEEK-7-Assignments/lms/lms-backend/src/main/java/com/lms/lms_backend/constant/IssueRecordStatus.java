package com.lms.lms_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lms.lms_backend.exception.InvalidOptionException;

public enum IssueRecordStatus {
//	Defining all the status constants
	ISSUED("Issued", "I"),
	RETURNED("Returned", "R");
	
	private String displayName;
	private String dbName;

	IssueRecordStatus(String displayName, String dbName) {
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
	public static IssueRecordStatus fromDisplayName(String displayName) {
		for (IssueRecordStatus status : IssueRecordStatus.values()) {
			if (status.displayName.equalsIgnoreCase(displayName)) {
				return status;
			}
		}
		throw new InvalidOptionException("Select Issue Record Status From Options");
	}

	public static IssueRecordStatus fromDbName(String dbName) {
		for (IssueRecordStatus status : IssueRecordStatus.values()) {
			if (status.dbName.equalsIgnoreCase(dbName)) {
				return status;
			}
		}
		throw new InvalidOptionException("Select Issue Record Status From Options");
	}
}
