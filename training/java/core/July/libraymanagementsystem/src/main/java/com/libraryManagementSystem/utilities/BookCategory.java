package com.libraryManagementSystem.utilities;

import com.libraryManagementSystem.exceptions.InvalidEnumValueException;

public enum BookCategory {

	SCIENCE("Science"), HISTORY("History"), TECHNOLOGY("Technology"), BIOGRAPHY("Biography"), MYSTERY("Mystery"),
	ROMANCE("Romance"), HORROR("Horror"), COMICS("Comics"), EDUCATION("Education");

	private String displayName;

	BookCategory(String displayName) {
		this.displayName = displayName;
	}

	public String getCategory() {
		return displayName;
	}

	public static BookCategory fromDisplayName(String displayName) {
		for (BookCategory category : BookCategory.values()) {
			if (category.displayName.equalsIgnoreCase(displayName)) {
				return category;
			}
		}
		throw new InvalidEnumValueException("book category");
	}

}
