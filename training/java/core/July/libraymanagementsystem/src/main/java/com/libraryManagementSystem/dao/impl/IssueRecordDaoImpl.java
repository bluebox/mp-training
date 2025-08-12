package com.libraryManagementSystem.dao.impl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.libraryManagementSystem.dao.BookDao;
import com.libraryManagementSystem.dao.IssueRecordDao;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.domain.IssueRecord;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseConnectionException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidIssueDataException;
import com.libraryManagementSystem.exceptions.StatementPreparationException;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.DBConnection;
import com.libraryManagementSystem.utilities.IssueStatus;
import com.libraryManagementSystem.utilities.PreparedStatementManager;
import com.libraryManagementSystem.utilities.SQLQueries;

public class IssueRecordDaoImpl implements IssueRecordDao {

	BookDao bookDaoImpl = new BookDaoImpl();

	@Override
	public void issueBook(IssueRecord newIssue, Book book)
			throws DatabaseConnectionException, DatabaseOperationException {
		try {

			DBConnection.setAutoCommit(false);
			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.ISSUE_INSERT);

			stmt.setInt(1, newIssue.getBookId());
			stmt.setInt(2, newIssue.getMemberId());
			stmt.setString(3, String.valueOf(newIssue.getStatus().toString().charAt(0)));
			stmt.setDate(4, Date.valueOf(newIssue.getIssueDate()));

			int effectedRows = stmt.executeUpdate();
			if (effectedRows <= 0) {
				DBConnection.rollback();
				throw new DatabaseOperationException("Issue Book Failed!");
			}

			bookDaoImpl.updateBookAvailability(book, BookAvailability.ISSUED);

			DBConnection.commit();
			DBConnection.setAutoCommit(true);

		} catch (SQLException | BookNotFoundException | DatabaseOperationException | DatabaseConnectionException
				| StatementPreparationException e) {
			try {
				DBConnection.rollback();
				DBConnection.setAutoCommit(true);
				throw new DatabaseOperationException(e.getMessage());
			} catch (SQLException e1) {

				throw new DatabaseConnectionException("Error in Server");
			}

		}

	}

	@Override
	public void returnBook(Book book, int id, LocalDate date) throws DatabaseOperationException {
		try {

			PreparedStatement stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.ISSUE_SELECT_RETURN_DATE);

			stmt.setInt(1, book.getBookId());

			stmt.setInt(2, id);

			ResultSet res = stmt.executeQuery();
			IssueRecord issue = null;

			if (res.next()) {

				int issueId = res.getInt("issue_id");
				int bookId = res.getInt("book_id");
				int memberId = res.getInt("member_id");
				IssueStatus status = IssueStatus.fromDbName(res.getString("status"));
				LocalDate issueDate = res.getDate("issue_date").toLocalDate();
				Date sqlDate = res.getDate("return_date");
				LocalDate returnDate = sqlDate == null ? null : sqlDate.toLocalDate();

				issue = new IssueRecord(issueId, bookId, memberId, status, issueDate, returnDate);

			} else {
				throw new BookNotFoundException("Issue record Not Found ");
			}

			if (issue.getIssueDate().isAfter(date)) {
				throw new InvalidIssueDataException("Date Should greater than issue date");
			}
			DBConnection.setAutoCommit(false);

			PreparedStatement stmt1 = PreparedStatementManager
					.getPreparedStatement(SQLQueries.ISSUE_UPDATE_RETURN_DATE);

			stmt1.setDate(1, Date.valueOf(date));
			stmt1.setInt(2, issue.getIssueId());

			stmt1.executeUpdate();

			System.out.println("herererer");

			issueLog(issue);

			bookDaoImpl.updateBookAvailability(book, BookAvailability.AVAILABLE);

			DBConnection.commit();
			DBConnection.setAutoCommit(true);

		} catch (SQLException | DatabaseConnectionException | StatementPreparationException | BookNotFoundException
				| DatabaseOperationException | InvalidIssueDataException e) {
			try {
				DBConnection.rollback();
				DBConnection.setAutoCommit(true);
				System.out.println(e.getMessage());
				throw new DatabaseOperationException(e.getMessage());
			} catch (SQLException e1) {

				throw new DatabaseOperationException("Error in Server");
			}

		}

	}

	@Override
	public void issueLog(IssueRecord issue) throws DatabaseOperationException {
		PreparedStatement stmt;
		try {

			stmt = PreparedStatementManager.getPreparedStatement(SQLQueries.ISSUE_LOG_INSERT);

			stmt.setInt(1, issue.getIssueId());
			stmt.setInt(2, issue.getBookId());
			stmt.setInt(3, issue.getMemberId());
			stmt.setString(4, String.valueOf(issue.getStatus().toString().charAt(0)));
			stmt.setDate(5, Date.valueOf(issue.getIssueDate()));
			stmt.setDate(6, issue.getReturnDate() == null ? null : Date.valueOf(issue.getReturnDate()));

			stmt.executeUpdate();

		} catch (SQLException | DatabaseConnectionException | StatementPreparationException e) {

			throw new DatabaseOperationException(e.getMessage());
		}

	}

}
