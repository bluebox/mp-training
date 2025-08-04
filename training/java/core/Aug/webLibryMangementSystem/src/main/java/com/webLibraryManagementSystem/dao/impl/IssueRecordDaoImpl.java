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
import com.webLibraryManagementSystem.utilities.BookAvailability;
import com.webLibraryManagementSystem.utilities.ConnectionPoolingServlet;
import com.webLibraryManagementSystem.utilities.IssueStatus;
import com.webLibraryManagementSystem.utilities.SQLQueries;
import com.webLibraryManagementSystemexceptions.InvalidException;

public class IssueRecordDaoImpl implements IssueRecordDao {

	BookDao bookDaoImpl = new BookDaoImpl();

	@Override
	public void issueBook(IssueRecord newIssue, Book book) throws InvalidException {
		Connection con = null;
		try {
			con = ConnectionPoolingServlet.getDataSource().getConnection();
			con.setAutoCommit(false);
			PreparedStatement stmt = con.prepareStatement(SQLQueries.ISSUE_INSERT);

			stmt.setInt(1, newIssue.getBookId());
			stmt.setInt(2, newIssue.getMemberId());
			stmt.setString(3, String.valueOf(newIssue.getStatus().toString().charAt(0)));
			stmt.setDate(4, Date.valueOf(newIssue.getIssueDate()));

			stmt.executeUpdate();

			bookDaoImpl.updateBookAvalability(book, BookAvailability.ISSUED);

			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println(e.getMessage());
				throw new InvalidException("Issue Book Roll back" + e.getMessage());
			} catch (SQLException e1) {

				throw new InvalidException("Error in Server");
			}

		}

	}

	@Override
	public void returnBook(Book book, int id, LocalDate date) throws InvalidException {
		Connection con = null;
		try {
			con = ConnectionPoolingServlet.getDataSource().getConnection();
			PreparedStatement stmt = con.prepareStatement(SQLQueries.ISSUE_SELECT_RETURN_DATE);

			stmt.setInt(1, book.getBookId());

			stmt.setInt(2, id);

			ResultSet res = stmt.executeQuery();
			IssueRecord issue = null;

			if (res.next()) {

				int issueId = res.getInt("issue_id");
				int bookId = res.getInt("book_id");
				int memberId = res.getInt("member_id");
				IssueStatus status = res.getString("status").equalsIgnoreCase("I") ? IssueStatus.ISSUED
						: IssueStatus.RETURNED;
				LocalDate issueDate = res.getDate("issue_date").toLocalDate();
				Date sqlDate = res.getDate("return_date");
				LocalDate returnDate = sqlDate == null ? null : sqlDate.toLocalDate();

				issue = new IssueRecord(issueId, bookId, memberId, status, issueDate, returnDate);

			} else {
				throw new InvalidException("Issue record Not Found ");
			}

			con.setAutoCommit(false);

			if (issue.getIssueDate().isAfter(date)) {
				throw new InvalidException("return date Must be after Issue Date");
			}

			System.out.println("before update");
			PreparedStatement stmt1 = con.prepareStatement(SQLQueries.ISSUE_UPDATE_RETURN_DATE);
			stmt1.setDate(1, Date.valueOf(date));
			stmt1.setInt(2, issue.getIssueId());

			stmt1.executeUpdate();

			System.out.println("herererer");

			issueLog(issue);
			System.out.println("after log");

			bookDaoImpl.updateBookAvalability(book, BookAvailability.AVAILABLE);
			System.out.println("after book avail");
			con.commit();
			con.setAutoCommit(true);

		} catch (SQLException e) {
			try {
				con.rollback();
				System.out.println(e.getMessage());
				throw new InvalidException("Issue Book Roll back" + e.getMessage());
			} catch (SQLException e1) {

				throw new InvalidException("Error in Server");
			}

		}

	}

	@Override
	public void issueLog(IssueRecord issue) throws InvalidException {
		PreparedStatement stmt;
		try {
			Connection con = ConnectionPoolingServlet.getDataSource().getConnection();
			stmt = con.prepareStatement(SQLQueries.ISSUE_LOG_INSERT);

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
