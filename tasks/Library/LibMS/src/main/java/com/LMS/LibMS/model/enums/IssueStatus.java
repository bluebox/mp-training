package com.LMS.LibMS.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum IssueStatus {
    ISSUED("I"),
    RETURNED("R");

    private final String code;

    public String getCode() {
		return code;
	}
   
    public static IssueStatus fromCode(String code) { 
        for (IssueStatus status : IssueStatus.values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
		return null;
    }
}