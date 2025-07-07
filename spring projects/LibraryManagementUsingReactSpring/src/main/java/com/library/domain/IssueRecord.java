package com.library.domain;
import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecord {
	
	
	private int issueId;
	
	@NotNull
	@Min(1)
	private int bookId;
	
	@NotNull
	@Min(1)
	private int memberId;
	private RecordStatus status; // 'I' or 'R'
	private LocalDate issueDate;
	private LocalDate returnDate;

	public IssueRecord(int bookId, int memberId) {

		this.bookId = bookId;
		this.memberId = memberId;
		this.issueDate = LocalDate.now();

	}

}
