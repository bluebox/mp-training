package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.IssueRecord;
import domain.IssueStatus;

public class IssueRecordDao {
    private Connection conn;
    public IssueRecordDao(Connection conn) {
        this.conn = conn;
    }
    public IssueRecordDao() {
		// TODO Auto-generated constructor stub
	}
	public String issueBook(int bookId, int memberId) throws SQLException {
        String checkBookQuery = "SELECT Status, Availability FROM books WHERE BookId = ?";
        String insertIssueQuery = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";
        String updateBookQuery = "UPDATE books SET Availability = 'I' WHERE BookId = ?";
        try (
            PreparedStatement checkStmt = conn.prepareStatement(checkBookQuery);
            PreparedStatement insertStmt = conn.prepareStatement(insertIssueQuery);
            PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery)
        ) {
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                return "Book not found.";
            }

            String status = rs.getString("Status");
            String availability = rs.getString("Availability");

            if (!status.equals("A")) {
                return "This book is inactive and cannot be issued.";
            }

            if (!availability.equals("A")) {
                return "This book is already issued to another member.";
            }
            insertStmt.setInt(1, bookId);
            insertStmt.setInt(2, memberId);
            insertStmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            insertStmt.executeUpdate();
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();

            return "Book issued successfully.";
        }
    }
        public String returnBook(int bookId, int memberId) throws SQLException {
        String checkBookQuery = "SELECT Availability FROM books WHERE BookId = ?";
        String findIssueQuery = "SELECT IssueId FROM issue_records WHERE BookId = ? AND MemberId = ? AND Status = 'I' ORDER BY IssueDate DESC LIMIT 1";
        String updateIssueQuery = "UPDATE issue_records SET Status = 'R', ReturnDate = ? WHERE IssueId = ?";
        String updateBookQuery = "UPDATE books SET Availability = 'A' WHERE BookId = ?";
        try (
            PreparedStatement checkStmt = conn.prepareStatement(checkBookQuery);
            PreparedStatement findStmt = conn.prepareStatement(findIssueQuery);
            PreparedStatement updateIssueStmt = conn.prepareStatement(updateIssueQuery);
            PreparedStatement updateBookStmt = conn.prepareStatement(updateBookQuery)
        ) {
            checkStmt.setInt(1, bookId);
            ResultSet bookRs = checkStmt.executeQuery();

            if (!bookRs.next()) {
                return "Book not found.";
            }
            String availability = bookRs.getString("Availability");
            if (!availability.equals("I")) {
                return "This book is not currently issued.";
            }
            findStmt.setInt(1, bookId);
            findStmt.setInt(2, memberId);
            ResultSet issueRs = findStmt.executeQuery();

            if (!issueRs.next()) {
                return "No active issue record found for this book and member.";
            }
            int issueId = issueRs.getInt("IssueId");
            updateIssueStmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            updateIssueStmt.setInt(2, issueId);
            updateIssueStmt.executeUpdate();
            updateBookStmt.setInt(1, bookId);
            updateBookStmt.executeUpdate();
            return "Book returned successfully.";
        }
    }
        public boolean isBookAlreadyIssued(int bookId) throws SQLException {
            String checkQuery = "SELECT Availability FROM books WHERE BookId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
                stmt.setInt(1, bookId);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String availability = rs.getString("Availability");
                    return "I".equalsIgnoreCase(availability); 
                    } 
                else {
                    throw new SQLException("Book with ID " + bookId + " not found.");
                }
            }
        }
        public String canBookBeIssued(int bookId) throws SQLException {
            String query = "SELECT Status, Availability FROM books WHERE BookId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, bookId);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) return "Book not found.";
                String status = rs.getString("Status");
                String availability = rs.getString("Availability");
                if (!"A".equalsIgnoreCase(status)) return "Book is inactive.";
                if (!"A".equalsIgnoreCase(availability)) return "Book is already issued.";

                return "OK";
            }
        }
        public List<IssueRecord> getAllIssueRecords() {
            List<IssueRecord> issueRecords = new ArrayList<>();
            String query = "SELECT IssueId, BookId, MemberId, Status, IssueDate, ReturnDate FROM issue_records";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    IssueRecord record = new IssueRecord();
                    record.setIssueId(rs.getInt("IssueId"));
                    record.setBookId(rs.getInt("BookId"));
                    record.setMemberId(rs.getInt("MemberId"));
                    String statusStr = rs.getString("Status");
                    if (statusStr != null && !statusStr.isEmpty()) {
                        record.setStatus(IssueStatus.valueOf(statusStr));
                    }
                    Date issueDateSql = rs.getDate("IssueDate");
                    if (issueDateSql != null) {
                        record.setIssueDate(issueDateSql.toLocalDate());
                    }
                    Date returnDateSql = rs.getDate("ReturnDate");
                    if (returnDateSql != null) {
                        record.setReturnDate(returnDateSql.toLocalDate());
                    }
                    issueRecords.add(record);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return issueRecords;
        }
}