package com.library.domain;



public class Book {

	private int bookId;
	private String title;
	private String author;
	private String category;
	private Status status;
	private Availability available;

	public Book(int bookId, String title, String author, String category, Status status, Availability available) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.available = available;
	}

	public Book(String title, String author, String category, Status status, Availability available) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.available = available;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", category=" + category
				+ ", status=" + status + ", available=" + available + "]";
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
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

	public Availability getAvailable() {
		return available;
	}

	public void setAvailable(Availability available) {
		this.available = available;
	}

}
