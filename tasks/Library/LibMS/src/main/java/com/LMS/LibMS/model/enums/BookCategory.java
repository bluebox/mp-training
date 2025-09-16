package com.LMS.LibMS.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BookCategory {
    FICTION("Fiction","F1"),
    SCIENCE("Science","S2"),
    HISTORY("History","H3"),
    BIOGRAPHY("Biography","B4"),
    TECHNOLOGY("Technology","T5"),
    FANTASY("Fantasy","F6"),
    MYSTERY("Mystery","M7"),
    THRILLER("Thriller","T8"),
    OTHER("Other","O9");

    private final String displayName;
    private final String code;
    
    public String getCode() {
    	return code;
    }
    
    public String getDisplayName() {
		return displayName;
	}

    public static BookCategory fromDisplayName(String code) {
        for (BookCategory category : BookCategory.values()) {
            if (category.code.equalsIgnoreCase(code)) {
                return category;
            }
        }
		return null;
    }

}