package com.LMS.LibMS.service.interfaces;

import java.util.List;

import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.model.Member;

public interface IssueService {
	void issueBook(int bookId, int memberId, String issuedBy) throws Exception;

	void returnBook(int bookId, String returnedBy) throws Exception;

	List<IssueRecord> getAllIssuedRecords();

	List<IssueRecord> getOverdueBooks(int dueDays);

	List<Member> getMembersWithActiveBooks();
}