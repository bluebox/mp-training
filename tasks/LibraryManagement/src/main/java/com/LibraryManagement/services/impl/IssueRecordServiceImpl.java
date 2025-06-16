package com.LibraryManagement.services.impl;

import java.sql.Connection;
import java.util.ArrayList;

import com.LibraryManagement.dao.IssueRecordDao;
import com.LibraryManagement.dao.impl.IssueRecordDaoImpl;
import com.LibraryManagement.exceptions.InvalidInputException;
import com.LibraryManagement.services.IssueRecordService;
import com.LibraryManagement.utilites.DBConnection;
import com.LibraryManagement.utilites.pojos.IssueRecord;

public class IssueRecordServiceImpl implements IssueRecordService{
	private IssueRecordDao ir=new IssueRecordDaoImpl();
	@Override
	public boolean issueBookToMember(int bookId, int memberId) throws Exception {
		Connection con =DBConnection.connectDB();
		con.setAutoCommit(false);
		if(ir.verifyBookAndMember(bookId, memberId)) {
			if(ir.issueBook(bookId,memberId)) {
				con.commit();
				con.setAutoCommit(true);
				return true;
			}
		}
		else {
			con.rollback();
			con.setAutoCommit(true);
			throw new InvalidInputException("Book not available or member doesn't exist.");	
		}
		return false;
	}

	@Override
	public boolean returnBook(int issuedId) throws Exception {
		Connection con =DBConnection.connectDB();
		con.setAutoCommit(false);
		if(ir.verifyRecord(issuedId)) {
			if(ir.returnBook(issuedId)){
				con.commit();
				con.setAutoCommit(true);
				return true;
			}
		}
		con.rollback();
		con.setAutoCommit(true);
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
