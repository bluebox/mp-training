package com.library.Library.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "issue_records_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecordLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    private Integer issueId;
    private Integer bookId;
    private Long memberId;

    private char status;
    private LocalDate issueDate;
    private LocalDate returnDate;

    private LocalDateTime logDate = LocalDateTime.now();
}
