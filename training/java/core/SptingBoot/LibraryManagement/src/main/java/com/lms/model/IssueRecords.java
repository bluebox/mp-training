package com.lms.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecords {
	
	@NotEmpty(message = "Issue Id cannot be Empty")
	private int issueId;
	
	@NotEmpty(message = "Book Id cannot be Empty")
	private int bookId;
	
	@NotEmpty(message = "Member Id cannot be Empty")
	private int memberId;
	
	@NotEmpty(message = "Status cannot be Empty")
	private String status;
	
	@NotEmpty(message = "Issue Date cannot be Empty")
	private LocalDate issueDate;
	
	@NotEmpty(message = "Return Date cannot be Empty")
	private LocalDate returnDate;
	

	public IssueRecords(int issueId, int bookId, int memberId, String status, LocalDate issueDate) {
		this(0, bookId, memberId, status, issueDate, null);
	}
}
