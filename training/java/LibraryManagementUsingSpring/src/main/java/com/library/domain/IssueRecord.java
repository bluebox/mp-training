package com.library.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecord {

	private int issueId;
	private int bookId;
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
