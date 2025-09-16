package com.LMS.LibMS.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {
    MALE("M"),
    FEMALE("F"),
    OTHER("O");

    private final String code;

    public String getCode() {
		return code;
	}

    public static Gender fromCode(String code) {
        for (Gender gender : Gender.values()) {
            if (gender.code.equalsIgnoreCase(code)) {
                return gender;
            }
        }
		return null;
    }
}