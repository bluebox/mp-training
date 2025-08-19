package com.lms.Models;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecords {

    private Integer issueId;

    @NotNull(message = "Book ID is required")
    private Integer bookId;

    @NotNull(message = "Member ID is required")
    private Integer memberId;

    @Pattern(regexp = "Available|Issued", message = "Availability must be 'Available' or 'Issued'")
    private String availability;

    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;

    private LocalDate returnDate; 

   
    public IssueRecords(Integer bookId, Integer memberId, String availability, LocalDate issueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.availability = availability;
        this.issueDate = issueDate;
    }
}

