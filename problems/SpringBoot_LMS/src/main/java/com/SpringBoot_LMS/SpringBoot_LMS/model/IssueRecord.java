package com.SpringBoot_LMS.SpringBoot_LMS.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Component
public class IssueRecord {
	@NotNull(message="Id must not be null")
    private  int IssueRecordId;
	@NotNull(message="BookId must not be null")
    private  int BookId;
	@NotNull(message="MemberId must not be null")
    private  int MemberId;
	@NotNull(message="Status of Issue Must not be Null")
    private  IssueStatus status;
	@NotNull(message="IssueDate of Issue Must not be Null")
    private  LocalDate issueDate;
	@NotNull(message="ReturnDate of Issue Must not be Null")
    private  LocalDate ReturnDate;  
}

