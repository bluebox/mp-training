package com.libraryManagementSystem.domain;

import com.libraryManagementSystem.utilities.BookCategory;

public class CustomCategoryCount {
	private BookCategory category;
	private int count;

	public CustomCategoryCount(BookCategory category, int count) {
		super();
		this.category = category;
		this.count = count;
	}

	public BookCategory getCategory() {
		return category;
	}

	public int getCount() {
		return count;
	}
}
