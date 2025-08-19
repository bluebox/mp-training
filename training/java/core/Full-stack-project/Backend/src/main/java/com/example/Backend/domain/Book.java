package com.example.Backend.domain;

import com.example.Backend.constants.BookAvailability;
import com.example.Backend.constants.BookCategory;
import com.example.Backend.constants.BookStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	private int bookId;

	@NotBlank(message = "please enter Title")
	@Size(min = 1, max = 60, message = "length of the author name must be greater than 3 and lessthan 60")
	private String title;

	@NotBlank(message = "please enter author name")
	@Size(min = 3, max = 60, message = "author name length is must be greater than 3 and lessthan 60")
	private String author;

	@NotNull(message = "Please select Category")
	private BookCategory category;
	private BookStatus status = BookStatus.ACTIVE;
	private BookAvailability availability = BookAvailability.AVAILABLE;
}
