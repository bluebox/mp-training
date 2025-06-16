package com.library.services;

import java.sql.Connection;

import com.library.dao.BookDAO;
import com.library.dao.IssueRecordDAO;
import com.library.dao.MemberDAO;
import com.library.util.DB;

public class IssueService {
    private BookDAO bookDAO = new BookDAO();
//    private MemberDAO memberDAO = new MemberDAO();
    private IssueRecordDAO issueDAO = new IssueRecordDAO();

    public void issueBook(int bookId, int memberId) throws Exception {
        Connection conn = null;
        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);

            if (!bookDAO.isAvailable(conn, bookId)) throw new Exception("Book is not available");
            if (!MemberDAO.exists(conn, memberId)) throw new Exception("Member does not exist");

            issueDAO.insertIssueRecord(conn, bookId, memberId);
            bookDAO.updateAvailability(conn, bookId, 'I');

            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }
    }

    public void returnBook(int bookId) throws Exception {
        
    	System.out.println("pavan");
    }
}