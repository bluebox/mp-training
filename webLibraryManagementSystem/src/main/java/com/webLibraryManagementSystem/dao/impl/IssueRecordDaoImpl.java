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
import com.webLibraryManagementSystem.utilities.ConnectionPoolingServlet;
import com.webLibraryManagementSystem.utilities.IssueStatus;
import com.webLibraryManagementSystem.utilities.SQLQueries;
import com.webLibraryManagementSystemexceptions.InvalidException;

public class IssueRecordDaoImpl implements IssueRecordDao {

	BookDao bookDaoImpl = new BookDaoImpl();

	@Override
	public void issueBook(IssueRecord newIssue, Book book) throws InvalidException {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtAvailability = null;
		PreparedStatement stmtBookLog = null;
		try {
			con = ConnectionPoolingServlet.getDataSource().getConnection();
			stmt = con.prepareStatement(SQLQueries.ISSUE_INSERT);

			stmt.setInt(1, newIssue.getBookId());
			stmt.setInt(2, newIssue.getMemberId());
			stmt.setString(3, String.valueOf(newIssue.getStatus().toString().charAt(0)));
			stmt.setDate(4, Date.valueOf(newIssue.getIssueDate()));

			con.setAutoCommit(false);

			stmt.executeUpdate();

			stmtAvailability = con.prepareStatement(SQLQueries.BOOK_UPDATE_AVAILABILITY);
			stmtBookLog = con.prepareStatement(SQLQueries.BOOKS_LOG_INSERT);

			stmtAvailability.setString(1, "I");
			stmtAvailability.setInt(2, book.getBookId());

			stmtAvailability.executeUpdate();
			bookDaoImpl.bookLog(book, con, stmtBookLog);

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);

			} catch (SQLException e1) {
				throw new InvalidException("Issue Book Roll back" + e.getMessage());
			}
			throw new InvalidException("Issue Book Roll back" + e.getMessage());

		} finally {
			try {
				con.close();
				stmt.close();
				stmtAvailability.close();
				stmtBookLog.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void returnBook(Book book, int memberId, LocalDate date) throws InvalidException {
		Connection con = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtAvailability = null;
		PreparedStatement stmtBookLog = null;
		PreparedStatement stmt1 = null;
		PreparedStatement IssueLogstmt = null;
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
				IssueStatus status = res.getString("status").equalsIgnoreCase("I") ? IssueStatus.ISSUED
						: IssueStatus.RETURNED;
				LocalDate issueDate = res.getDate("issue_date").toLocalDate();
				Date sqlDate = res.getDate("return_date");
				LocalDate returnDate = sqlDate == null ? null : sqlDate.toLocalDate();

				issue = new IssueRecord(issueId, bookId, issueMemberId, status, issueDate, returnDate);

			} else {
				throw new InvalidException("Issue record Not Found ");
			}

			if (issue.getIssueDate().isAfter(date)) {
				throw new InvalidException("return date Must be after Issue Date");
			}

			stmt1 = con.prepareStatement(SQLQueries.ISSUE_UPDATE_RETURN_DATE);
			stmt1.setDate(1, Date.valueOf(date));
			stmt1.setInt(2, issue.getIssueId());

			con.setAutoCommit(false);

			stmt1.executeUpdate();
			IssueLogstmt = con.prepareStatement(SQLQueries.ISSUE_LOG_INSERT);
			issueLog(issue, con, IssueLogstmt);

			stmtAvailability = con.prepareStatement(SQLQueries.BOOK_UPDATE_AVAILABILITY);
			stmtBookLog = con.prepareStatement(SQLQueries.BOOKS_LOG_INSERT);

			stmtAvailability.setString(1, "A");
			stmtAvailability.setInt(2, book.getBookId());

			stmtAvailability.executeUpdate();

			bookDaoImpl.bookLog(book, con, stmtBookLog);

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			try {
				con.rollback();
				con.setAutoCommit(true);

			} catch (SQLException e1) {
				throw new InvalidException("return Book Roll back" + e.getMessage());
			}
			throw new InvalidException("return Book Roll back" + e.getMessage());

		} finally {
			try {
				con.close();
				stmt.close();
				stmtAvailability.close();
				stmtBookLog.close();
				stmt1.close();
				IssueLogstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void issueLog(IssueRecord issue, Connection con, PreparedStatement stmt) throws InvalidException {
		try {

			stmt.setInt(1, issue.getIssueId());
			stmt.setInt(2, issue.getBookId());
			stmt.setInt(3, issue.getMemberId());
			stmt.setString(4, String.valueOf(issue.getStatus().toString().charAt(0)));
			stmt.setDate(5, Date.valueOf(issue.getIssueDate()));
			stmt.setDate(6, issue.getReturnDate() == null ? null : Date.valueOf(issue.getReturnDate()));

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
