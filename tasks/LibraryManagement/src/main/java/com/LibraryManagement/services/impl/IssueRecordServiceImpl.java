package com.LibraryManagement.services.impl;

import java.util.ArrayList;

import com.LibraryManagement.dao.impl.IssueRecordDaoImpl;
import com.LibraryManagement.exceptions.InvalidInputException;
import com.LibraryManagement.services.IssueRecordService;
import com.LibraryManagement.utilites.pojos.IssueRecord;

public class IssueRecordServiceImpl implements IssueRecordService{
	private IssueRecordDaoImpl ir=new IssueRecordDaoImpl();
	@Override
	public boolean issueBookToMember(int bookId, int memberId) throws InvalidInputException {
		if(ir.verifyBookAndMember(bookId, memberId)) {
			ir.issueBook(bookId,memberId);
			return true;
		}
		else {
			throw new InvalidInputException("Book not available or member doesn't exist.");
				
		}
	}

	@Override
	public boolean returnBook(int issuedId) throws Exception {
		if(ir.verifyRecord(issuedId)) {
			ir.returnBook(issuedId);
		}
		return false;
	}


	@Override
	public ArrayList<IssueRecord> viewAllIssuedRecords() throws Exception {
		return ir.viewAllIssuedRecords();
	}

	@Override
	public ArrayList<IssueRecord> viewAllIssuedRecordLog() throws Exception {
		return ir.viewIssueRecordLog();
	}

}
