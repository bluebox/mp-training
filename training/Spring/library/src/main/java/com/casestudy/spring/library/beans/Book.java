package com.casestudy.spring.library.beans;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private int bookId;
	@NotBlank
    @Size(min = 3, max = 50, message = "The title should be between 3 and 50 chars")
	private String title;
	@NotBlank
    @Size(min = 3, max = 50, message = "The author name should be between 3 and 50 chars")
	@Pattern(regexp = "^(?!.*  )[A-Za-z ]+$",message = "Author Name Must contain only letters and spaces, and no consecutive spaces")
	private String author;
	@NotBlank
    @Size(min = 3, max = 20, message = "The category should be between 3 and 20 chars")
	@Pattern(regexp = "^(?!.*  )[A-Za-z ]+$",
    message = "Category Must contain only letters and spaces, and no consecutive spaces")
	private String category;
	private Status status;
	private Availability available;

//	public Book(int bookId, String title, String author, String category, Status status, Availability available) {
//		this.bookId = bookId;
//		this.title = title;
//		this.author = author;
//		this.category = category;
//		this.status = status;
//		this.available = available;
//	}

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

//	public Integer getBookId() {
//		return bookId;
//	}
//
//	public void setBookId(Integer bookId) {
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
//	public Availability getAvailable() {
//		return available;
//	}
//
//	public void setAvailable(Availability available) {
//		this.available = available;
//	}

}
