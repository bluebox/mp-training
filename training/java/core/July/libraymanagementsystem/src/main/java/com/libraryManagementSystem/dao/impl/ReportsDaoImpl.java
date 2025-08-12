package com.libraryManagementSystem.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.libraryManagementSystem.dao.ReportsDao;
import com.libraryManagementSystem.domain.CustomActiveIssuedBooks;
import com.libraryManagementSystem.domain.CustomCategoryCount;
import com.libraryManagementSystem.domain.CustomOverDueBooks;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.PreparedStatementManager;
import com.libraryManagementSystem.utilities.SQLQueries;

public class ReportsDaoImpl implements ReportsDao {
	@Override
	public List<CustomCategoryCount> getBookCountByCategory() throws DatabaseOperationException {
		List<CustomCategoryCount> books = new ArrayList<>();

		try {
			PreparedStatement ps = PreparedStatementManager.getPreparedStatement(SQLQueries.GET_BOOK_BY_CATEGORY);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String category = rs.getString("category");
				int count = rs.getInt("book_count");
				CustomCategoryCount customCategoryCount = new CustomCategoryCount(
						BookCategory.valueOf(category.toUpperCase()), count);
				books.add(customCategoryCount);
			}

		} catch (SQLException | DatabaseConnectionException | StatementPreparationException e) {
			throw new DatabaseOperationException("Error fetching book count by category: " + e.getMessage(), e);
		}

		return books;
	}

	@Override
	public List<CustomActiveIssuedBooks> getActiveIssuedBooks() throws DatabaseOperationException {
		List<CustomActiveIssuedBooks> activeBooks = new ArrayList<>();

		try {
			PreparedStatement ps = PreparedStatementManager.getPreparedStatement(SQLQueries.GET_ACTIVE_ISSUED_BOOKS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int memberId = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("book_title");
				LocalDate issuedDate = rs.getDate("issue_date").toLocalDate();

				activeBooks.add(new CustomActiveIssuedBooks(memberId, memberName, bookId, bookTitle, issuedDate));
			}

		} catch (SQLException | DatabaseConnectionException | StatementPreparationException e) {
			throw new DatabaseOperationException("Error fetching active issued books: " + e.getMessage(), e);
		}

		return activeBooks;
	}

	@Override
	public List<CustomOverDueBooks> getOverDueBooks() throws DatabaseOperationException {
		List<CustomOverDueBooks> overDueBooks = new ArrayList<>();

		try {
			PreparedStatement ps = PreparedStatementManager.getPreparedStatement(SQLQueries.GET_OVER_DUE_BOOKS);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookName = rs.getString("book_name");
				int memberId = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				LocalDate issuedDate = rs.getDate("issue_date").toLocalDate();

				overDueBooks.add(new CustomOverDueBooks(bookId, bookName, memberId, memberName, issuedDate));
			}

		} catch (SQLException | DatabaseConnectionException | StatementPreparationException e) {
			throw new DatabaseOperationException("Error fetching overdue books: " + e.getMessage(), e);
		}

		return overDueBooks;
	}

}
