package com.library.services;

import java.sql.Connection;
import java.util.List;

import com.library.domain.IssueRecord;
import com.library.serviceInterface.IssueServiceInterface;

public class IssueService implements IssueServiceInterface{

	@Override
	public void issueBook(int int1, int int2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IssueRecord> getAllIssuedRecords() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateIssueRecord(Connection conn) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertIssueRecord(int bookId, int memberId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void returnBook(int bookId) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
