package com.lms.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
	
	@NotEmpty(message = "BookId cannot be Empty")
	private int bookId;
	
	@NotBlank(message = "Title cannot be blank")
	@Size(min = 3, message = "Title must not be less than 3 letters")
	private String title;
	
	@NotBlank(message = "Author cannot be blank")
	@Size(min = 3, message = "Author must not be less than 3 letters")
	private String author;
	
	@NotBlank(message = "Category cannot be blank")
	@Size(min = 3, message = "Category must not be less than 3 letters")
	private String category;
	
	@NotBlank(message = "Status cannot be blank")
	private String status;
	
	@NotBlank(message = "Availability cannot be blank")
	private String availability;
	
	public Book(String title, String author, String category) {
		this.title = title;
		this.author = author;
		this.category = category;
	}
}
