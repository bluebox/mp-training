package com.LibraryManagement.dao;

import java.util.ArrayList;

import com.LibraryManagement.exceptions.InvalidInputException;
import com.LibraryManagement.utilites.pojos.IssueRecord;

public interface IssueRecordDao {
	public boolean issueBook(int bookId, int memberId) throws InvalidInputException;
	public boolean verifyBookAndMember(int bookId,int memberId);
	public boolean verifyRecord(int issuedId);
	public boolean returnBook(int issueId) throws Exception;
	public ArrayList<IssueRecord> viewAllIssuedRecords() throws Exception;
	public ArrayList<IssueRecord> viewIssueRecordLog() throws Exception;
}
