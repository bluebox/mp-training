package com.lms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookCategory; 
    private String status;
    private String availability;
}
