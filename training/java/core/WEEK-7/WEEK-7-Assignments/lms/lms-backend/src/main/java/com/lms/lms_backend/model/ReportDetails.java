package com.lms.lms_backend.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetails {
	private int issueId;
	private int memberId;
	private String memberName;
	private int bookId;
	private String bookTitle;
	private LocalDate issueDate;
}
