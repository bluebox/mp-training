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
	    String insert = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, ?, ?)";
	    String updateBook = "UPDATE books SET Availability='I' WHERE BookId=?";
	    String insertLog = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, ?)";

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

   
}
