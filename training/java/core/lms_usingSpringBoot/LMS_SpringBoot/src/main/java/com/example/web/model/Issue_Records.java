package com.example.web.model;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue_Records {
	private int issueId;
	
	@NotNull(message="Book should not be empty")
	private int bookId;
	@NotNull(message="member should not be empty")
	private int memberId;
	private String status;
	private Date issueDate;
	private Date returnDate;
	
	public Issue_Records(int bookId, int memberId) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = RecordStatus.Issued.getCode();
		this.issueDate = new Date(new java.util.Date().getTime());
		this.returnDate = null;
	}
}
