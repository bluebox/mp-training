package com.example.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class IssueRecord {
	@NotNull(message="Issue ID is required")
	private int issueId;
	@NotNull(message="Book ID is required")
	private long bookId;
	@NotNull(message="Member ID is required")
	private int memberId;
	private char statusrec;
	private LocalDate issueDate;
	private LocalDate returnDate;
	public IssueRecord(int issueId,long bookId,int memberId,char statusrec) {
		this.issueId=issueId;
		this.bookId=bookId;
		this.memberId=memberId;
		this.statusrec=statusrec;
		this.issueDate=LocalDate.now();
		this.returnDate=LocalDate.now().plusDays(7);
	}
}
