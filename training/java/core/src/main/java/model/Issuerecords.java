package model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Issuerecords {

    private int IssueRecordId;

    @Min(value = 1, message = "Book ID must be a positive number")
    private int BookId;

    @Min(value = 1, message = "Member ID must be a positive number")
    private int MemberId;

    @NotNull(message = "Issue status must be provided")
    private IssueStatus status;

    @NotNull(message = "Issue date cannot be null")
    private LocalDate issueDate;

    private LocalDate ReturnDate;
}
