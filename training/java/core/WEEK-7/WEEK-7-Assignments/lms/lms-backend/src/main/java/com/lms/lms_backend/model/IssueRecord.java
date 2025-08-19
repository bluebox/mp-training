package com.lms.lms_backend.model;

import java.time.LocalDate;
import java.util.Objects;

import com.lms.lms_backend.constant.IssueRecordStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class IssueRecord {
	private int IssueId;

	@Min(value = 1, message = "Invalid Book Id")
	@NotNull(message = "Book id is required")
	private Integer bookId;

	@Min(value = 1, message = "Invalid Member Id")
	@NotNull(message = "Member Id is required")
	private Integer memberId;

//	@NotNull(message = "Status is required")
	private IssueRecordStatus status;

	@PastOrPresent(message = "Issue Date must be before today")
	private LocalDate issueDate;

	@PastOrPresent(message = "return Date must be before today")
	private LocalDate returnDate;

	@Override
	public int hashCode() {
		return Objects.hash(IssueId, bookId, issueDate, memberId, returnDate, status);
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
		IssueRecord other = (IssueRecord) obj;
		return IssueId == other.IssueId && Objects.equals(bookId, other.bookId)
				&& Objects.equals(issueDate, other.issueDate) && Objects.equals(memberId, other.memberId)
				&& Objects.equals(returnDate, other.returnDate) && status == other.status;
	}

}