package com.lms.lms_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lms.lms_backend.exception.InvalidOptionException;

public enum MemberStatus {

	ACTIVE("Active", "A"), INACTIVE("Inactive", "I");

	private String displayName;
	private String dbName;

	MemberStatus(String displayName, String dbName) {
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
	public static MemberStatus fromDisplayName(String displayName) {
		for (MemberStatus status : MemberStatus.values()) {
			if (status.displayName.equalsIgnoreCase(displayName)) {
				return status;
			}
		}
		throw new InvalidOptionException("Select Member Status from Options");
	}

	public static MemberStatus fromDbName(String dbName) {
		for (MemberStatus status : MemberStatus.values()) {
			if (status.dbName.equalsIgnoreCase(dbName)) {
				return status;
			}
		}
		throw new InvalidOptionException("Select Member Status from Options");
	}
}
