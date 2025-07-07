package com.library.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class Book {

    private int bookId;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Pattern(regexp = "[A-Za-z ]+", message = "Author must contain only alphabets and spaces")
    @Size(max = 50, message = "Author cannot exceed 50 characters")
    private String author;

    @NotBlank(message = "Category is required")
    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;
    
    
    private Status status;
    private Availability available;
    
    
    public Book() {
    	this.available = Availability.AVAILABLE;
    }
    
	public Book(int bookId,
			@NotBlank(message = "Title is required") @Size(max = 100, message = "Title cannot exceed 100 characters") String title,
			@NotBlank(message = "Author is required") @Pattern(regexp = "[A-Za-z ]+", message = "Author must contain only alphabets and spaces") @Size(max = 50, message = "Author cannot exceed 50 characters") String author,
			@NotBlank(message = "Category is required") @Size(max = 50, message = "Category cannot exceed 50 characters") String category,
			Status status, Availability available) {
		
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.available = available;
	}
	
	
	public Book(
			@NotBlank(message = "Title is required") @Size(max = 100, message = "Title cannot exceed 100 characters") String title,
			@NotBlank(message = "Author is required") @Pattern(regexp = "[A-Za-z ]+", message = "Author must contain only alphabets and spaces") @Size(max = 50, message = "Author cannot exceed 50 characters") String author,
			@NotBlank(message = "Category is required") @Size(max = 50, message = "Category cannot exceed 50 characters") String category,
			Status status, Availability available) {
		
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.available = available;
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
	public Availability getAvailable() {
		return available;
	}
	public void setAvailable(Availability available) {
		this.available = available;
	}
    
    
    
    
}
