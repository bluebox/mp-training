package com.Models;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private String status; // A (Active), I (Inactive)
    private String availability; // A (Available), I (Issued)

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    

    public Book() {
		super();
	}

	public Book(int bookId, String title, String author, String category, String status, String availability) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.availability = availability;
	}
	public Book(String title, String author, String category, String status, String availability) {
		super();
		//this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.availability = availability;
	}
	public Book(int bookId, String title, String author, String category, String status) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		//this.availability = availability;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Book [ID=" + bookId + ", Title=" + title + ", Author=" + author + ", Category=" + category + ", Status=" + status + ", Availability=" + availability + "]";
    }
}
