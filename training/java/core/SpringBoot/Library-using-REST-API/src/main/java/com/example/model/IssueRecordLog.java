package com.example.model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class IssueRecordLog {
	private int issueId;
	private int bookId;
	private int memberId;
	private char status;
	private Date issueDate;
	private Date returnDate;
	private LocalDateTime time;
	public IssueRecordLog(int issueId,int bookId,int memberId,char status,Date issueDate,Date returnDate) {
		this.issueId=issueId;
		this.bookId=bookId;
		this.memberId=memberId;
		this.status=status;
		this.issueDate=issueDate;
		this.returnDate=returnDate;
		this.time=LocalDateTime.now();
	}
}
