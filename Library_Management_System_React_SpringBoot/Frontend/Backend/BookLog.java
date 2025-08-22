package com.library.Library.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "books_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    private Integer bookId;
    private String Title;
    private String Author;
    private String Category;
    private char Status;
    private char Availability;
    private LocalDateTime updatedAt;
}
