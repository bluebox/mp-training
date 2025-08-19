package com.example.library.domain;

import com.example.library.constants.BookAvailability;
import com.example.library.constants.BookCategory;
import com.example.library.constants.BookStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	private Integer bookId;

	@NotBlank(message = "Title is required")
	@Size(max = 100, message = "Title cannot exceed 100 characters")
	private String title;

	@NotBlank(message = "Author name is required")
	@Size(max = 60, message = "Author name cannot exceed 60 characters")
	private String author;

	@NotNull(message = "Category is required")
	private BookCategory category;
	
	@NotNull(message = "Status is required")
	private BookStatus status = BookStatus.ACTIVE;
	
	@NotNull(message = "Availability is required")
	private BookAvailability availability = BookAvailability.AVAILABLE;

}
