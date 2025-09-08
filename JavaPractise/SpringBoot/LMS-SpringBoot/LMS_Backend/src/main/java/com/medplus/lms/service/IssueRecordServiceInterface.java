package com.medplus.lms.service;

import java.util.List;

import com.medplus.lms.domain.IssueRecordDto;

public interface IssueRecordServiceInterface {
	public void issueBook(int bookId, int memberId, String issuedBy);
	public void returnBook(int bookId, int memberId, String returnedTo);
	public List<IssueRecordDto> getAllIssues();
}

