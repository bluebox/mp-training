package com.LMS.LibMS.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import com.LMS.LibMS.model.enums.IssueStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRecord {
	
	@Id
	private Integer issueId;
	
	@Id
	private Integer bookId;
	
	@Id
	private Integer memberId;
	
	@NotBlank(message="Status must not be blank")
	private IssueStatus status; 
	
	@CreatedDate
	private LocalDateTime issueDate;
	
	@CreatedBy
	private String issuedBy;
	
	@CreatedDate
	private LocalDateTime returnDate;
	
	@CreatedBy
	private String returnedBy;
}
