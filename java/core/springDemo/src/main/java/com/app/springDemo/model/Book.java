package com.app.springDemo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Book {
	
	
	private int bookId;

    @NotBlank(message="Title must not be blank")
    @Size(min=3, message="Title must be at least 3 characters long")
	private String title;
    
    @NotBlank(message="Author must not be blank")
    @Size(min=3, message="Author must be at least 3 characters long")
	private String author;
    
    @NotBlank(message="Price must not be blank")
    @Size(min=1, message="Price must be at least 3 characters long")
	private String price;
}
