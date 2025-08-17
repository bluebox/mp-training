package com.lms.springbootlms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private BookCategory bookCategory;
    private char status;
    private char availability;
}
