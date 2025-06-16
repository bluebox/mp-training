package com.LibraryManagement.services;

import java.util.ArrayList;

import com.LibraryManagement.exceptions.InvalidInputException;
import com.LibraryManagement.utilites.pojos.IssueRecord;

public interface IssueRecordService {
	public boolean issueBookToMember(int bookId, int memberId) throws InvalidInputException, Exception;
	public boolean returnBook(int issuedId) throws Exception;
	public ArrayList<IssueRecord> viewAllIssuedRecords() throws Exception;
	public ArrayList<IssueRecord> viewAllIssuedRecordLog() throws Exception;
	
}
