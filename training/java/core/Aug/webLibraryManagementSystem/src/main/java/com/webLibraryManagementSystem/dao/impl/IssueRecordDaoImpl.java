package com.webLibraryManagementSystem.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.webLibraryManagementSystem.dao.BookDao;
import com.webLibraryManagementSystem.dao.IssueRecordDao;
import com.webLibraryManagementSystem.domain.Book;
import com.webLibraryManagementSystem.domain.IssueRecord;
import com.webLibraryManagementSystem.exceptions.BookNotFoundException;
import com.webLibraryManagementSystem.exceptions.DatabaseConnectionException;
import com.webLibraryManagementSystem.exceptions.DatabaseOperationException;
import com.webLibraryManagementSystem.exceptions.InvalidIssueDataException;
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystem.utilities.ConnectionPoolingServlet;
import com.webLibraryManagementSystem.utilities.IssueStatus;
import com.webLibraryManagementSystem.utilities.SQLQueries;

public class IssueRecordDaoImpl implements IssueRecordDao {

	BookDao bookDaoImpl = new BookDaoImpl();

	@Override
	public void issueBook(IssueRecord newIssue, Book book)
			throws DatabaseConnectionException, DatabaseOperationException {
		Connection con = null;
		PreparedStatement stmt = null;

		try {
			con = ConnectionPoolingServlet.getDataSource().getConnection();
			stmt = con.prepareStatement(SQLQueries.ISSUE_INSERT);

			stmt.setInt(1, newIssue.getBookId());
			stmt.setInt(2, newIssue.getMemberId());
			stmt.setString(3, newIssue.getStatus().getDbName());
			stmt.setDate(4, Date.valueOf(newIssue.getIssueDate()));

			con.setAutoCommit(false);

			stmt.executeUpdate();
			bookDaoImpl.updateBookAvailability(book, BookAvailability.ISSUED, con);
			con.commit();

		} catch (SQLException | BookNotFoundException | DatabaseOperationException e) {
			try {
				con.rollback();

			} catch (SQLException e1) {
				throw new DatabaseConnectionException("Issue Book Roll back" + e.getMessage());
			}
			throw new DatabaseOperationException("Issue Book Roll back" + e.getMessage());

		} finally {
			try {
				stmt.close();
				con.setAutoCommit(true);
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void returnBook(Book book, int memberId, LocalDate date)
			throws DatabaseOperationException, InvalidIssueDataException {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;

		try {
			con = ConnectionPoolingServlet.getDataSource().getConnection();
			stmt = con.prepareStatement(SQLQueries.ISSUE_SELECT_RETURN_DATE);

			stmt.setInt(1, book.getBookId());

			stmt.setInt(2, memberId);

			ResultSet res = stmt.executeQuery();
			IssueRecord issue = null;

			if (res.next()) {

				int issueId = res.getInt("issue_id");
				int bookId = res.getInt("book_id");
				int issueMemberId = res.getInt("member_id");
				IssueStatus status = IssueStatus.fromDbName(res.getString("status"));
				LocalDate issueDate = res.getDate("issue_date").toLocalDate();
				Date sqlDate = res.getDate("return_date");
				LocalDate returnDate = sqlDate == null ? null : sqlDate.toLocalDate();

				issue = new IssueRecord(issueId, bookId, issueMemberId, status, issueDate, returnDate);

			} else {
				throw new InvalidIssueDataException("Issue record Not Found ");
			}

			if (issue.getIssueDate().isAfter(date)) {
				throw new InvalidIssueDataException("return date Must be after Issue Date");
			}

			stmt1 = con.prepareStatement(SQLQueries.ISSUE_UPDATE_RETURN_DATE);
			stmt1.setDate(1, Date.valueOf(date));
			stmt1.setInt(2, issue.getIssueId());

			con.setAutoCommit(false);

			stmt1.executeUpdate();

			issueLog(issue, con);

			bookDaoImpl.updateBookAvailability(book, BookAvailability.AVAILABLE, con);

			con.commit();

		} catch (SQLException | BookNotFoundException | DatabaseOperationException e) {
			try {
				con.rollback();

			} catch (SQLException e1) {
				throw new DatabaseOperationException("return Book Roll back" + e.getMessage());
			}
			throw new DatabaseOperationException("return Book Roll back" + e.getMessage());

		} finally {
			try {

				con.setAutoCommit(true);
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void issueLog(IssueRecord issue, Connection con) throws DatabaseOperationException {
		try {
			PreparedStatement stmt = con.prepareStatement(SQLQueries.ISSUE_LOG_INSERT);
			stmt.setInt(1, issue.getIssueId());
			stmt.setInt(2, issue.getBookId());
			stmt.setInt(3, issue.getMemberId());
			stmt.setString(4, String.valueOf(issue.getStatus().toString().charAt(0)));
			stmt.setDate(5, Date.valueOf(issue.getIssueDate()));
			stmt.setDate(6, issue.getReturnDate() == null ? null : Date.valueOf(issue.getReturnDate()));

			stmt.executeUpdate();

		} catch (SQLException e) {

			throw new DatabaseOperationException(e.getMessage());
		}

	}

}
