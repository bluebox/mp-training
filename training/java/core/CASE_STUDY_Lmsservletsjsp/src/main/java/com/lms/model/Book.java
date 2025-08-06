package com.lms.model;

public class Book {
	private String bookId;
	private String bookTitle;
	private String bookAuthor;
	private BookCategory bookCategory;
	private char status;
	private char availability;
	public Book() {
	}
	public Book(String bookId,String bookTitle, String bookAuthor, BookCategory bookCategory, char status,
			char availability) {
		this.bookId=bookId;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.status = status;
		this.availability = availability;

	}
	public Book(String bookTitle, String bookAuthor, BookCategory bookCategory, char status, char availability) {
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
		this.bookCategory = bookCategory;
		this.status = status;
		this.availability = availability;
	}

	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public BookCategory getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public char getAvailability() {
		return availability;
	}
	public void setAvailability(char availability) {
		this.availability = availability;
	}
	
	
}