package com.library.service.impl;

import com.library.dao.impl.IssueRecordDaoImplementation;
import com.library.dao.interfaces.IssueRecordDao;
import com.library.model.IssueRecord;
import com.library.service.interfaces.IssueRecordService;
import com.library.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class IssueRecordServiceImplementation implements IssueRecordService {

    private final IssueRecordDao dao = new IssueRecordDaoImplementation();

    @Override
    public boolean issueBook(IssueRecord record) throws Exception {
        if (record == null)
            throw new Exception("Issue data is null");

        if (record.getBookId() <= 0 || record.getMemberId() <= 0)
            throw new Exception("Invalid book or member ID");

        Connection conn = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            boolean result = dao.issueBook(record, conn);
            conn.commit();
            return result;
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw new Exception("Failed to issue book: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public boolean returnBook(int issueId) throws Exception {
        if (issueId <= 0)
            throw new Exception("Invalid issue ID");

        Connection conn = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            boolean result = dao.returnBook(issueId, conn);
            conn.commit();
            return result;
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw new Exception("Failed to return book: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public IssueRecord getActiveIssueByBookId(int bookId) throws Exception {
        if (bookId <= 0)
            throw new Exception("Invalid book ID");

        return dao.getActiveIssueByBookId(bookId);
    }

    @Override
    public List<IssueRecord> getAllIssues() throws Exception {
        return dao.getAllIssues();
    }
}
