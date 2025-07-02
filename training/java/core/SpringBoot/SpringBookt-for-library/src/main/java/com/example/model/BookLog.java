package com.example.model;

import java.time.LocalDateTime;

import com.library.enums.Availability;
import com.library.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookLog {
	private long bookId;
    private String title;
    private String author;
    private String category;
    private char status;
    private char availability;
    private LocalDateTime time;
    public BookLog(long bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = Status.Active.getStatus();
        this.availability = Availability.Available.getAvailability();
        this.time=LocalDateTime.now();
    }
}
