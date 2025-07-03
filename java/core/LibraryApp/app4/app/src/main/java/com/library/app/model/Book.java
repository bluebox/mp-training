package com.library.app.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Book {
	 private int bookId;
	    @NotBlank(message="Title not to be Null!")
		@Size(min = 3, message = "Title has atleast 3 characters")
	    private String title;
	    @NotBlank(message="Author not to be Null!")
		@Size(min = 3, message = "Author has atleast 3 characters")
	    private String author;
	    @NotBlank(message="Category not to be Null!")
		@Size(min = 3, message = "Category has atleast 3 characters")
	    private String category;
	    @NotNull(message="Status not to be Null!")
	    private Status status;
	    @NotNull(message="Availability not to be Null!")
	    private Availability availability;

//	public int getBookId() {
//		return bookId;
//	}
//
//	public void setBookId(int bookId) {
//		this.bookId = bookId;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getAuthor() {
//		return author;
//	}
//
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//
//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}
//
//	public Status getStatus() {
//		return status;
//	}
//
//	public void setStatus(Status status) {
//		this.status = status;
//	}
//
//	public Availability getAvailability() {
//		return availability;
//	}
//
//	public void setAvailability(Availability availability) {
//		this.availability = availability;
//	}
//
	public String toString() {
		return "Book{" + "bookId=" + bookId + ", title='" + title + '\'' + ", author='" + author + '\'' + ", category='"
				+ category + '\'' + ", status=" + status + ", availability=" + availability + "}";
	}
}
