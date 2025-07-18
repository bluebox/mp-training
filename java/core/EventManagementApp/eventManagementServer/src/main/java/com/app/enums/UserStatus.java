package com.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
	ACTIVE("A"), INACTIVE("I");

	private final String code;

	UserStatus(String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return code;
	}

	@JsonCreator
	public static UserStatus fromCode(String code) {
		for (UserStatus userStatus : values()) {
			if (userStatus.code.equalsIgnoreCase(code)) {
				return userStatus;
			}
		}
		throw new IllegalArgumentException("Unknown UserStatus: " + code);
	}
}


