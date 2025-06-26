package com.casestudy.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.casestudy.dao.models.IssueRecordDaoModel;
import com.casestudy.domain.IssueRecord;
import com.casestudy.domain.RecordStatus;
import com.casestudy.util.DBUtil;

public class IssueRecordDao implements IssueRecordDaoModel {

	private Connection conn;

	public void issueBook(IssueRecord issueRecord) throws SQLException {
		conn = DBUtil.getConnection();
		conn.setAutoCommit(false);

		// Step 1: Insert into IssueRecords
		String sql = "INSERT INTO IssueRecords (bookId, memberId, status, issueDate) VALUES (?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, issueRecord.getBookId());
		ps.setInt(2, issueRecord.getMemberId());
		ps.setString(3, "I"); // Issued
		ps.setDate(4, Date.valueOf(issueRecord.getIssueDate()));

		int rowsInserted = ps.executeUpdate();

		if (rowsInserted > 0) {
			System.out.println("Book issued successfully.");
		} else {
			System.out.println("Book issue failed.");
			conn.rollback();
			ps.close();
			conn.close();
			return;
		}

		ps.close();

		// Step 2: Log into LogIssueRecords
		sql = "INSERT INTO LogIssueRecords (bookId, memberId, status, issueDate) VALUES (?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, issueRecord.getBookId());
		ps.setInt(2, issueRecord.getMemberId());
		ps.setString(3, "I");
		ps.setDate(4, Date.valueOf(issueRecord.getIssueDate()));

		int logInserted = ps.executeUpdate();

		if (logInserted > 0) {
			System.out.println("Issue logged successfully.");
			conn.commit();
		} else {
			System.out.println("Logging issue failed.");
			conn.rollback();
		}

		ps.close();
		conn.close();
	}

	public void returnBook(IssueRecord issueRecord) throws SQLException {
		conn = DBUtil.getConnection();
		conn.setAutoCommit(false);

		// Step 1: Update status and return date in IssueRecords
		String sql = "UPDATE IssueRecords SET status = ?, returnDate = ? WHERE bookId = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "R"); // Returned
		ps.setDate(2, Date.valueOf(LocalDate.now()));
		ps.setInt(3, issueRecord.getBookId());

		int rowsUpdated = ps.executeUpdate();

		if (rowsUpdated > 0) {
			System.out.println("Book return updated successfully in IssueRecords.");
		} else {
			System.out.println("Book return update failed.");
			conn.rollback();
			ps.close();
			conn.close();
			return;
		}

		ps.close();

		// Step 2: Insert return record into LogIssueRecords
		sql = "INSERT INTO LogIssueRecords (bookId, memberId, status, issueDate, returnDate) VALUES (?, ?, ?, ?, ?)";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, issueRecord.getBookId());
		ps.setInt(2, issueRecord.getMemberId());
		ps.setString(3, "R"); // Returned
		ps.setDate(4, Date.valueOf(issueRecord.getIssueDate())); // Should match original issue date
		ps.setDate(5, Date.valueOf(LocalDate.now()));

		int logInserted = ps.executeUpdate();

		if (logInserted > 0) {
			System.out.println("Return logged successfully.");
			conn.commit();
		} else {
			System.out.println("Return logging failed.");
			conn.rollback();
		}

		ps.close();
		conn.close();
	}

	public List<IssueRecord> getIssuedBooks() {
		List<IssueRecord> overdueList = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT bookId, memberId, status, issueDate, returnDate " + "FROM IssueRecords "
					+ "WHERE status = ? AND issueDate < ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, RecordStatus.ISSUED.getCode());
			ps.setDate(2, Date.valueOf(LocalDate.now().minusMonths(1))); // 1 month ago

			rs = ps.executeQuery();

			while (rs.next()) {
				int issueId = rs.getInt("issueId");
				int bookId = rs.getInt("bookId");
				int memberId = rs.getInt("memberId");
				RecordStatus status = RecordStatus.fromCode(rs.getString("status"));
				LocalDate issueDate = rs.getDate("issueDate").toLocalDate();
				Date returnDateRaw = rs.getDate("returnDate");
				LocalDate returnDate = returnDateRaw != null ? returnDateRaw.toLocalDate() : null;

				IssueRecord record = new IssueRecord(issueId, bookId, memberId, status, issueDate, returnDate);
				overdueList.add(record);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ignored) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ignored) {
			}
		}

		return overdueList;
	}

	public List<IssueRecord> getAllIssuedRecords() {
		List<IssueRecord> allIssuedBooks = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT issueId,bookId, memberId, status, issueDate, returnDate " + "FROM IssueRecords";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				int issueId = rs.getInt("issueId");
				int bookId = rs.getInt("bookId");
				int memberId = rs.getInt("memberId");
				RecordStatus status = RecordStatus.fromCode(rs.getString("status"));
				LocalDate issueDate = rs.getDate("issueDate").toLocalDate();
				Date returnDateRaw = rs.getDate("returnDate");
				LocalDate returnDate = returnDateRaw != null ? returnDateRaw.toLocalDate() : null;

				IssueRecord record = new IssueRecord(issueId, bookId, memberId, status, issueDate, returnDate);
				allIssuedBooks.add(record);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ignored) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ignored) {
			}
		}

		return allIssuedBooks;
	}

	public List<IssueRecord> getActiveIssuedBooks() {
		List<IssueRecord> activeIssuedBooks = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT issueId,bookId, memberId, status, issueDate, returnDate " + "FROM IssueRecords "
					+ "WHERE status = ? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, RecordStatus.ISSUED.getCode());

			rs = ps.executeQuery();

			while (rs.next()) {
				int issueId = rs.getInt("issueId");
				int bookId = rs.getInt("bookId");
				int memberId = rs.getInt("memberId");
				RecordStatus status = RecordStatus.fromCode(rs.getString("status"));
				LocalDate issueDate = rs.getDate("issueDate").toLocalDate();
				Date returnDateRaw = rs.getDate("returnDate");
				LocalDate returnDate = returnDateRaw != null ? returnDateRaw.toLocalDate() : null;

				IssueRecord record = new IssueRecord(issueId, bookId, memberId, status, issueDate, returnDate);
				activeIssuedBooks.add(record);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException ignored) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException ignored) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException ignored) {
			}
		}

		return activeIssuedBooks;
	}

	public boolean alreadyIssued(IssueRecord issueRecord) {
		String isReturned = "SELECT status FROM IssueRecords WHERE bookId = ? AND memberId = ? AND status = 'I'";

		try {
			conn = DBUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement(isReturned);

			ps.setInt(1, issueRecord.getBookId());
			ps.setInt(2, issueRecord.getMemberId());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("insdie  rs.next() alreadyissued , ");
				if ("I".equals(rs.getString("status"))) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("insdie alreadyissued , ");
		return false;
	}

}
