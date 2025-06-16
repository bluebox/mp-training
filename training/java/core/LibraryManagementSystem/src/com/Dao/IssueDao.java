package com.Dao;
import com.Exception.DatabaseException;
import com.Exception.InvalidInputException;
import com.Models.*;
import com.utilities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueDao {

    public void issueBook(Issue record) throws DatabaseException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);

            String insertSQL = "INSERT INTO issues (BookId, MemberId, Status, IssueDate) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, record.getBookId());
            pst.setInt(2, record.getMemberId());
            pst.setString(3, String.valueOf(record.getStatus()));
            pst.setDate(4, Date.valueOf(record.getIssueDate()));
          //  pst.setDate(5, Date.valueOf(record.getReturnDate()));
            pst.executeUpdate();
            try(ResultSet rs=pst.getGeneratedKeys()){
            	if(rs.next()) {
            		record.setIssueId(rs.getInt(1));
            	}
            }
            String insertSQL2 = "INSERT INTO issuelogs (issusId, BookId, MemberId, Status, IssueDate) VALUES (?, ?, ?, ?,?)";
            PreparedStatement pst2 = conn.prepareStatement(insertSQL2);
            pst2.setInt(1, record.getIssueId());
            pst2.setInt(2, record.getBookId());
            pst2.setInt(3, record.getMemberId());
            pst2.setString(4, String.valueOf(record.getStatus()));
            pst2.setDate(5, Date.valueOf(record.getIssueDate()));
            pst2.executeUpdate();

            String updateBook = "UPDATE books SET Availablity = 'I' WHERE BookId = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateBook);
            updateStmt.setInt(1, record.getBookId());
            updateStmt.executeUpdate();

            conn.commit(); 
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback(); 
            } catch (SQLException ex) {
                throw new DatabaseException("Rollback failed", ex);
            }
            throw new DatabaseException("Failed to issue book", e);
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DatabaseException("Failed to reset auto-commit", e);
            }
        }
    }

//    public void returnBook(int bookId,int memberId, LocalDate returnDate) throws DatabaseException {
//        Connection conn = null;
//        try {
//            conn = DbUtil.getConnection();
//            conn.setAutoCommit(false);
//
//            String updateIssue = "UPDATE issues SET Status = 'R', ReturnDate = ? WHERE bookId = ? AND Status = 'I' AND memberId = ? ";
//            PreparedStatement pst = conn.prepareStatement(updateIssue,Statement.RETURN_GENERATED_KEYS);
//            pst.setDate(1, Date.valueOf(returnDate));
//            pst.setInt(2, bookId);
//            pst.setInt(3, memberId);
//            pst.executeUpdate();
//            int issueId=0;
//            try(ResultSet rs=pst.getGeneratedKeys()){
//            	if(rs.next()) {
//            		issueId=rs.getInt(1);
//            	}
//            	else
//            		throw new DatabaseException("Wrong Details !!");
//            }
//            String insertSQL2 = "INSERT INTO issuelogs (issusId, BookId, MemberId, Status, ReturnDate) VALUES (?, ?, ?, ?,?)";
//            PreparedStatement pst2 = conn.prepareStatement(insertSQL2);
//            pst2.setInt(1, issueId);
//            pst2.setInt(2, bookId);
//            pst2.setInt(3, memberId);
//            pst2.setString(4, "R");
//            pst2.setDate(5, Date.valueOf(returnDate));
//            pst2.executeUpdate();
//            String updateBook = "UPDATE books SET Availablity = 'A' WHERE BookId = ?";
//            PreparedStatement updateStmt = conn.prepareStatement(updateBook);
//            updateStmt.setInt(1, bookId);
//            updateStmt.executeUpdate();
//
//            conn.commit(); 
//        } catch (Exception e) {
//            try {
//                if (conn != null) conn.rollback();
//            } catch (SQLException ex) {
//                throw new DatabaseException("Rollback failed", ex);
//            }
//            throw new DatabaseException("Failed to return book", e);
//        } finally {
//            try {
//                if (conn != null) conn.setAutoCommit(true);
//            } catch (SQLException e) {
//                throw new DatabaseException("Failed to reset auto-commit", e);
//            }
//        }
//    }
    public void returnBook(int bookId, int memberId, LocalDate returnDate) throws DatabaseException {
        Connection conn = null;
        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);

            String selectIssue = "SELECT IssusId FROM issues WHERE bookId = ? AND memberId = ? AND Status = 'I'";
            PreparedStatement selectStmt = conn.prepareStatement(selectIssue);
            selectStmt.setInt(1, bookId);
            selectStmt.setInt(2, memberId);
            ResultSet rs = selectStmt.executeQuery();

            int issueId = -1;
            if (rs.next()) {
                issueId = rs.getInt("IssusId");
            } else {
                throw new DatabaseException("No active issue found for the given book and member!");
            }

            String updateIssue = "UPDATE issues SET Status = 'R', ReturnDate = ? WHERE IssusId = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateIssue);
            updateStmt.setDate(1, Date.valueOf(returnDate));
            updateStmt.setInt(2, issueId);
            updateStmt.executeUpdate();

            String insertSQL2 = "INSERT INTO issuelogs (issusId, BookId, MemberId, Status, ReturnDate) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst2 = conn.prepareStatement(insertSQL2);
            pst2.setInt(1, issueId);
            pst2.setInt(2, bookId);
            pst2.setInt(3, memberId);
            pst2.setString(4, "R");
            pst2.setDate(5, Date.valueOf(returnDate));
            pst2.executeUpdate();

            String updateBook = "UPDATE books SET Availablity = 'A' WHERE BookId = ?";
            PreparedStatement updateBookStmt = conn.prepareStatement(updateBook);
            updateBookStmt.setInt(1, bookId);
            updateBookStmt.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                throw new DatabaseException("Rollback failed", ex);
            }
            throw new DatabaseException("Failed to return book", e);
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DatabaseException("Failed to reset auto-commit", e);
            }
        }
    }


    public boolean isBookIssued(int bookId) throws DatabaseException {
        String query = "SELECT COUNT(*) FROM issues WHERE BookId = ? AND Status = 'I'";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error checking book issue status", e);
        }
        return false;
    }

    public List<Issue> getAllIssuedRecords() throws DatabaseException {
        List<Issue> list = new ArrayList<>();
        String query = "SELECT * FROM issues WHERE Status = 'I'";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Issue record = new Issue();
                record.setIssueId(rs.getInt("issusId"));
                record.setBookId(rs.getInt("bookId"));
                record.setMemberId(rs.getInt("memberId"));
                record.setStatus(rs.getString("Status").charAt(0));
                record.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                Date returnDate = rs.getDate("ReturnDate");
                if (returnDate != null) {
                    record.setReturnDate(returnDate.toLocalDate());
                }
                list.add(record);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch issued records", e);
        }
        return list;
    }

	public List<Issue> getAllOverDueRecords() throws DatabaseException {
		List<Issue> list = new ArrayList<>();
        String query = "SELECT * FROM issues WHERE Status = 'I'";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Issue record = new Issue();
                record.setIssueId(rs.getInt("issusId"));
                record.setBookId(rs.getInt("bookId"));
                record.setMemberId(rs.getInt("memberId"));
                record.setStatus(rs.getString("Status").charAt(0));
                record.setIssueDate(rs.getDate("IssueDate").toLocalDate());
                Date returnDate = rs.getDate("ReturnDate");
                if (returnDate != null) {
                    record.setReturnDate(returnDate.toLocalDate());
                }
                if(record.getIssueDate().plusDays(15).isBefore(LocalDate.now()))
                list.add(record);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Failed to fetch issued records", e);
        }
        return list;
	}
	public List<Issue> getAllIssues() throws DatabaseException {
		List<Issue> list = new ArrayList<>();
        String query = "SELECT * FROM issues";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {


    while (rs.next()) {
        Issue record = new Issue();
        record.setIssueId(rs.getInt("issusId"));
        record.setBookId(rs.getInt("bookId"));
        record.setMemberId(rs.getInt("memberId"));
        record.setStatus(rs.getString("Status").charAt(0));
        record.setIssueDate(rs.getDate("IssueDate").toLocalDate());
        Date returnDate = rs.getDate("ReturnDate");
        if (returnDate != null) {
            record.setReturnDate(returnDate.toLocalDate());
        }
        if(record.getIssueDate().plusDays(15).isBefore(LocalDate.now()))
        list.add(record);
		    }
		} catch (SQLException e) {
		    throw new DatabaseException("Failed to fetch issued records", e);
		}
		return list;
		}
}

