package com.library.dao.impl;

import com.library.dao.interfaces.IssueRecordDao;
import com.library.model.IssueRecord;
import com.library.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDaoImplementation implements IssueRecordDao {

    @Override
    public boolean issueBook(IssueRecord record) {
        String insertIssue = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";
        String logIssue = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, NULL)";
        String updateAvailability = "UPDATE books SET Availability='I' WHERE BookId=?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            // Check if book is already issued
            IssueRecord existing = getActiveIssueByBookId(record.getBookId());
            if (existing != null) {
                throw new SQLException("Book is already issued to someone.");
            }

            int issueId = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(insertIssue, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, record.getBookId());
                pstmt.setInt(2, record.getMemberId());
                pstmt.setDate(3, Date.valueOf(record.getIssueDate()));
                pstmt.executeUpdate();

                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    issueId = rs.getInt(1);
                } else {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement logStmt = conn.prepareStatement(logIssue)) {
                logStmt.setInt(1, issueId);
                logStmt.setInt(2, record.getBookId());
                logStmt.setInt(3, record.getMemberId());
                logStmt.setString(4, "I");
                logStmt.setDate(5, Date.valueOf(record.getIssueDate()));
                logStmt.executeUpdate();
            }

            try (PreparedStatement availStmt = conn.prepareStatement(updateAvailability)) {
                availStmt.setInt(1, record.getBookId());
                availStmt.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error in issueBook: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean returnBook(int issueId) {
        String updateIssue = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueId=?";
        String insertLog = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) " +
                "SELECT IssueId, BookId, MemberId, 'R', IssueDate, ? FROM issue_records WHERE IssueId=?";
        String updateAvailability = "UPDATE books SET Availability='A' WHERE BookId=(SELECT BookId FROM issue_records WHERE IssueId=?)";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement updateStmt = conn.prepareStatement(updateIssue)) {
                updateStmt.setDate(1, Date.valueOf(LocalDate.now()));
                updateStmt.setInt(2, issueId);
                int updated = updateStmt.executeUpdate();

                if (updated != 1) {
                    conn.rollback();
                    return false;
                }
            }

            try (PreparedStatement logStmt = conn.prepareStatement(insertLog)) {
                logStmt.setDate(1, Date.valueOf(LocalDate.now()));
                logStmt.setInt(2, issueId);
                logStmt.executeUpdate();
            }

            try (PreparedStatement availStmt = conn.prepareStatement(updateAvailability)) {
                availStmt.setInt(1, issueId);
                availStmt.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error in returnBook: " + e.getMessage());
        }

        return false;
    }

    @Override
    public IssueRecord getActiveIssueByBookId(int bookId) {
        String query = "SELECT * FROM issue_records WHERE BookId=? AND Status='I'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();

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

        } catch (SQLException e) {
            System.err.println("Error in getActiveIssueByBookId: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<IssueRecord> getAllIssues() {
        List<IssueRecord> list = new ArrayList<>();
        String query = "SELECT * FROM issue_records";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();
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
}
