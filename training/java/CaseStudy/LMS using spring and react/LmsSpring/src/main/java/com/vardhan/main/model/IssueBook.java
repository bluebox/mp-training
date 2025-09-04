package com.vardhan.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueBook {

    private Integer issueId;

    @NotNull(message = "Book ID is required")
    private String bookId;

    @NotNull(message = "Member ID is required")
    private Integer memberId;

    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;

    @NotNull(message = "Return date is required")
    private LocalDate returnDate;

    private LocalDate actualReturnDate;

    
    private String memberName;
    private String bookTitle;

  
    public IssueBook(String bookId, Integer memberId, LocalDate issueDate, LocalDate returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

   
    public boolean isReturned() {
        return actualReturnDate != null;
    }

    public boolean isOverdue() {
        if (isReturned()) {
            return actualReturnDate.isAfter(returnDate);
        }
        return LocalDate.now().isAfter(returnDate);
    }

    public long getDaysOverdue() {
        if (!isOverdue()) {
            return 0;
        }
        LocalDate compareDate = isReturned() ? actualReturnDate : LocalDate.now();
        return compareDate.toEpochDay() - returnDate.toEpochDay();
    }
}
