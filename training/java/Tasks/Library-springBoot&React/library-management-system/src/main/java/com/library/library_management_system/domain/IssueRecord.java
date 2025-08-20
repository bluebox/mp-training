package com.library.library_management_system.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecord {
	private int issueId;

	@Positive(message = "Book ID must be positive")
	private int bookId;

	@Positive(message = "Member ID must be positive")
	private int memberId;

	@NotBlank(message = "Status must not be blank")
	@Pattern(regexp = "I|R", message = "Status must be 'I' (Issued) or 'R' (Returned)")
	private String status;

	private LocalDate issueDate;
	private LocalDate returnDate;

	public IssueRecord(int bookId, int memberId) {
		this.bookId = bookId;
		this.memberId = memberId;
	}

}
