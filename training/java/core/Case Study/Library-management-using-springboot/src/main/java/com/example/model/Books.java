package com.example.model;

import org.springframework.format.annotation.NumberFormat;

import com.library.enums.Availability;
import com.library.enums.Status;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class Books {
	@NotNull(message="Book ID is required")
	private long bookId;
	@NotNull(message="Book title is required")
    private String title;
	@NotNull(message="Book Author is required")
    private String author;
	@NotNull(message="Book category is required")
    private String category;
    private char status;
    private char availability;
    public Books(Long bookId2, String title, String author, String category) {
        this.bookId = bookId2;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = Status.Active.getStatus();
        this.availability = Availability.Available.getAvailability();
    }
//    public Books(Long bookId2, String title, String author, String category,char status,char availability) {
//        this.bookId = bookId2;
//        this.title = title;
//        this.author = author;
//        this.category = category;
//        this.status = status;
//        this.availability = availability;
//    }
}
