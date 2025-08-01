package com.libraryManagementSystem.domain;

import java.util.Objects;

import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.BookStatus;

public class Book {

	private int bookId;
	private String title;
	private String author;
	private BookCategory category;
	private BookStatus status;
	private BookAvailability availability;

	public Book(int bookId, String title, String author, BookCategory category, BookStatus status,
			BookAvailability availability) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.availability = availability;
	}

	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public BookCategory getCategory() {

		return category;
	}

	public BookStatus getStatus() {
		return status;
	}

	public BookAvailability getAvailability() {
		return availability;
	}

	@Override
	public String toString() {

		return title;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Book book = (Book) obj;

		return Objects.equals(book.getTitle(), title) && Objects.equals(book.getAuthor(), author)
				&& Objects.equals(book.getCategory(), category) && Objects.equals(book.getAvailability(), availability)
				&& Objects.equals(book.getStatus(), status);

	}

}
