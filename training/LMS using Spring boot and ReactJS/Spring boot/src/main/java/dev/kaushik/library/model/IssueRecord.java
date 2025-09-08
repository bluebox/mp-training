package dev.kaushik.library.model;

import java.time.LocalDateTime;

import dev.kaushik.library.model.enums.IssueStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueRecord {
	private Integer issueId; 
	private Integer bookId;
	private Integer memberId;
	
	private IssueStatus status;
	
	private LocalDateTime issueDate;
	
	private String issuedBy;
	
	private LocalDateTime returnDate;
	
	private String returnedBy;
}
