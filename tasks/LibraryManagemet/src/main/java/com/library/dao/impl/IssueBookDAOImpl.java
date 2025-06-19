package com.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.library.dao.queries.IssueTableSQLQueries;
import com.library.domain.IssueRecord;
import com.library.utilities.ConnectionMaker;

public class IssueBookDAOImpl extends IssueTableSQLQueries implements com.library.dao.IssueBookDAO {

	private final Connection conn = ConnectionMaker.getConnection();

	public void issueBook(Connection conn, int bookId, int memberId) {
		try (PreparedStatement ps = conn.prepareStatement(insertIntoIssueRecords)) {

			ps.setInt(1, bookId);
			ps.setInt(2, memberId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean returnBook(int bookId, int memberId, Connection conn) {
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

	public boolean logReturn(int bookId, int memberId, Connection conn) {
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

	public boolean isBookIssuedToMember(int bookId, int memberId, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(isBookIssued)) {
			ps.setInt(1, bookId);
			ps.setInt(2, memberId);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<IssueRecord> getAllIssuedBooks(Connection conn) {
		List<IssueRecord> list = new ArrayList<>();
		try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(showAllIssuedRecords)) {
			while (rs.next()) {
				list.add(new IssueRecord(rs.getInt("BookId"), rs.getInt("MemberId"), rs.getDate("IssueDate"),
						rs.getDate("ReturnDate")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
