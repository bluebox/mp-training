package com.LibraryManagement.models;

import javafx.beans.property.*;

public class Book {
	private IntegerProperty bookId = new SimpleIntegerProperty();
	private StringProperty title = new SimpleStringProperty();
	private StringProperty author = new SimpleStringProperty();
	private StringProperty category = new SimpleStringProperty();
	private StringProperty status = new SimpleStringProperty();
	private StringProperty availability = new SimpleStringProperty();

	public Book(int bookId, String title, String author, String category, String status, String availability) {
		this.bookId.set(bookId);
		this.title.set(title);
		this.author.set(author);
		this.category.set(category);
		this.status.set(status);
		this.availability.set(availability);
	}

	public Book(String title, String author, String category) {
		this.title.set(title);
		this.author.set(author);
		this.category.set(category);
	}

	public int getBookId() {
		return bookId.get();
	}

	public String getTitle() {
		return title.get();
	}

	public String getAuthor() {
		return author.get();
	}

	public String getCategory() {
		return category.get();
	}

	public String getStatus() {
		return status.get();
	}

	public String getAvailability() {
		return availability.get();
	}

	public void setBookId(int bookId) {
		this.bookId.set(bookId);
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public void setAuthor(String author) {
		this.author.set(author);
	}

	public void setCategory(String category) {
		this.category.set(category);
	}

	public void setStatus(String status) {
		this.status.set(status);
	}

	public void setAvailability(String availability) {
		this.availability.set(availability);
	}

	public IntegerProperty bookIdProperty() {
		return bookId;
	}

	public StringProperty titleProperty() {
		return title;
	}

	public StringProperty authorProperty() {
		return author;
	}

	public StringProperty categoryProperty() {
		return category;
	}

	public StringProperty statusProperty() {
		return status;
	}

	public StringProperty availabilityProperty() {
		return availability;
	}
}