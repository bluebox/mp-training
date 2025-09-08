package com.medplus.lms.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.domain.IssueStatus;

public interface IssueRecordRepositoryInterface {
	public void issueBook(int bookId, int memberId, String issuedBy, LocalDateTime issueDate, IssueStatus status);
	public int findActiveIssueId(int bookId, int memberId);
	public void returnBook(int bookId, int memberId, String returnedTo);
	public List<IssueRecordDto> getAllIssues();
}
