package com.casestudy.spring.library.beans;

public enum Status {ACTIVE("A"),INACTIVE("I");
	private final String code;

    // Constructor
    Status(String code) {
        this.code = code;
    }

    // Method 1: Returns "A" or "I"
    public String getCode() {
        return code;
    }

    // Method 2: Returns Status enum from string code (e.g., "A" → ACTIVE)
    public static Status fromCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Code cannot be null");
        }

        switch (code.toUpperCase()) {
            case "A":
                return ACTIVE;
            case "I":
                return INACTIVE;
            default:
                throw new IllegalArgumentException("Invalid gender code: " + code);
        }
    }


}

