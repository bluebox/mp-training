package com.example.library.domain;

import java.time.LocalDate;

import com.example.library.constants.IssueRecordStatus;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRecord {
	
	@NotNull(message = "Issue ID cannot be null")
	private Integer issueId;

	@NotNull(message = "Book ID cannot be null")
	private Integer bookId;

	@NotNull(message = "Member ID cannot be null")
	private Integer memberId;

	@NotNull(message = "Status must be provided")
	private IssueRecordStatus status;

	@NotNull(message = "Issue date is required")
	@PastOrPresent(message = "Issue date cannot be in the future")
	private LocalDate issueDate;

	@FutureOrPresent(message = "Return date cannot be in the past")
	private LocalDate returnDate;

}
