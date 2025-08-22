package com.library.Library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookId")
    private Integer id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    @Column(name = "Title", nullable = false)
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min = 2, max = 255, message = "Author must be between 2 and 255 characters")
    @Column(name = "Author", nullable = false)
    private String author;

    @NotBlank(message = "Category is required")
    @Size(max = 100, message = "Category must be at most 100 characters")
    @Column(name = "Category", nullable = false)
    private String category;

    @NotNull(message = "Status is required")
    @Column(name = "Status", nullable = false, length = 1)
    private char status;   // A (Active) / I (Inactive)

    @NotNull(message = "Availability is required")
    @Column(name = "Availability", nullable = false, length = 1)
    private char availability; // A (Available) / I (Issued)
}
