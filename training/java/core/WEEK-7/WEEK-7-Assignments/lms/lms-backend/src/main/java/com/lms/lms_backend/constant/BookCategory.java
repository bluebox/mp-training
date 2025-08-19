package com.lms.lms_backend.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.lms.lms_backend.exception.InvalidOptionException;

public enum BookCategory {
//	Defining all the category constants
	FICTION("Fiction"),
	NON_FICTION("Non Fiction"),
	MYSTERY("Mystery"),
	ROMANCE("Romance"),
	SCIENCE_FICTION("Science Fiction"),
	FANTASY("Fantasy"),
	HORROR("Horror");
	
	private String displayName;
	
//	Constructor
	BookCategory(String displayName) {
		this.displayName=displayName;
	}

//	Defining all the related functions
	@JsonValue
	public String getDisplayName() {
		return this.displayName;
	}
	
	@Override
	public String toString() {
		return this.displayName;
	}
	
	@JsonCreator
	public static BookCategory fromDisplayName(String displayName) {
		for (BookCategory category : BookCategory.values()) {
			if (category.displayName.equalsIgnoreCase(displayName)) {
				return category;
			}
		}
		throw new InvalidOptionException("Select Book Category From Options");
	}
}
