package com.library.library_management_system.domain;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class Return {

	@Min(value = 1, message = "Invalid Book Id")
	@NotNull(message = "Book id is required")
	private Integer bookId;

	@Min(value = 1, message = "Invalid Member Id")
	@NotNull(message = "Member Id is required")
	private Integer memberId;

	@NotNull(message = "issue Date is required")
	@PastOrPresent(message = "Issue Date must be before today")
	private LocalDate returnDate;

}
