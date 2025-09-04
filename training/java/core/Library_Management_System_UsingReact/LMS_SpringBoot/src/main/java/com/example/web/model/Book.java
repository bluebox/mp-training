package com.example.web.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
	@Id
	private int book_Id;
	@NotNull(message="Book Title should not be null")
	@NotBlank(message = "Book Title should not be empty")
	@Pattern(regexp = "^[A-Za-z]{2}[A-Za-z\\s]{0,218}$",message="Please enter a valid Title")
	private String book_Title;
	@NotNull(message="Author Name should not be null")
	@NotBlank(message = "Book Author should not be empty")
	@Pattern(regexp = "^[A-Za-z]{2}[A-Za-z\\s]{0,218}$",message="Please enter a valid Author Name")
	private String book_Author;
	@NotNull(message="Book Category should not be null")
	@NotBlank(message = "Book Category should not be empty")
	private String book_Category;
	private char book_Status;
	private char book_Availability;

	public Book(String book_Title, String book_Author, String book_Category) {
		this.book_Title=book_Title;
		this.book_Author=book_Author;
		this.book_Category=book_Category;
		this.book_Status=BookStatus.ACTIVE.getCode();
		this.book_Availability=BookAvailability.AVAILABLE.getCode();
	}
}
