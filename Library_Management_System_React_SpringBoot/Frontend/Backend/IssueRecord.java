package com.library.Library.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "issue_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer issueId;

    private Integer bookId;
    private Long memberId;

    private char status; 
    private LocalDate issueDate;
    private LocalDate returnDate;
}
