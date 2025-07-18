package com.example.library.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.library.dao.IssueDAO;
import com.example.library.model.Book;
import com.example.library.model.IssueRecord;

@Repository
public class IssueDAOImpl implements IssueDAO{

	private final JdbcTemplate jdbcTemplate;
	private final BookDAOImpl bookDAO;

	@Autowired
	public IssueDAOImpl(JdbcTemplate jdbcTemplate, BookDAOImpl bookDAO) {
		this.jdbcTemplate = jdbcTemplate;
		this.bookDAO = bookDAO;
	}

	@Override
	public void issueBook(IssueRecord issue) throws Exception {
		String insert = "INSERT INTO issue_records (bookId, memberId, status, issueDate) VALUES (?, ?, ?, ?)";
		String updateBook = "UPDATE books SET availability='I' WHERE bookId=?";
		String insertBookLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
		Book existingBook = bookDAO.getBookById(issue.getBookId());
		jdbcTemplate.update(insertBookLog,
			existingBook.getBookId(),
			existingBook.getTitle(),
			existingBook.getAuthor(),
			existingBook.getCategory(),
			String.valueOf(existingBook.getStatus()),
			String.valueOf(existingBook.getAvailability())
		);
		jdbcTemplate.update(insert,
			issue.getBookId(),
			issue.getMemberId(),
			"I",
			java.sql.Date.valueOf(issue.getIssueDate())
		);
		jdbcTemplate.update(updateBook, issue.getBookId());
	}

	@Override
	public void returnBook(int issueId, int bookId, java.time.LocalDate returnDate) throws Exception {
		String updateIssue = "UPDATE issue_records SET status='R', returnDate=? WHERE issueId=?";
		String updateBook = "UPDATE books SET availability='A' WHERE bookId=?";
		String insertLog = "INSERT INTO issue_records_log (issueId, bookId, memberId, status, issueDate, returnDate) VALUES (?, ?, ?, ?, ?, ?)";
		String insertBookLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
		IssueRecord existingRecord = getRecordById(issueId);
		if (existingRecord == null) {
			throw new Exception("Issue record not found");
		}
		Book existingBook = bookDAO.getBookById(bookId);
		jdbcTemplate.update(insertBookLog,
			existingBook.getBookId(),
			existingBook.getTitle(),
			existingBook.getAuthor(),
			existingBook.getCategory(),
			String.valueOf(existingBook.getStatus()),
			String.valueOf(existingBook.getAvailability())
		);
		jdbcTemplate.update(insertLog,
			existingRecord.getIssueId(),
			existingRecord.getBookId(),
			existingRecord.getMemberId(),
			String.valueOf(existingRecord.getStatus()),
			java.sql.Date.valueOf(existingRecord.getIssueDate()),
			existingRecord.getReturnDate() != null ? java.sql.Date.valueOf(existingRecord.getReturnDate()) : null
		);
		jdbcTemplate.update(updateIssue, java.sql.Date.valueOf(returnDate), issueId);
		jdbcTemplate.update(updateBook, bookId);
	}

	@Override
	public List<IssueRecord> getAllIssuedRecords() throws Exception {
		String query = "SELECT issueId, bookId, memberId, status, issueDate, returnDate FROM issue_records ORDER BY issueDate DESC";
		return jdbcTemplate.query(query, (rs, rowNum) -> new IssueRecord(
			rs.getInt("issueId"),
			rs.getInt("bookId"),
			rs.getInt("memberId"),
			rs.getString("status").charAt(0),
			rs.getDate("issueDate").toLocalDate(),
			rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null
		));
	}

	@Override
	public IssueRecord getRecordById(int issueId) throws Exception {
		String query = "SELECT issueId, bookId, memberId, status, issueDate, returnDate FROM issue_records WHERE issueId = ?";
		List<IssueRecord> records = jdbcTemplate.query(query, (rs, rowNum) -> new IssueRecord(
			rs.getInt("issueId"),
			rs.getInt("bookId"),
			rs.getInt("memberId"),
			rs.getString("status").charAt(0),
			rs.getDate("issueDate").toLocalDate(),
			rs.getDate("returnDate") != null ? rs.getDate("returnDate").toLocalDate() : null
		), issueId);
		return records.isEmpty() ? null : records.get(0);
	}
}
