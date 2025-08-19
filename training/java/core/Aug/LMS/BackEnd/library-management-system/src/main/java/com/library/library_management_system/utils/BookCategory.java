package com.library.library_management_system.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.library.library_management_system.exception.InvalidDataException;

public enum BookCategory {

	SCIENCE("Science"), HISTORY("History"), TECHNOLOGY("Technology"), BIOGRAPHY("Biography"), MYSTERY("Mystery"),
	ROMANCE("Romance"), HORROR("Horror"), COMICS("Comics"), EDUCATION("Education");

	private String displayName;

	BookCategory(String displayName) {
		this.displayName = displayName;
	}

	@JsonValue
	public String getCategory() {
		return displayName;
	}

	@JsonCreator
	public static BookCategory fromDisplayName(String displayName) {
		for (BookCategory category : BookCategory.values()) {
			if (category.displayName.equalsIgnoreCase(displayName)) {
				return category;
			}
		}
		throw new InvalidDataException("Book Select Category from Options");
	}

}
