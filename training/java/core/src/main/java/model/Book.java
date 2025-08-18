package model;

import jakarta.persistence.*;   // for @Entity, @Table, @Id, @Column
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "books")   
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookId")
    private int bookId;

    @NotBlank(message = "Title cannot be empty")
    @Column(name = "Title", nullable = false)
    private String title;

    @NotBlank(message = "Author cannot be empty")
    @Column(name = "Author", nullable = false)
    private String author;

    @NotBlank(message = "Category cannot be empty")
    @Column(name = "Category", nullable = false)
    private String category;

    @NotNull(message = "Status must be provided")
    @Enumerated(EnumType.STRING) 
    @Column(name = "status")
    private BookStatus status;

    @NotNull(message = "Availability must be provided")
    @Enumerated(EnumType.STRING) 
    @Column(name = "Availablity") 
    private BookAvailability availablity;

}
