package com.example.library.service;

import java.util.List;

import com.example.library.domain.IssueRecord;

public interface IssueRecordService {

	String issueBook(int bookId, int memberId);

	String returnBook(int memberId, int bookId);

	List<IssueRecord> getAllIssues();
}
