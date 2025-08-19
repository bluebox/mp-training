package com.lms.lms_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lms.lms_backend.exception.InvalidOptionException;

public enum MemberGender {
	MALE("Male", "M"), FEMALE("Female", "F");

	private String displayName;
	private String dbName;

	MemberGender(String displayName, String dbName) {
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
	public static MemberGender fromDisplayName(String displayName) {
		for (MemberGender gender : MemberGender.values()) {
			if (gender.displayName.equalsIgnoreCase(displayName)) {
				return gender;
			}
		}
		throw new InvalidOptionException("Select Member Gender From Options");
	}

	public static MemberGender fromDbName(String dbName) {
		for (MemberGender gender : MemberGender.values()) {
			if (gender.dbName.equalsIgnoreCase(dbName)) {
				return gender;
			}
		}
		throw new InvalidOptionException("Select Member Gender From Options");
	}
}
