package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.IssueRecordDao;
import domain.IssueRecord;

public class IssueService {
    private IssueRecordDao issueRecordDAO;

    public IssueService(Connection conn) {
        this.issueRecordDAO = new IssueRecordDao(conn);
    }
    public String issueBookToMember(int bookId, int memberId) {
        try {
            return issueRecordDAO.issueBook(bookId, memberId);
        } catch (SQLException e) {
            e.printStackTrace(); 
            return "Error while issuing the book: " + e.getMessage();
        }
    }
    public String returnBookFromMember(int bookId, int memberId) {
        try {
            return issueRecordDAO.returnBook(bookId, memberId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error while returning the book: " + e.getMessage();
        }
    }
    public boolean isBookAlreadyIssued(int bookId) {
        try {
            return issueRecordDAO.isBookAlreadyIssued(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    public String canBookBeIssued(int bookId) {
        try {
            return issueRecordDAO.canBookBeIssued(bookId);
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error checking book status: " + e.getMessage();
        }
    }
    public List<IssueRecord> getAllIssueRecords() {
        return issueRecordDAO.getAllIssueRecords();
    }
}