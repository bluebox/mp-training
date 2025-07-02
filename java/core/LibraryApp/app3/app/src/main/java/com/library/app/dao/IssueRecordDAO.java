package com.library.app.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.library.app.model.IssueRecord;
import com.library.app.utilities.DBUtil;


public class IssueRecordDAO {
	private Connection conn;

	public IssueRecordDAO() throws SQLException {
		conn = DBUtil.getConnection();
	}

	public void issueBook(int bookId, int memberId) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		try (PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO issue_records (BookId, MemberId, Status, IssueDate,ReturnDate) VALUES (?, ?, 'I', ?, ?)")) {
			PreparedStatement ps3 = conn.prepareStatement(
					"INSERT INTO issue_records_log (BookId, MemberId, Status, IssueDate,ReturnDate) VALUES (?, ?, 'I', ?, ?)");
			ps3.setInt(1, bookId);
			ps3.setInt(2, memberId);
			ps3.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			ps3.setDate(4, java.sql.Date.valueOf(LocalDate.now().plusDays(15)));
			ps3.executeUpdate();
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM books WHERE BookId=?");
			ps2.setInt(1, bookId);
			ResultSet rs = ps2.executeQuery();

			ps.setInt(1, bookId);
			ps.setInt(2, memberId);
			ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			ps.setDate(4, java.sql.Date.valueOf(LocalDate.now().plusDays(15)));
			ps.executeUpdate();
			ps2.close();
			ps3.close();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	public void returnBook(int issueId) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		try (
				PreparedStatement ps = conn
						.prepareStatement("UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueId=?")) {
			PreparedStatement ps2 = conn
					.prepareStatement("INSERT INTO issue_records_log (IssueId, Status, ReturnDate) VALUES (?, 'R', ?)");
			ps2.setInt(1, issueId);
			ps2.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
			ps2.executeUpdate();
			ps2.close();

			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ps.setInt(2, issueId);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
		}
	}

	public IssueRecord getIssueById(int issueId) throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		IssueRecord issue = null;
		try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM issue_records WHERE IssueId = ?")) {
			ps.setInt(1, issueId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				issue = new IssueRecord();
				issue.setIssueId(rs.getInt("IssueId"));
				issue.setBookId(rs.getInt("BookId"));
				issue.setMemberId(rs.getInt("MemberId"));
				issue.setStatus(rs.getString("Status").charAt(0));
				issue.setIssueDate(rs.getDate("IssueDate").toLocalDate());
				Date returnDate = rs.getDate("ReturnDate");
				if (returnDate != null)
					issue.setReturnDate(returnDate.toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return issue;
	}

	public List<IssueRecord> getAllIssuedRecords() throws SQLException {
		if(conn==null||conn.isClosed()) {
			conn=DBUtil.getConnection();
		}
		List<IssueRecord> records = new ArrayList<>();
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM issue_records")) {
			while (rs.next()) {
				IssueRecord issue = new IssueRecord();
				issue.setIssueId(rs.getInt("IssueId"));
				issue.setBookId(rs.getInt("BookId"));
				issue.setMemberId(rs.getInt("MemberId"));
				issue.setStatus(rs.getString("Status").charAt(0));
				issue.setIssueDate(rs.getDate("IssueDate").toLocalDate());
				Date returnDate = rs.getDate("ReturnDate");
				if (returnDate != null)
					issue.setReturnDate(returnDate.toLocalDate());
				records.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}
}

