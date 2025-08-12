package com.libraryManagementSystem.utilities;

import com.libraryManagementSystem.exceptions.InvalidEnumValueException;

public enum MemberGender {
	MALE("Male", "M"), FEMALE("Female", "F"), OTHERS("Others", "O");

	private String displayName;
	private String dbName;

	MemberGender(String displayName, String dbName) {
		this.displayName = displayName;
		this.dbName = dbName;

	}

	public String getDisplayName() {
		return displayName;
	}

	public String getDbName() {
		return dbName;
	}

	public static MemberGender fromDisplayName(String displayName) {
		for (MemberGender gender : MemberGender.values()) {
			if (gender.displayName.equalsIgnoreCase(displayName)) {
				return gender;
			}
		}
		throw new InvalidEnumValueException("member status");
	}

	public static MemberGender fromDbName(String dbName) {
		for (MemberGender gender : MemberGender.values()) {
			if (gender.dbName.equalsIgnoreCase(dbName)) {
				return gender;
			}
		}
		throw new InvalidEnumValueException("member status");
	}

}
