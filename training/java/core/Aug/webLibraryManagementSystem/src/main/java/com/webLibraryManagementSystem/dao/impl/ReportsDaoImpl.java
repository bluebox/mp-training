package com.webLibraryManagementSystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webLibraryManagementSystem.dao.ReportsDao;
import com.webLibraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.utilities.ConnectionPoolingServlet;
import com.webLibraryManagementSystem.utilities.SQLQueries;

public class ReportsDaoImpl implements ReportsDao {
	@Override
	public Map<String, Integer> getBookCountByCategory() throws DatabaseOperationException {

		Map<String, Integer> books = new HashMap<>();

		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement ps = con.prepareStatement(SQLQueries.GET_BOOK_BY_CATEGORY);) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String category = rs.getString("category");
				int count = rs.getInt("book_count");
				books.put(category, count);

			}

		} catch (SQLException e) {

			throw new DatabaseOperationException("Error fetching book count by category: " + e.getMessage(), e);
		}
		return books;

	}

	@Override
	public List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws DatabaseOperationException {
		List<CustomActiveIssuedBooks> activeBooks = new ArrayList<>();
		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement ps = con.prepareStatement(SQLQueries.GET_ACTIVE_ISSUED_BOOKS)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int memberId = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				LocalDate issuedDate = rs.getDate("issue_date").toLocalDate();

				CustomActiveIssuedBooks customActiveIssuedBooks = new CustomActiveIssuedBooks(memberId, memberName,
						bookId, bookTitle, issuedDate);

				activeBooks.add(customActiveIssuedBooks);

			}
		} catch (SQLException e) {
			throw new DatabaseOperationException("Error fetching book count by category: " + e.getMessage(), e);
		}
		return activeBooks;
	}

	@Override
	public List<CustomActiveIssuedBooks> getOverDueBooks() throws DatabaseOperationException {
		List<CustomActiveIssuedBooks> overDueBooks = new ArrayList<>();
		try (Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
				PreparedStatement ps = con.prepareStatement(SQLQueries.GET_OVER_DUE_BOOKS)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int memberId = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				LocalDate issuedDate = rs.getDate("issue_date").toLocalDate();

				CustomActiveIssuedBooks customOverDueBooks = new CustomActiveIssuedBooks(memberId, memberName, bookId,
						bookName, issuedDate);

				overDueBooks.add(customOverDueBooks);
			}
		} catch (SQLException e) {
			throw new DatabaseOperationException("Error fetching book count by category: " + e.getMessage(), e);
		}
		return overDueBooks;
	}
}
