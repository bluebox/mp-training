package DAO;

import casestudy.IssueRecord; 
import casestudy.DatabaseUtil; 
import casestudy.LibraryException;
import java.sql.*; 
import java.time.LocalDate; 
import java.util.ArrayList; 
import java.util.List;

public class IssueDAO { 
	public void issueBook(IssueRecord issue) throws LibraryException { 
		Connection conn = null; 
		try { 
			conn = DatabaseUtil.getConnection();
			// conn.setAutoCommit(false);

			// Check if member exists
			String memberSql = "SELECT COUNT(*) FROM members WHERE MemberId = ?";
			try (PreparedStatement memberStmt = conn.prepareStatement(memberSql)) {
				memberStmt.setInt(1, issue.getMemberId());
				ResultSet rs = memberStmt.executeQuery();
				if (!rs.next() || rs.getInt(1) == 0) {
					throw new LibraryException("Member does not exist");
				}
			}

			// Check book status and lock the row
			String bookSql = "SELECT Status, Availability FROM books WHERE BookId = ? FOR UPDATE";
			try (PreparedStatement bookStmt = conn.prepareStatement(bookSql)) {
				bookStmt.setInt(1, issue.getBookId());
				ResultSet rs = bookStmt.executeQuery();
				if (!rs.next()) {
					throw new LibraryException("Book does not exist");
				}
				char status = rs.getString("Status").charAt(0);
				char availability = rs.getString("Availability").charAt(0);
				
				if (status != 'A') {
					throw new LibraryException("Book is not active");
				}
				if (availability != 'A') {
					throw new LibraryException("Book is not available for issue");
				}
			}

			// Check if member already has this book
			String existingSql = "SELECT COUNT(*) FROM issue WHERE MemberId = ? AND BookId = ? AND Status = 'I'";
			try (PreparedStatement existingStmt = conn.prepareStatement(existingSql)) {
				existingStmt.setInt(1, issue.getMemberId());
				existingStmt.setInt(2, issue.getBookId());
				ResultSet rs = existingStmt.executeQuery();
				if (rs.next() && rs.getInt(1) > 0) {
					throw new LibraryException("Member already has this book issued");
				}
			}

			// Insert issue record
			String issueSql = "INSERT INTO issue (BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?)";
			int issueId;
			try (PreparedStatement pstmt = conn.prepareStatement(issueSql, Statement.RETURN_GENERATED_KEYS)) {
				pstmt.setInt(1, issue.getBookId());
				pstmt.setInt(2, issue.getMemberId());
				pstmt.setString(3, String.valueOf(issue.getStatus()));
				pstmt.setDate(4, Date.valueOf(issue.getIssueDate()));
				pstmt.setDate(5, issue.getReturnDate() != null ? Date.valueOf(issue.getReturnDate()) : null);
				pstmt.executeUpdate();

				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						issueId = rs.getInt(1);
						logIssueAction(issueId, "ISSUE");
					} else {
						throw new LibraryException("Failed to retrieve issue ID");
					}
				}
			}

			// Update book availability
			String updateSql = "UPDATE books SET Availability = 'I' WHERE BookId = ?";
			try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
				updateStmt.setInt(1, issue.getBookId());
				int rows = updateStmt.executeUpdate();
				if (rows == 0) {
					throw new LibraryException("Failed to update book availability");
				}
			}

			conn.commit();
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException ex) {
				throw new LibraryException("Rollback failed: " + ex.getMessage());
			}
			throw new LibraryException("Error issuing book: " + e.getMessage());
		} finally {
			if (conn != null) {
				// conn.setAutoCommit(true);
				DatabaseUtil.closeConnection(conn);
			}
		}
	}

	public void returnBook(int issueId) throws LibraryException {
		Connection conn = null;
		try {
			conn = DatabaseUtil.getConnection();
			// conn.setAutoCommit(false);

			// Verify issue record and get BookId
			String selectSql = "SELECT BookId FROM issue WHERE IssueId = ? AND Status = 'I' FOR UPDATE";
			int bookId;
			try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
				selectStmt.setInt(1, issueId);
				ResultSet rs = selectStmt.executeQuery();
				if (!rs.next()) {
					throw new LibraryException("Issue record not found or already returned");
				}
				bookId = rs.getInt("BookId");
			}

			// Update issue record
			String updateIssueSql = "UPDATE issue SET Status = 'R', ReturnDate = ? WHERE IssueId = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(updateIssueSql)) {
				pstmt.setDate(1, Date.valueOf(LocalDate.now()));
				pstmt.setInt(2, issueId);
				int rows = pstmt.executeUpdate();
				if (rows == 0) {
					throw new LibraryException("Failed to update issue record");
				}
				logIssueAction(issueId, "RETURN");
			}

			// Update book availability
			String updateBookSql = "UPDATE books SET Availability = 'A' WHERE BookId = ?";
			try (PreparedStatement updateStmt = conn.prepareStatement(updateBookSql)) {
				updateStmt.setInt(1, bookId);
				int rows = updateStmt.executeUpdate();
				if (rows == 0) {
					throw new LibraryException("Failed to update book availability");
				}
			}

			conn.commit();
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
				}
			} catch (SQLException ex) {
				throw new LibraryException("Rollback failed: " + ex.getMessage());
			}
			throw new LibraryException("Error returning book: " + e.getMessage());
		} finally {
			if (conn != null) {
				// conn.setAutoCommit(true);
				DatabaseUtil.closeConnection(conn);
			}
		}
	}

	public List<IssueRecord> getOverdueBooks() throws LibraryException {
		List<IssueRecord> overdue = new ArrayList<>();
		String sql = "SELECT * FROM issue WHERE Status = 'I' AND IssueDate < ?";
		try (Connection conn = DatabaseUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setDate(1, Date.valueOf(LocalDate.now().minusDays(14))); // 14 days overdue
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				IssueRecord issue = new IssueRecord();
				issue.setIssueId(rs.getInt("IssueId"));
				issue.setBookId(rs.getInt("BookId"));
				issue.setMemberId(rs.getInt("MemberId"));
				issue.setStatus(rs.getString("Status").charAt(0));
				issue.setIssueDate(rs.getDate("IssueDate").toLocalDate());
				issue.setReturnDate(rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null);
				overdue.add(issue);
			}
		} catch (SQLException e) {
			throw new LibraryException("Error retrieving overdue books: " + e.getMessage());
		}
		return overdue;
	}

	public List<IssueRecord> getAllIssues() throws LibraryException {
		List<IssueRecord> issues = new ArrayList<>();
		String sql = "SELECT * FROM issue";
		try (Connection conn = DatabaseUtil.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				IssueRecord issue = new IssueRecord();
				issue.setIssueId(rs.getInt("IssueId"));
				issue.setBookId(rs.getInt("BookId"));
				issue.setMemberId(rs.getInt("MemberId"));
				issue.setStatus(rs.getString("Status").charAt(0));
				issue.setIssueDate(rs.getDate("IssueDate").toLocalDate());
				issue.setReturnDate(rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null);
				issues.add(issue);
			}
		} catch (SQLException e) {
			throw new LibraryException("Error retrieving issues: " + e.getMessage());
		}
		return issues;
	}

	private void logIssueAction(int issueId, String action) throws SQLException {
		String logSql = "INSERT INTO issue_log (IssueId, Action, ActionDate) VALUES (?, ?, NOW())";
		try (Connection conn = DatabaseUtil.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(logSql)) {
			pstmt.setInt(1, issueId);
			pstmt.setString(2, action);
			pstmt.executeUpdate();
		}
	}
}
