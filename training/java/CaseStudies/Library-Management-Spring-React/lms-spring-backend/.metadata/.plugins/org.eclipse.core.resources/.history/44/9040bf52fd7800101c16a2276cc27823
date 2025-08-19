package dev.tulasidhar.lms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	private int book_Id;
	private String book_Title;
	private String book_Author;
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
