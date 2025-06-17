package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.domain.IssueRecord;
import com.library.queries.IssueTableSQLQueries;
import com.library.utilities.ConnectionMaker;


public class IssueBookDAO extends IssueTableSQLQueries {

	private final Connection conn = ConnectionMaker.getConnection();

	public boolean isBookAvailable(int bookId) throws SQLException {
		String sql = "SELECT status, availability FROM book WHERE id = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, bookId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return "A".equals(rs.getString("status")) && "A".equals(rs.getString("availability"));
				}
			}
		}
		return false;
	}

	public void issueBook(IssueRecord record) throws SQLException {
		String sql = "INSERT INTO issue_records(BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', NOW())";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, record.getBookId());
			ps.setInt(2, record.getMemberId());
			ps.executeUpdate();
		}

		String logSql = "INSERT INTO issue_log(BookId, MemberId, Action, ActionTime) VALUES (?, ?, 'ISSUE', NOW())";
		try (PreparedStatement logStmt = conn.prepareStatement(logSql)) {
			logStmt.setInt(1, record.getBookId());
			logStmt.setInt(2, record.getMemberId());
			logStmt.executeUpdate();
		}

		try (PreparedStatement update = conn.prepareStatement("UPDATE book SET availability = 'I' WHERE id = ?")) {
			update.setInt(1, record.getBookId());
			update.executeUpdate();
		}
	}

	public boolean returnBook(int bookId, int memberId, Connection conn) throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement(updateReturnBook)) {
			ps.setInt(1, bookId);
			ps.setInt(2, memberId);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean logReturn(int bookId, int memberId, Connection conn ) {
		try (PreparedStatement ps = conn.prepareStatement(returnIssueLog)) {
			ps.setInt(1, bookId);
			ps.setInt(2, memberId);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isBookIssuedToMember(int bookId, int memberId, Connection conn) throws SQLException {
		try (PreparedStatement ps = conn.prepareStatement(isBookIssued)) {
			ps.setInt(1, bookId);
			ps.setInt(2, memberId);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}
	
	public List<IssueRecord> getAllIssuedBooks() throws SQLException {
		List<IssueRecord> list = new ArrayList<>();
		String sql = "SELECT * FROM issue_records";
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new IssueRecord(rs.getInt("BookId"), rs.getInt("MemberId"), rs.getDate("IssueDate"),
						rs.getDate("ReturnDate")));
			}
		}
		return list;
	}
}
