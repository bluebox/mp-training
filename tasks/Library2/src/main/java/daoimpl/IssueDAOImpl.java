package daoimpl;

import model.IssueRecord;
import model.Book;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.IssueDAO;

public class IssueDAOImpl implements IssueDAO{

	public void issueBook(IssueRecord issue) throws Exception {
		String insert = "INSERT INTO issue_records (bookId, memberId, status, issueDate) VALUES (?, ?, ?, ?)";
		String updateBook = "UPDATE books SET availability='I' WHERE bookId=?";
		String insertBookLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement bookLogStmt = null;
		PreparedStatement stmt = null;
		PreparedStatement updateStmt = null;
		Book existingBook = null;

		try {
			conn = JDBCConnection.getConnection();
			conn.setAutoCommit(false);

			BookDAOImpl bookDAO = new BookDAOImpl();
			existingBook = bookDAO.getBookById(issue.getBookId());

			bookLogStmt = conn.prepareStatement(insertBookLog);
			bookLogStmt.setInt(1, existingBook.getBookId());
			bookLogStmt.setString(2, existingBook.getTitle());
			bookLogStmt.setString(3, existingBook.getAuthor());
			bookLogStmt.setString(4, existingBook.getCategory());
			bookLogStmt.setString(5, String.valueOf(existingBook.getStatus()));
			bookLogStmt.setString(6, String.valueOf(existingBook.getAvailability()));
			bookLogStmt.executeUpdate();

			stmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, issue.getBookId());
			stmt.setInt(2, issue.getMemberId());
			stmt.setString(3, String.valueOf(issue.getStatus()));
			stmt.setDate(4, Date.valueOf(issue.getIssueDate()));
			stmt.executeUpdate();

			updateStmt = conn.prepareStatement(updateBook);
			updateStmt.setInt(1, issue.getBookId());
			updateStmt.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception rollbackEx) {
					throw new Exception("Error during rollback: " + rollbackEx.getMessage(), rollbackEx);
				}
			}
			throw new Exception("Error issuing book: " + e.getMessage(), e);
		} finally {
			try {
				if (bookLogStmt != null)
					bookLogStmt.close();
				if (stmt != null)
					stmt.close();
				if (updateStmt != null)
					updateStmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new Exception("Error closing resources: " + e.getMessage(), e);
			}
		}
	}

	public void returnBook(int issueId, int bookId, java.time.LocalDate returnDate) throws Exception {
		String updateIssue = "UPDATE issue_records SET status='R', returnDate=? WHERE issueId=?";
		String updateBook = "UPDATE books SET availability='A' WHERE bookId=?";
		String insertLog = "INSERT INTO issue_records_log (issueId, bookId, memberId, status, issueDate, returnDate) VALUES (?, ?, ?, ?, ?, ?)";
		String insertBookLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";

		Connection conn = null;
		PreparedStatement bookLogStmt = null;
		PreparedStatement logStmt = null;
		PreparedStatement stmt = null;
		PreparedStatement updateStmt = null;
		IssueRecord existingRecord = null;
		Book existingBook = null;

		try {
			conn = JDBCConnection.getConnection();
			conn.setAutoCommit(false);

			existingRecord = getRecordById(issueId);
			if (existingRecord == null) {
				throw new Exception("Issue record not found");
			}

			BookDAOImpl bookDAO = new BookDAOImpl();
			existingBook = bookDAO.getBookById(bookId);

			bookLogStmt = conn.prepareStatement(insertBookLog);
			bookLogStmt.setInt(1, existingBook.getBookId());
			bookLogStmt.setString(2, existingBook.getTitle());
			bookLogStmt.setString(3, existingBook.getAuthor());
			bookLogStmt.setString(4, existingBook.getCategory());
			bookLogStmt.setString(5, String.valueOf(existingBook.getStatus()));
			bookLogStmt.setString(6, String.valueOf(existingBook.getAvailability()));
			bookLogStmt.executeUpdate();

			logStmt = conn.prepareStatement(insertLog);
			logStmt.setInt(1, existingRecord.getIssueId());
			logStmt.setInt(2, existingRecord.getBookId());
			logStmt.setInt(3, existingRecord.getMemberId());
			logStmt.setString(4, String.valueOf(existingRecord.getStatus()));
			logStmt.setDate(5, Date.valueOf(existingRecord.getIssueDate()));
			logStmt.setDate(6,
					existingRecord.getReturnDate() != null ? Date.valueOf(existingRecord.getReturnDate())
							: null);
			logStmt.executeUpdate();

			stmt = conn.prepareStatement(updateIssue);
			stmt.setDate(1, Date.valueOf(returnDate));
			stmt.setInt(2, issueId);
			stmt.executeUpdate();

			updateStmt = conn.prepareStatement(updateBook);
			updateStmt.setInt(1, bookId);
			updateStmt.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception rollbackEx) {
					throw new Exception("Error during rollback: " + rollbackEx.getMessage(), rollbackEx);
				}
			}
			throw new Exception("Error returning book: " + e.getMessage(), e);
		} finally {
			try {
				if (bookLogStmt != null)
					bookLogStmt.close();
				if (logStmt != null)
					logStmt.close();
				if (stmt != null)
					stmt.close();
				if (updateStmt != null)
					updateStmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				throw new Exception("Error closing resources: " + e.getMessage(), e);
			}
		}
	}

	public List<IssueRecord> getAllIssuedRecords() throws Exception {
		String query = "SELECT issueId, bookId, memberId, status, issueDate, returnDate FROM issue_records ORDER BY issueDate DESC";
		List<IssueRecord> issuedList = new ArrayList<>();
		try (Connection conn = JDBCConnection.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				IssueRecord record = new IssueRecord(rs.getInt("issueId"), rs.getInt("bookId"), rs.getInt("memberId"),
						rs.getString("status").charAt(0), rs.getDate("issueDate").toLocalDate(),
						rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null);
				issuedList.add(record);
			}
		}
		return issuedList;
	}

	public IssueRecord getRecordById(int issueId) throws Exception {
		String query = "SELECT issueId, bookId, memberId, status, issueDate, returnDate FROM issue_records WHERE issueId = ?";
		try (Connection conn = JDBCConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, issueId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new IssueRecord(rs.getInt("issueId"), rs.getInt("bookId"), rs.getInt("memberId"),
							rs.getString("status").charAt(0), rs.getDate("issueDate").toLocalDate(),
							rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null);
				}
				return null;
			}
		}
	}
}
