package com.library.dao;

import com.library.domain.IssueRecord;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueRecordDAO {

    public List<IssueRecord> getAllRecords() {
        List<IssueRecord> records = new ArrayList<>();
        //pavan
        return records;
    }
    
    public void insertIssueRecord(Connection conn, int bookId, int memberId) throws SQLException {
        
        String insertSQL = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
            insertStmt.setInt(1, bookId);
            insertStmt.setInt(2, memberId);
            insertStmt.setDate(3, Date.valueOf(LocalDate.now()));
            insertStmt.executeUpdate();
        }
    }


    public void updateReturn(Connection conn, int bookId) throws SQLException {

        String fetchSQL = "SELECT * FROM issue_records WHERE BookId = ? AND Status = 'I' ORDER BY IssueId DESC LIMIT 1";
        try (PreparedStatement fetchStmt = conn.prepareStatement(fetchSQL)) {
            fetchStmt.setInt(1, bookId);
            ResultSet rs = fetchStmt.executeQuery();

            if (rs.next()) {
                int issueId = rs.getInt("IssueId");

                
                String insertLogSQL = "INSERT INTO issue_records_log (issue_id, bookid, memberid, status, issuedate, returndate) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement logStmt = conn.prepareStatement(insertLogSQL)) {
                    logStmt.setInt(1, rs.getInt("IssueId"));
                    logStmt.setInt(2, rs.getInt("BookId"));
                    logStmt.setInt(3, rs.getInt("MemberId"));
                    logStmt.setString(4, rs.getString("Status"));
                    logStmt.setDate(5, rs.getDate("IssueDate"));
                    logStmt.setDate(6, rs.getDate("ReturnDate"));
                    logStmt.executeUpdate();
                }

                String updateSQL = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueId=?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                    updateStmt.setDate(1, Date.valueOf(LocalDate.now()));
                    updateStmt.setInt(2, issueId);
                    updateStmt.executeUpdate();
                }
            } else {
                throw new SQLException("No active issued record found for bookId: " + bookId);
            }
        }
    }

}