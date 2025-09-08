package com.medplus.lms.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class Book {
	private int bookId;
	@NotBlank(message = "Book Name is required....")
	@Size(max = 35, message = "Title cannot exceed 35 characters....")
	private String title;
	@NotBlank(message = "Author is required...")
	@Size(max = 35, message = "Author cannot exceed 35 characters...")
	private String author;
	private Category category;
	private Status status;
	private AvailabilityStatus availability;
	private String createdBy;
	private String updatedBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public boolean equalsForUpdate(Book other) {
		if (other == null)
			return false;
		return safeEquals(this.title, other.title) && safeEquals(this.author, other.author)
				&& safeEquals(this.category, other.category) && safeEquals(this.status, other.status)
				&& safeEquals(this.availability, other.availability);
	}

	private boolean safeEquals(Object a, Object b) {
		return a == null ? b == null : a.equals(b);
	}
}
