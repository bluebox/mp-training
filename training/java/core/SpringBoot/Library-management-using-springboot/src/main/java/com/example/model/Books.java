package com.example.model;

import com.library.enums.Availability;
import com.library.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Books {
	private long bookId;
    private String title;
    private String author;
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
//        this.status = this.status;
//        this.availability = this.availability;
//    }
}
