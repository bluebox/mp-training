package com.library.library_management_system.domain;

import java.util.Objects;

import com.library.library_management_system.utils.BookAvailability;
import com.library.library_management_system.utils.BookCategory;
import com.library.library_management_system.utils.BookStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Book {

	private int id;

	@NotBlank(message = "Title cannot be Empty")
	@Size(min = 3, max = 50, message = "Title must be between 3 and 50 length")
	@Pattern(regexp = "[a-zA-Z0-9 :\\-.'&/,?!+]{0,50}", message = "Title only contain aplhabates,numbers -.&,?!+")
	private String title;

	@NotBlank(message = "Author is required")
	@Size(min = 3, max = 50, message = "Author must be between 3 and 50 length")
	@Pattern(regexp = "[a-zA-Z .'-]{0,50}", message = "Author only contain aplhabates -.'")
	private String author;

	@NotNull(message = "Category is required")
	private BookCategory category;

	private BookStatus status;

	private BookAvailability availability;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && availability == other.availability && category == other.category
				&& status == other.status && Objects.equals(title, other.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, author, category, status, availability);
	}

}
