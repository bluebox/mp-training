package com.library.dao.impl;

import com.library.dao.interfaces.IssueRecordDao;
import com.library.model.IssueRecord;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDaoImplementation implements IssueRecordDao {

    @Override
    public boolean issueBook(IssueRecord record, Connection conn) throws SQLException {
        String insertIssueSql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";
        String logIssueSql = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, NULL)";
        String updateAvailabilitySql = "UPDATE books SET Availability='I' WHERE BookId=?";

        IssueRecord existing = getActiveIssueByBookId(record.getBookId());
        if (existing != null) throw new SQLException("Book is already issued to someone.");

        int issueId;

        // issue_records 
        try (PreparedStatement insertStmt = conn.prepareStatement(insertIssueSql, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setInt(1, record.getBookId());
            insertStmt.setInt(2, record.getMemberId());
            insertStmt.setDate(3, Date.valueOf(record.getIssueDate()));
            insertStmt.executeUpdate();

            try (ResultSet rs = insertStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    issueId = rs.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve generated IssueId.");
                }
            }
        }

        // log updation
        try (PreparedStatement logStmt = conn.prepareStatement(logIssueSql)) {
            logStmt.setInt(1, issueId);
            logStmt.setInt(2, record.getBookId());
            logStmt.setInt(3, record.getMemberId());
            logStmt.setString(4, "I");
            logStmt.setDate(5, Date.valueOf(record.getIssueDate()));
            logStmt.executeUpdate();
        }

        //book availability updation
        try (PreparedStatement updateAvailStmt = conn.prepareStatement(updateAvailabilitySql)) {
            updateAvailStmt.setInt(1, record.getBookId());
            updateAvailStmt.executeUpdate();
        }

        return true;
    }

    @Override
    public boolean returnBook(int issueId, Connection conn) throws SQLException {
        String updateIssueSql = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueId=?";
        String insertLogSql = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) " +
                              "SELECT IssueId, BookId, MemberId, 'R', IssueDate, ? FROM issue_records WHERE IssueId=?";
        String updateAvailabilitySql = "UPDATE books SET Availability='A' WHERE BookId=(SELECT BookId FROM issue_records WHERE IssueId=?)";

        LocalDate returnDate = LocalDate.now();

        try (PreparedStatement updateStmt = conn.prepareStatement(updateIssueSql)) {
            updateStmt.setDate(1, Date.valueOf(returnDate));
            updateStmt.setInt(2, issueId);
            if (updateStmt.executeUpdate() != 1) throw new SQLException("No matching issue record found.");
        }

        try (PreparedStatement logStmt = conn.prepareStatement(insertLogSql)) {
            logStmt.setDate(1, Date.valueOf(returnDate));
            logStmt.setInt(2, issueId);
            logStmt.executeUpdate();
        }

        try (PreparedStatement updateAvailStmt = conn.prepareStatement(updateAvailabilitySql)) {
            updateAvailStmt.setInt(1, issueId);
            updateAvailStmt.executeUpdate();
        }

        return true;
    }

    @Override
    public IssueRecord getActiveIssueByBookId(int bookId) {
        String query = "SELECT * FROM issue_records WHERE BookId=? AND Status='I'";

        try (Connection conn = com.library.util.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);

            try (ResultSet rs = stmt.executeQuery()) {
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
            }

        } catch (SQLException e) {
            System.err.println("Error in getActiveIssueByBookId: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<IssueRecord> getAllIssues() {
        List<IssueRecord> list = new ArrayList<>();
        String query = "SELECT * FROM issue_records";

        try (Connection conn = com.library.util.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                IssueRecord record = new IssueRecord(
                        rs.getInt("IssueId"),
                        rs.getInt("BookId"),
                        rs.getInt("MemberId"),
                        rs.getString("Status").charAt(0),
                        rs.getDate("IssueDate").toLocalDate(),
                        rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null
                );
                list.add(record);
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllIssues: " + e.getMessage());
        }

        return list;
    }

    @Override
    public List<IssueRecord> getAllIssuedRecords() {
        List<IssueRecord> list = new ArrayList<>();
        String query = "SELECT * FROM issue_records WHERE Status='I'";

        try (Connection conn = com.library.util.DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                IssueRecord record = new IssueRecord(
                		
                        rs.getInt("IssueId"),
                        rs.getInt("BookId"),
                        rs.getInt("MemberId"),
                        rs.getString("Status").charAt(0),
                        rs.getDate("IssueDate").toLocalDate(),
                        rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null );
                list.add(record);
            }

        } catch (SQLException e) {
            System.err.println("Error in getAllIssuedRecords: " + e.getMessage());
        }

        return list;
    }
}
