package com.example.Backend.domain;

import java.time.LocalDate;

import com.example.Backend.constants.IssueRecordStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRecord {
	private int issueId;
	private int bookId;
	private int memberId;
	private IssueRecordStatus status;
	private LocalDate issueDate;
	private LocalDate returnDate;

}
