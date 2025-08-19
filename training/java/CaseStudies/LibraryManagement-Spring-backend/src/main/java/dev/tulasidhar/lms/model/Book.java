package dev.tulasidhar.lms.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	@NotNull int book_Id;
	@NotBlank(message = "Book title should not be empty")
	private String book_Title;
	
	@NotBlank(message= "Book Author should not be empty")
	private String book_Author;
	
	@NotBlank
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


	@Override
	public String toString() {
		return "Book [book_Id=" + book_Id + ", book_Title=" + book_Title + ", book_Author=" + book_Author
				+ ", book_Category=" + book_Category + ", book_Status=" + book_Status + ", book_Availability="
				+ book_Availability + "]";
	}
	
	
}
