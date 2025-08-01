package com.library_management.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.library_management.dao.IssueRecordDaoInterface;
import com.library_management.domain.IssueRecord;
import com.library_management.domain.IssueRecordStatus;
import com.library_management.utilities.DBConnection;

public class IssueRecordDaoImplementation implements IssueRecordDaoInterface {

	@Override
	public String issueBook(IssueRecord issue) {
		Connection conn = DBConnection.getConn();

		try {
			String memberSql = "SELECT * FROM members WHERE member_id = ?";
			PreparedStatement memberStmt = conn.prepareStatement(memberSql);
			memberStmt.setInt(1, issue.getMemberId());
			ResultSet memberRs = memberStmt.executeQuery();
			if (!memberRs.next()) {
				System.out.println("Member does not exist");
				// ResponseHandler.showResponse(message, e.getMessage(), Color.RED);
				return "Member does not exist";
			}

			String bookSql = "SELECT * FROM books WHERE book_id = ?";
			PreparedStatement bookStmt = conn.prepareStatement(bookSql);
			bookStmt.setInt(1, issue.getBookId());
			ResultSet bookRs = bookStmt.executeQuery();
			if (!bookRs.next()) {
				System.out.println("Book does not exist");
				return "Book does not exist";
			}

			char status = bookRs.getString("status").charAt(0);
			char availability = bookRs.getString("availability").charAt(0);
//			System.out.println("-----------");
//			System.out.println(bookRs.getString("title"));
//			System.out.println(bookRs.getString("author"));
//			System.out.println(bookRs.getString("category"));
//			System.out.println(status);
//			System.out.println(availability);
//			System.out.println("-----------");
			if (status != 'A') {
				System.out.println("Book is not active");
				return "Book is not active";
			}
			if (availability != 'A') {
				System.out.println("Book is not available for issue");
				return "Book is not available for issue";
			}

			String issueSql = "INSERT INTO issue_records (book_id, member_id, status, issue_date, return_date) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(issueSql);
			pstmt.setInt(1, issue.getBookId());
			pstmt.setInt(2, issue.getMemberId());
			issue.setStatus(IssueRecordStatus.I);
			pstmt.setString(3, String.valueOf(issue.getStatus()));
			pstmt.setDate(4, Date.valueOf(issue.getIssueDate()));
			pstmt.setDate(5, issue.getReturnDate() != null ? Date.valueOf(issue.getReturnDate()) : null);
			pstmt.executeUpdate();
			System.out.println("Book issued successfully");

			String updateSql = "UPDATE books SET availability = 'I' WHERE book_id = ?";
			PreparedStatement updateStmt = conn.prepareStatement(updateSql);
			updateStmt.setInt(1, issue.getBookId());
			int rows = updateStmt.executeUpdate();
			if (rows == 0) {
				System.out.println("Failed to update book availability");
				return "Failed to update book availability";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Book issued successfully";
	}

	@Override
	public String returnBook(int memberId, int bookId) {
		Connection conn = DBConnection.getConn();
		try {
			int issueId;
			String selectSql = "SELECT * FROM issue_records WHERE member_id = ? AND book_id = ? AND status = 'I'";
			PreparedStatement selectStmt = conn.prepareStatement(selectSql);
			selectStmt.setInt(1, memberId);
			selectStmt.setInt(2, bookId);
			ResultSet rs = selectStmt.executeQuery();
			if (!rs.next()) {
				System.out.println("Issue record not found or already returned");
				return "Issue record not found or already returned";
			}
			issueId = rs.getInt("issue_id");

			String updateIssueSql = "UPDATE issue_records SET status = 'R', return_date = ? WHERE issue_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(updateIssueSql);
			pstmt.setDate(1, Date.valueOf(LocalDate.now()));
			pstmt.setInt(2, issueId);
			int rows = pstmt.executeUpdate();
			if (rows == 0) {
				System.out.println("Failed to update issue record");
				return "Failed to update issue record";
			}
			System.out.println("Book returned successfully");
			logIssue(issueId);

			String updateBookSql = "UPDATE books SET availability = 'A' WHERE book_id = ?";
			PreparedStatement updateStmt = conn.prepareStatement(updateBookSql);
			updateStmt.setInt(1, bookId);
			int rowsUpdated = updateStmt.executeUpdate();
			if (rowsUpdated == 0) {
				System.out.println("Failed to update book availability");
				return "Failed to update book availability";
			}
			System.out.println("Book status updated successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Book returned successfully";
	}

	public List<IssueRecord> getOverdueBooks() {
		List<IssueRecord> overdue = new ArrayList<>();
		String sql = "SELECT * FROM issue_records WHERE status = 'I' AND issue_date < ?";
		try  {
			Connection conn = DBConnection.getConn();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDate(1, Date.valueOf(LocalDate.now().minusDays(17)));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				IssueRecord issue = new IssueRecord();
				issue.setIssueId(rs.getInt("issue_id"));
				issue.setBookId(rs.getInt("book_id"));
				issue.setMemberId(rs.getInt("member_id"));
				issue.setStatus(IssueRecordStatus.valueOf(rs.getString("status")));
				issue.setIssueDate(rs.getDate("issue_date").toLocalDate());
				issue.setReturnDate(rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return overdue;
	}

	@Override
	public List<IssueRecord> getAllIssues() {
		List<IssueRecord> issues = new ArrayList<>();
		String sql = "SELECT * FROM issue_records";
		Connection conn = DBConnection.getConn();
		try  {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				IssueRecord issue = new IssueRecord();
				issue.setIssueId(rs.getInt("issue_id"));
				issue.setBookId(rs.getInt("book_id"));
				issue.setMemberId(rs.getInt("member_id"));
				issue.setStatus(IssueRecordStatus.valueOf(rs.getString("status")));
				issue.setIssueDate(rs.getDate("issue_date").toLocalDate());
				issue.setReturnDate(rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null);
				issues.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return issues;
	}

	@Override
	public List<List<String>> getStatusTable() {
		List<List<String>> activeIssues = new ArrayList<>();
		String sql = "SELECT m.member_id, m.name, b.title, ir.status as issue_status, b.status as book_status FROM members m JOIN issue_records ir ON m.member_id = ir.member_id JOIN books b ON ir.book_id = b.book_id";
		try  {
			Connection conn = DBConnection.getConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				List<String> issue = new ArrayList<>();
				issue.add(String.valueOf(rs.getInt("member_id")));
				issue.add(rs.getString("name"));
				issue.add(rs.getString("title"));
				issue.add(rs.getString("issue_status"));
				issue.add(rs.getString("book_status"));
				activeIssues.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activeIssues;
	}

	private void logIssue(int issueId) {
		String logSql = "INSERT INTO issue_records_log (issue_id, book_id, member_id, status, issue_date, return_date) SELECT * FROM issue_records WHERE issue_records.issue_id = ?";
		Connection conn = DBConnection.getConn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(logSql);
			pstmt.setInt(1, issueId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Inserting log");
	}

}
