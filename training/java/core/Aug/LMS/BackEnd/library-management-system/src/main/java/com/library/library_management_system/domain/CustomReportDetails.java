package com.library.library_management_system.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CustomReportDetails {
	private int id;
	private int memberId;
	private String memberName;
	private int bookId;
	private String bookTitle;
	private LocalDate issueDate;
}
