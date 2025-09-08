package dev.kaushik.library.validator;

import java.time.LocalDateTime;

import dev.kaushik.library.exception.LibraryException;
import dev.kaushik.library.model.IssueRecord;

public class IssueValidator {
	public static void validate(IssueRecord issue) {
		if (issue == null) {
			throw new LibraryException("Issue Record cannot be null");
		}
		if (issue.getBookId() == null || issue.getBookId() <= 0) {
			throw new LibraryException("Book Id can only be positive");
		}
		if (issue.getMemberId() == null || issue.getMemberId() <= 0) {
			throw new LibraryException("Member Id can only be positive");
		}
		if (issue.getStatus() == null) {
			throw new LibraryException("Issue Status can't be null");
		}
		if (issue.getIssuedBy() == null || issue.getIssuedBy().isBlank()) {
			throw new LibraryException("Issuer Name can't be null or blank");
		}
		int nameLength = issue.getIssuedBy().trim().length();
		if (nameLength < 2 || nameLength > 100) {
			throw new LibraryException("issuer name must be between 2 and 100 characters");
		}

		if (issue.getIssueDate() == null) {
			throw new LibraryException("issue date cannot be null");
		}
		if (!issue.getIssueDate().isBefore(LocalDateTime.now())) {
			throw new LibraryException("issue date cannot be in future");
		}

		if (issue.getReturnDate() != null && !issue.getReturnDate().isBefore(LocalDateTime.now())) {
			throw new LibraryException("issue date cannot be in future");
		}

		if (issue.getReturnedBy() != null) {
			int returnedByLength = issue.getReturnedBy().trim().length();
			if (returnedByLength < 2 || returnedByLength > 100) {
				throw new LibraryException("'returned by' must be between 2 and 100 characters");
			}
		}
	}
}
