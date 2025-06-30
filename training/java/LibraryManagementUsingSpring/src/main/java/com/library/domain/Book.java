package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
    private int bookId;
    private String title;
    private String author;
    private String category;
    private Status status;
    private Availability available;

}
