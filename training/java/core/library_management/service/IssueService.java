package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import DAO.BookDAO;
import DAO.IssueRecordDAO;
import pojo.Book;
import pojo.Book.Availability;
import pojo.Book.Status;
import pojo.IssueRecord;
import service.exceptions.DatabaseException;
import service.exceptions.InvalidInputException;
import service.exceptions.LogicalError;
import utility.DBUtil;
import utility.DBqueries;

public class IssueService {
    private IssueRecordDAO issueRecordDAO;
    private BookDAO bookDAO;

    public IssueService() {
        this.issueRecordDAO = new IssueRecordDAO();
        this.bookDAO = new BookDAO();
    }

    public void issueBook(int bookId, int memberId) throws InvalidInputException, LogicalError, DatabaseException {
        if (bookId <= 0 || memberId <= 0) {
            throw new InvalidInputException("Book ID and Member ID must be positive integers.");
        }
        try (Connection connection = DBUtil.getConnection()) {
            connection.setAutoCommit(false);
            Book book = bookDAO.getBookById(bookId); 
            if (book == null) {
                throw new LogicalError("Book with ID " + bookId + " not found.");
            }
            if (book.getStatus() == Status.INACTIVE) {
                throw new LogicalError("Book with ID " + bookId + " is inactive.");
            }
            if (book.getAvailability() == Availability.ISSUED) {
                throw new LogicalError("Book with ID " + bookId + " is already issued.");
            }
            IssueRecord issueRecord = new IssueRecord();
            issueRecord.setBookid(bookId);
            issueRecord.setMemberid(memberId);
            issueRecord.setIssuedate(new Date());

            try (PreparedStatement preparedStatement = connection.prepareStatement(DBqueries.INSERT_ISSUE_RECORD, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, issueRecord.getBookid());
                preparedStatement.setInt(2, issueRecord.getMemberid());
                preparedStatement.setDate(3, new java.sql.Date(issueRecord.getIssuedate().getTime()));
                preparedStatement.executeUpdate();

                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        issueRecord.setIssueid(generatedKeys.getInt(1));
                    }
                }
            }
            
            try (PreparedStatement insertLogStmt = connection.prepareStatement(DBqueries.INSERT_BOOK_LOG)) {
                insertLogStmt.setInt(1, bookId);
                insertLogStmt.executeUpdate();
            }

            try (PreparedStatement updateBookAvailabilityStmt = connection.prepareStatement(DBqueries.UPDATE_BOOK_AVAILABILITY)) {
                updateBookAvailabilityStmt.setString(1, Availability.ISSUED.toString().substring(0, 1));
                updateBookAvailabilityStmt.setInt(2, bookId);
                updateBookAvailabilityStmt.executeUpdate();
            }

            connection.commit(); 
        } catch (SQLException e) {
            throw new DatabaseException("Error issuing book due to database issue.", e);
        }
    }


    public void returnBook(int issueId, int bookId) throws InvalidInputException, LogicalError, DatabaseException {
        if (issueId <= 0 || bookId <= 0) {
            throw new InvalidInputException("Issue ID and Book ID must be positive integers.");
        }
        try (Connection connection = DBUtil.getConnection()) {
            connection.setAutoCommit(false); 
            IssueRecord issueRecord = issueRecordDAO.getIssueRecordById(issueId);
            if (issueRecord == null) {
                throw new LogicalError("Issue record with ID " + issueId + " not found.");
            }
            if (issueRecord.getStatus() == pojo.IssueRecord.Status.RETURNED) {
                throw new LogicalError("Book for issue ID " + issueId + " has already been returned.");
            }
            if (issueRecord.getBookid() != bookId) {
                throw new LogicalError("Book ID does not match the issue record.");
            }

            try (PreparedStatement insertLogStmt = connection.prepareStatement(DBqueries.INSERT_ISSUE_LOG)) {
                insertLogStmt.setInt(1, issueId);
                insertLogStmt.executeUpdate();
            }

            try (PreparedStatement updateIssueStatusStmt = connection.prepareStatement(DBqueries.UPDATE_ISSUE_STATUS_RETURN)) {
                updateIssueStatusStmt.setDate(1, new java.sql.Date(new Date().getTime()));
                updateIssueStatusStmt.setInt(2, issueId);
                updateIssueStatusStmt.executeUpdate();
            }

            try (PreparedStatement insertBookLogStmt = connection.prepareStatement(DBqueries.INSERT_BOOK_LOG)) {
                insertBookLogStmt.setInt(1, bookId);
                insertBookLogStmt.executeUpdate();
            }

            try (PreparedStatement updateBookAvailabilityStmt = connection.prepareStatement(DBqueries.UPDATE_BOOK_AVAILABILITY)) {
                updateBookAvailabilityStmt.setString(1, Availability.AVAILABLE.toString().substring(0, 1));
                updateBookAvailabilityStmt.setInt(2, bookId);
                updateBookAvailabilityStmt.executeUpdate();
            }

            connection.commit(); 
            } catch (SQLException e) {
           
            throw new DatabaseException("Error returning book due to database issue.", e);
        }
    }

    public List<IssueRecord> getAllIssuedRecords() throws DatabaseException {
        try {
            return issueRecordDAO.getAllIssuedRecords();
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving all issue records from the database.", e);
        }
    }
}