package com.library.library_management_system.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.library.library_management_system.exception.InvalidDataException;

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
		throw new InvalidDataException("Member Select Status from Options");
	}

	public static MemberGender fromDbName(String dbName) {
		for (MemberGender gender : MemberGender.values()) {
			if (gender.dbName.equalsIgnoreCase(dbName)) {
				return gender;
			}
		}
		throw new InvalidDataException("Member Select Status from Options");
	}
}
