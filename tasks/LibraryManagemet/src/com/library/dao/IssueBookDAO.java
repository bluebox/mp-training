package com.library.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.domain.IssueRecord;
import com.library.utilities.ConnectionMaker;

public class IssueBookDAO {

    private Connection conn = ConnectionMaker.getConnection();

    public boolean isBookAvailable(int bookId) throws SQLException {
        String sql = "SELECT status, availability FROM book WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return "A".equals(rs.getString("status")) && "A".equals(rs.getString("availability"));
                }
            }
        }
        return false;
    }

    public void issueBook(IssueRecord record) throws SQLException {
        // Insert into main table
        String sql = "INSERT INTO issue_records(BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', NOW())";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, record.getBookId());
            ps.setInt(2, record.getMemberId());
            ps.executeUpdate();
        }

        // Log action
        String logSql = "INSERT INTO issue_log(BookId, MemberId, Action, ActionTime) VALUES (?, ?, 'ISSUE', NOW())";
        try (PreparedStatement logStmt = conn.prepareStatement(logSql)) {
            logStmt.setInt(1, record.getBookId());
            logStmt.setInt(2, record.getMemberId());
            logStmt.executeUpdate();
        }

        // Update book availability
        try (PreparedStatement update = conn.prepareStatement("UPDATE book SET availability = 'I' WHERE id = ?")) {
            update.setInt(1, record.getBookId());
            update.executeUpdate();
        }
    }

    public void returnBook(int bookId, int memberId) throws SQLException {
        conn.setAutoCommit(false);
        try {
            // Update main table
            String sql = "UPDATE issue_records SET ReturnDate = NOW(), Status = 'R' WHERE BookId = ? AND MemberId = ? AND ReturnDate IS NULL";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, bookId);
                ps.setInt(2, memberId);
                ps.executeUpdate();
            }

            // Update book table
            try (PreparedStatement ps = conn.prepareStatement("UPDATE book SET availability = 'A' WHERE id = ?")) {
                ps.setInt(1, bookId);
                ps.executeUpdate();
            }

            // Log action
            try (PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO issue_log(BookId, MemberId, Action, ActionTime) VALUES (?, ?, 'RETURN', NOW())")) {
                ps.setInt(1, bookId);
                ps.setInt(2, memberId);
                ps.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    public List<IssueRecord> getAllIssuedBooks() throws SQLException {
        List<IssueRecord> list = new ArrayList<>();
        String sql = "SELECT * FROM issue_records";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new IssueRecord(
                    rs.getInt("BookId"),
                    rs.getInt("MemberId"),
                    rs.getDate("IssueDate"),
                    rs.getDate("ReturnDate")
                ));
            }
        }
        return list;
    }
}
