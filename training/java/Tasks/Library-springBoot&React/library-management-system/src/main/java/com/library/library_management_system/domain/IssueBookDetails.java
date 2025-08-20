package com.library.library_management_system.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueBookDetails {
		private int memberId;
		private String memberName;
		private int bookId;
		private String bookTitle;
		private String issueStatus;
		private LocalDate issueDate;
		private LocalDate returnDate;
		private String bookStatus;
}
