package com.library.dao;

import com.library.enums.Availability;
import com.library.enums.Status;

public class Book {
	private int bookId;
	private String title;
    private String author;
    private String category;
    private Status status;
    private Availability availability;
    public Book(int bookId, String title, String author, String category) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = Status.Active;
		this.availability = Availability.Available;
	}
	
	public Book(int bookId, String title, String author, String category, Status status, Availability availability) {
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
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Availability getAvailability() {
		return availability;
	}
	public void setAvailability(Availability availability) {
		this.availability = availability;
	}
    @Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", category=" + category
				+ ", status=" + status + ", availability=" + availability + "]";
	}
}
