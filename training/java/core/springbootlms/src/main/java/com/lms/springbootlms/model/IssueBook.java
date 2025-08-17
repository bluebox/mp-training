package com.lms.springbootlms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueBook {
    private int issueId;
    private String bookId;
    private int memberId;
    private char status;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate;
}
