package com.casestudy;

public enum Availability {AVAILABLE("A"),ISSUED("I");
	private final String code;

    // Constructor
	Availability(String code) {
        this.code = code;
    }

    // Method 1: Returns "A" or "I"
    public String getCode() {
        return code;
    }

    // Method 2: Returns Availablity enum from string code (e.g., "A" → AVAILABLE)
    public static Availability fromCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Code cannot be null");
        }

        switch (code.toUpperCase()) {
            case "A":
                return AVAILABLE;
            case "I":
                return ISSUED;
            default:
                throw new IllegalArgumentException("Invalid gender code: " + code);
        }
    }

}
