package com.LMS.LibMS.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.LMS.LibMS.model.enums.IssueStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRecord {

	@Id
	private Integer issueId;
	
	private Integer bookId;
	
	private Integer memberId;
	
	private IssueStatus status; 
	
	private LocalDateTime issueDate;
	
	private String issuedBy;
	
	private LocalDateTime returnDate;
	
	private String returnedBy;

	public IssueRecord(Integer bookId, Integer memberId, IssueStatus status, LocalDateTime issueDate, String issuedBy) {
		this.bookId = bookId;
		this.memberId = memberId;
		this.status = status;
		this.issueDate = issueDate;
		this.issuedBy = issuedBy;
	}
}