package com.library.library_management_system.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@NoArgsConstructor 
@AllArgsConstructor
public class Book {
	
	private int bookId;
	
	@NotBlank(message = "Title must not be blank")
    @Size(min = 3, max = 60, message = "Title must be between 3 and 60 characters")
	private String title;
	
	@NotBlank(message = "Author must not be blank")
    @Size(min = 3, max = 60, message = "Author must be between 3 and 60 characters")
	private String author;
	
	@NotBlank(message = "Category must not be blank")
	private String category;
	
	private String status; 
	private String availability;
	
	public Book(String title, String author, String category) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = "A";
		this.availability = "A";
	}
}
