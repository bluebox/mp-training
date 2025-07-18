package com.app.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("M"),
    FEMALE("F"),
    OTHER("O");

    private final String code;

    Gender(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static Gender fromCode(String code) {
        for (Gender gender: values()) {
            if (gender.code.equalsIgnoreCase(code)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown Gender: " + code);
    }
}


//package com.app.enums;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonValue;
//
//public enum Gender {
//    MALE("M"),
//    FEMALE("F"),
//    OTHER("O");
//
//    private final String code;
//
//    Gender(String code) {
//        this.code = code;
//    }
//
//    @JsonValue
//    public String getCode() {
//        return code;
//    }
//
//    @JsonCreator
//    public static Gender fromCode(String code) {
//        if (code == null) return null;
//        switch (code.toUpperCase()) {
//            case "M":
//                return MALE;
//            case "F":
//                return FEMALE;
//            case "O":
//                return OTHER;
//            default:
//                throw new IllegalArgumentException("Invalid gender code: " + code);
//        }
//    }
//}
