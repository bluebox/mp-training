package com.library.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.library.dao.BookDAO;
import com.library.dao.IssueRecordDAO;
import com.library.dao.MemberDAO;
import com.library.domain.Book;
import com.library.domain.IssueRecord;
import com.library.serviceInterface.IssueServiceInterface;
import com.library.util.DB;

public class IssueService implements IssueServiceInterface {

	private BookDAO bookDAO = new BookDAO();
	private IssueRecordDAO issueDAO = new IssueRecordDAO();
	private MemberDAO memberDao = new MemberDAO();

	@Override
	public void issueBook(int bookId, int memberId) throws Exception {
		Connection conn = null;
		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false);

			if (!bookDAO.isAvailable(conn, bookId))
				throw new Exception("Book is not available");
			if ((memberDao.getMemberById(conn, memberId) == null))
				throw new Exception("Member does not exist");

			try {
				Book book=bookDAO.getBookById(conn, bookId, 'A');
				if (book!=null) {
					bookDAO.insertIntoBookLog(conn, book);
				}
				issueDAO.insertIssueRecord(conn, bookId, memberId);
				bookDAO.updateAvailability(conn, bookId, 'I');

				conn.commit();
			
		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			throw e;
		} 
		}finally {

			if (conn != null)
				conn.close();
		}
	}

	@Override
	public List<IssueRecord> getAllIssuedRecords() throws Exception {

		List<IssueRecord> listOfIssuedRecords = null;
		// pavan
		return listOfIssuedRecords;

	}

	@Override
	public void updateIssueRecord(Connection conn) throws Exception {
		// TODO Auto-generated method stub

	}

	public void returnBook(int bookId) throws Exception {
//	    	Connection conn = null;
//	        try {
//			            conn = DB.getConnection();
//			            conn.setAutoCommit(false);
//			
//			            if (!bookDAO.isAvailable(conn, bookId)) throw new Exception("Book is not available");
//			
//			            ResultSet resultData=bookDAO.getBookById(conn, bookId, 'I');
//			            bookDAO.insertIntoBookLog(conn, resultData);
//			           
//			            ResultSet rs= IssueRecordDAO.getIssueRecord(conn, bookId);
//			            IssueRecordDAO.insertIntoIssueRecordLog(ResultSet rs);
//			            IssueRecordDAO.updateIssueRecord(conn,bookId);
//			            bookDAO.updateAvailability(conn, bookId, 'A');
//			
//			            conn.commit();
//	        } 
//	        catch (Exception e)
//	        {
//			            if (conn != null) conn.rollback();
//			            throw e;
//	        } 
//	        finally {
//	            
//	        			if (conn != null) conn.close();
//	        }

	}

}