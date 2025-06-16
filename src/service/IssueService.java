package service;

import dao.IssueDAO;
import model.IssueRecord;
import exception.BookAlreadyIssuedException;
import exception.DatabaseException;

import java.sql.SQLException;
import java.util.List;

public class IssueService {

    private IssueDAO issueDAO;

    public IssueService() {
        this.issueDAO = new IssueDAO();
    }

    public void issueBook(int bookId, int memberId) throws BookAlreadyIssuedException, DatabaseException {
        try {
            issueDAO.issueBook(bookId, memberId);
        } catch (BookAlreadyIssuedException e) {
            throw e;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to issue book: " + e.getMessage());
        }
    }

    public void returnBook(int bookId, int memberId) throws DatabaseException {
        try {
            issueDAO.returnBook(bookId, memberId);
        } catch (SQLException e) {
            throw new DatabaseException("Failed to return book: " + e.getMessage());
        }
    }

    public List<IssueRecord> getAllIssueRecords() throws DatabaseException {
        try {
            return issueDAO.getAllIssueRecords();
        } catch (SQLException e) {
            throw new DatabaseException("Failed to retrieve issue records: " + e.getMessage());
        }
    }
}
