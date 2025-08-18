package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueRecord;
import com.SpringBoot_LMS.SpringBoot_LMS.model.IssueStatus;



public interface IssueRecordInterface {
	 int createBookIssue(IssueRecord issue) throws SQLException;
	   int returnBook(int BookId, int MemebrId) throws SQLException;
	   boolean checkBookIssue(int BookId, int MemberId) throws SQLException;
	   List<IssueRecord> getAllIssueRecords() throws SQLException;
	   IssueRecord getIssueRecord(int bookId,int MemberId) throws SQLException;
}
