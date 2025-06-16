package com.medplus.dao.impl;


import com.medplus.dao.IssueRecordDAO;
import com.medplus.model.IssueRecord;
import com.medplus.util.DBConnection;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class IssueRecordDAOImpl implements IssueRecordDAO {

    @Override
    public void issueBook(IssueRecord record) throws Exception {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String sql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, record.getBookId());
            stmt.setInt(2, record.getMemberId());
            stmt.setString(3, String.valueOf(record.getStatus()));
            stmt.setDate(4, Date.valueOf(record.getIssueDate()));
            stmt.executeUpdate();

            
            String updateBook = "UPDATE books SET Availability = 'I' WHERE BookId = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateBook);
            updateStmt.setInt(1, record.getBookId());
            updateStmt.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void returnBook(int issueId) throws SQLException {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            String sql = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueId=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, issueId);
            stmt.executeUpdate();

            String getBookIdSQL = "SELECT BookId FROM issue_records WHERE IssueId=?";
            PreparedStatement getStmt = conn.prepareStatement(getBookIdSQL);
            getStmt.setInt(1, issueId);
            ResultSet rs = getStmt.executeQuery();
            int bookId = 0;
            if (rs.next()) {
                bookId = rs.getInt("BookId");
            }
            if (rs != null) rs.close();
            if (getStmt != null) getStmt.close();

            String updateBook = "UPDATE books SET Availability='A' WHERE BookId=?";
            PreparedStatement updateStmt = conn.prepareStatement(updateBook);
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw new SQLException("Failed to return book and update availability: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public IssueRecord getIssueById(int issueId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM issue_records WHERE IssueId = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, issueId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new IssueRecord(
                    rs.getInt("IssueId"),
                    rs.getInt("BookId"),
                    rs.getInt("MemberId"),
                    rs.getString("Status").charAt(0),
                    rs.getDate("IssueDate").toLocalDate(),
                    rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null
                );
            }
            return null;
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }

    @Override
    public List<IssueRecord> getAllIssuedBooks() throws SQLException {
        List<IssueRecord> records = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM issue_records WHERE Status = 'I'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                records.add(new IssueRecord(
                    rs.getInt("IssueId"),
                    rs.getInt("BookId"),
                    rs.getInt("MemberId"),
                    rs.getString("Status").charAt(0),
                    rs.getDate("IssueDate").toLocalDate(),
                    rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null
                ));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return records;
    }
}