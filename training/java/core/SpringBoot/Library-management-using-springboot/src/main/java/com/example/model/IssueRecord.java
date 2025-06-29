package com.example.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IssueRecord {
	private int issueId;
	private long bookId;
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
