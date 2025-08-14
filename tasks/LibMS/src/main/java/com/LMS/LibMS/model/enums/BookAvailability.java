package com.LMS.LibMS.model.enums;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public enum BookAvailability {
    AVAILABLE("A"),
    ISSUED("I");

    private final String code;


    public static BookAvailability fromCode(String code) { 
        for (BookAvailability availability : BookAvailability.values()) {
            if (availability.code.equalsIgnoreCase(code)) {
                return availability;
            }
        }
        throw new IllegalArgumentException("No book availability with code: " + code);
    }
    
}