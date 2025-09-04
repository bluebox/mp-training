package com.vardhan.main.model;

import lombok.Getter;

@Getter
public enum BookCategory {
    FICTION("Fiction"),
    NONFICTION("Non-Fiction"),
    MYSTERY("Mystery"),
    THRILLER("Thriller"),
    HORROR("Horror"),
    ROMANCE("Romance"),
    SCIENCEFICTION("Science Fiction"),
    FANTASY("Fantasy"),
    BIOGRAPHY("Biography"),
    HISTORY("History"),
    CHILDREN("Children"),
    EDUCATIONAL("Educational"),
    POETRY("Poetry"),
    COMICS("Comics"),
    SELFHELP("Self Help"),
	PROGRAMMING("Programming");

    private final String displayName;

    BookCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

 
    public static BookCategory fromString(String category) {
        for (BookCategory bc : BookCategory.values()) {
            if (bc.displayName.equalsIgnoreCase(category) || 
                bc.name().equalsIgnoreCase(category)) {
                return bc;
            }
        }
        throw new IllegalArgumentException("Unknown category: " + category);
    }
}
