package com.LibraryManagement.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.LibraryManagement.exceptions.InvalidInputException;
import com.LibraryManagement.utilites.pojos.IssueRecord;

public interface IssueRecordService {
	public boolean issueBookToMember(int bookId, int memberId) throws InvalidInputException, ClassNotFoundException, IOException, SQLException, Exception;
	public boolean returnBook(int issuedId) throws Exception;
	public ArrayList<IssueRecord> viewAllIssuedRecords() throws Exception;
	public ArrayList<IssueRecord> viewAllIssuedRecordLog() throws Exception;
	
}