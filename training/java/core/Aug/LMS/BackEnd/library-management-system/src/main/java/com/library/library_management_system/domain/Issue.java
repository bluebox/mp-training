package com.library.library_management_system.domain;

import java.time.LocalDate;
import java.util.Objects;

import com.library.library_management_system.utils.IssueStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class Issue {
	private int id;

	@Min(value = 1, message = "Invalid Book Id")
	@NotNull(message = "Book id is required")
	private Integer bookId;

	@Min(value = 1, message = "Invalid Member Id")
	@NotNull(message = "Member Id is required")
	private Integer memberId;

	private IssueStatus status;

	@NotNull(message = "issue Date is required")
	@PastOrPresent(message = "Issue Date must be before today")
	private LocalDate issueDate;

	@PastOrPresent(message = "return Date must be before today")
	private LocalDate returnDate;

	@Override
	public int hashCode() {
		return Objects.hash(id, bookId, issueDate, memberId, returnDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Issue other = (Issue) obj;
		return id == other.id && Objects.equals(bookId, other.bookId) && Objects.equals(issueDate, other.issueDate)
				&& Objects.equals(memberId, other.memberId) && Objects.equals(returnDate, other.returnDate)
				&& status == other.status;
	}

}
