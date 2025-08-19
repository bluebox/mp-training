package com.lms.Models;



import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    
   
    private Integer bookId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @NotBlank(message = "Category is required")
    private String category;

    private String status;       
    private String availability; 

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
    }
}
