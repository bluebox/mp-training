package com.library.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.library.dao.Issue;
import com.library.enums.Status;
import com.library.enums.StatusRecords;
import com.library.exception.UserDefinedException;

public class IssueService {
	public static Connection conn;
	public static void BookDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","Vbhanu@123");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void issueBook(int bookId, int memberId) throws SQLException, UserDefinedException {
        String checkQuery = "SELECT Availability FROM books WHERE BookId = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                char availability = rs.getString("Availability").charAt(0);
                if (availability == 'I') {
                    throw new UserDefinedException("Book is already issued.");
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
	public List<Issue> getAllIssueRecords() throws SQLException {
        List<Issue> records = new ArrayList<>();
       String sql = "SELECT * FROM issue_records";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Issue i = new Issue(rs.getInt("IssueId"),rs.getInt("BookId"),rs.getInt("MemberId"),rs.getString("Status").charAt(0)=='R'?StatusRecords.Returned:StatusRecords.Issued,rs.getDate("IssueDate"),rs.getDate("ReturnDate"));
                records.add(i);
            }
        }
        conn.close();
        return records;
    }
	public void returnBook(int bookId, int memberId) throws SQLException {
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
