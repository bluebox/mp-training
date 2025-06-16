package dao;

import model.IssueRecord;
import util.DBConnection;
import exception.BookAlreadyIssuedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import enums.StatusRecords;

public class IssueDAO {

    public void issueBook(int bookId, int memberId) throws SQLException, BookAlreadyIssuedException {
        Connection conn = DBConnection.getConnection();

        String checkQuery = "SELECT Availability FROM books WHERE BookId = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                char availability = rs.getString("Availability").charAt(0);
                if (availability == 'I') {
                    throw new BookAlreadyIssuedException("Book is already issued.");
                }
            } else {
                throw new SQLException("Book not found with ID: " + bookId);
            }
        }

        String insertQuery = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', CURDATE())";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, bookId);
            insertStmt.setInt(2, memberId);
            insertStmt.executeUpdate();
        }

        String updateBookQuery = "UPDATE books SET Availability = 'I' WHERE BookId = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateBookQuery)) {
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();
        }

        conn.close();
    }

    public List<IssueRecord> getAllIssueRecords() throws SQLException {
        List<IssueRecord> records = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM issue_records";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                IssueRecord record = new IssueRecord();
                record.setIssueId(rs.getInt("IssueId"));
                record.setBookId(rs.getInt("BookId"));
                record.setMemberId(rs.getInt("MemberId"));
                record.setStatus(rs.getString("Status").charAt(0)=='I'?StatusRecords.Issued:StatusRecords.Returned);
                record.setIssueDate(rs.getDate("IssueDate"));
                record.setReturnDate(rs.getDate("ReturnDate"));
                records.add(record);
            }
        }

        conn.close();
        return records;
    }
    public void returnBook(int bookId, int memberId) throws SQLException {
        Connection conn = DBConnection.getConnection();
        conn.setAutoCommit(false);

        try {
            String updateIssue = "UPDATE issue_records SET Status = 'R', ReturnDate = CURDATE() " +
                                 "WHERE BookId = ? AND MemberId = ? AND Status = 'I'";
            try (PreparedStatement issueStmt = conn.prepareStatement(updateIssue)) {
                issueStmt.setInt(1, bookId);
                issueStmt.setInt(2, memberId);
                int rowsUpdated = issueStmt.executeUpdate();

                if (rowsUpdated == 0) {
                    throw new SQLException("No active issue record found for return.");
                }
            }

            String updateBook = "UPDATE books SET Availability = 'A' WHERE BookId = ?";
            try (PreparedStatement bookStmt = conn.prepareStatement(updateBook)) {
                bookStmt.setInt(1, bookId);
                bookStmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
            conn.close();
        }
    }

}