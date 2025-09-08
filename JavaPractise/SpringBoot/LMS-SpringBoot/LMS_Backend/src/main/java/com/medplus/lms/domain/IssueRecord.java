package com.medplus.lms.domain;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class IssueRecord {
	private int issueId;
	private int bookId;
	private int memberId;
	private IssueStatus status;
	private LocalDateTime issueDate;
	private LocalDateTime returnDate;
	private String issuedBy;
	private String returnedTo;
}
