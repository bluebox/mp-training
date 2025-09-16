package com.LMS.LibMS.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.LMS.LibMS.model.enums.BookAvailability;
import com.LMS.LibMS.model.enums.BookCategory;
import com.LMS.LibMS.model.enums.BookStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	
	private Integer bookId;
	
	@NotBlank(message="Title must not be blank")
	@Size(min=5,max=20, message="Title must be at least 5 and at most 20 characters long")
	private String title;
	
	@NotBlank(message="Author must not be blank")
	@Size(min=5,max=20, message="Author must be at least 5 and at most 20 characters long")
	private String author;
	

	private BookCategory category;
	private BookStatus status;
	private BookAvailability availability;
	
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private LocalDateTime updatedAt;
	
	private String updatedBy;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) 
				&& availability == other.availability
				&& Objects.equals(bookId, other.bookId) 
				&& category == other.category
				&& status == other.status 
				&& Objects.equals(title, other.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, availability, bookId, category, status, title);
	}
		
}
