package dao;

import model.IssueRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class IssueDAO {

	public void issueBook(IssueRecord issue) throws Exception {
	    String insert = "INSERT INTO issue_records (bookId, memberId, status, issueDate) VALUES (?, ?, ?, ?)";
	    String updateBook = "UPDATE books SET availability='I' WHERE bookId=?";
//	    String bookLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
	    String insertLog = "INSERT INTO issue_records_log (issueId, bookId, memberId, status, issueDate, returnDate) VALUES (?, ?, ?, ?, ?, ?)";

	    try (Connection conn = JDBCConnection.getConnection()) {
	        conn.setAutoCommit(false);

	        PreparedStatement stmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
	        stmt.setInt(1, issue.getBookId());
	        stmt.setInt(2, issue.getMemberId());
	        stmt.setString(3, String.valueOf(issue.getStatus()));
	        stmt.setDate(4, java.sql.Date.valueOf(issue.getIssueDate()));
	        stmt.executeUpdate();

	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            int issueId = rs.getInt(1);

	            PreparedStatement logStmt = conn.prepareStatement(insertLog);
	            logStmt.setInt(1, issueId);
	            logStmt.setInt(2, issue.getBookId());
	            logStmt.setInt(3, issue.getMemberId());
	            logStmt.setString(4, String.valueOf(issue.getStatus()));
	            logStmt.setDate(5, java.sql.Date.valueOf(issue.getIssueDate()));
	            logStmt.setNull(6, java.sql.Types.DATE); // no return date yet
	            logStmt.executeUpdate();
	        }

	        // Update book availability
	        PreparedStatement updateStmt = conn.prepareStatement(updateBook);
	        updateStmt.setInt(1, issue.getBookId());
	        updateStmt.executeUpdate();
	        

	        conn.commit();
	    }
	}

    public void returnBook(int issueId, int bookId, java.time.LocalDate returnDate) throws Exception {
        String updateIssue = "UPDATE issue_records SET status='R', returnDate=? WHERE issueId=?";
        String updateBook = "UPDATE books SET availability='A' WHERE bookId=?";
        String insertLog = "INSERT INTO issue_records_log (issueId, bookId, memberId, status, issueDate, returnDate) SELECT IssueId, BookId, MemberId, Status, IssueDate, ? FROM issue_records WHERE IssueId=?";
        try (Connection conn = JDBCConnection.getConnection()) {
            conn.setAutoCommit(false);
            // Update issue record
            PreparedStatement stmt = conn.prepareStatement(updateIssue);
            stmt.setDate(1, java.sql.Date.valueOf(returnDate));
            stmt.setInt(2, issueId);
            stmt.executeUpdate();
            // Update book availability
            PreparedStatement updateStmt = conn.prepareStatement(updateBook);
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();
            // Log the return
            PreparedStatement logStmt = conn.prepareStatement(insertLog);
            logStmt.setDate(1, java.sql.Date.valueOf(returnDate));
            logStmt.setInt(2, issueId);
            logStmt.executeUpdate();
            conn.commit();
        }
    }
}
