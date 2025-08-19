package com.lms.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;

    private String bookId;

    @NotBlank(message = "Book title cannot be blank")
    @Size(min = 2, max = 50, message = "Book title must be between 2 and 100 characters")
    @Pattern(regexp = "^[A-Za-z0-9 .,'!?:;\"()\\-]+$", 
             message = "Book title contains invalid characters")
    private String bookTitle;

    @NotBlank(message = "Book author cannot be blank")
    @Size(min = 3, max = 50, message = "Book author must be between 3 and 50 characters")
    @Pattern(regexp = "^[A-Za-z .]+$", 
             message = "Author name must only contain letters, spaces, and . characters")
    private String bookAuthor;

    @NotNull(message = "Book category must be selected")
    private BookCategory bookCategory;

    private String status;

    private String availability;

    public Book(String bookId, String bookTitle, String bookAuthor,
                BookCategory bookCategory, String status, String availability) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.status = status;
        this.availability = availability;
    }
}
