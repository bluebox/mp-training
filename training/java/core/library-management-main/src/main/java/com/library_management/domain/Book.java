package com.library_management.domain;

public class Book {
	// All fields of a book
		private int bookId;
		private String title;
		private String author;
		private BookCategory category;
		private BookStatus status;
		private BookAvailability availability;
		
//		Constructors
		public Book(String title, String author, BookCategory category) {
			this(title,author,category,BookStatus.ACTIVE,BookAvailability.AVAILABLE);
		}
		
		public Book(String title, String author, BookCategory category, BookStatus status, BookAvailability availability) {
			this.title=title;
			this.author=author;
			this.category=category;
			this.status=status;
			this.availability=availability;
		}
		
		public Book(int id, String title, String author, BookCategory category, BookStatus status, BookAvailability availability) {
			this.bookId=id;
			this.title=title;
			this.author=author;
			this.category=category;
			this.status=status;
			this.availability=availability;
		}
		
		public Book(int id, String title, String author, BookCategory category, BookStatus status) {
			this.bookId=id;
			this.title=title;
			this.author=author;
			this.category=category;
			this.status=status;
		}
		
		public Book(int bookId,String category) {
			this.bookId=bookId;
			this.category=BookCategory.getEnumConstant(category);
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

		public BookCategory getCategory() {
			return category;
		}

		public void setCategory(BookCategory category) {
			this.category = category;
		}

		public BookStatus getStatus() {
			return status;
		}

		public void setStatus(BookStatus status) {
			this.status = status;
		}

		public BookAvailability getAvailability() {
			return availability;
		}

		public void setAvailability(BookAvailability availability) {
			this.availability = availability;
		}

		
		
}
