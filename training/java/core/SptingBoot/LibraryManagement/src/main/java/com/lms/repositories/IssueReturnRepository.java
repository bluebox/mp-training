package com.lms.repositories;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lms.model.Book;
import com.lms.model.IssueRecords;
import com.lms.model.Member;

@Repository
public class IssueReturnRepository {

	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public IssueRecords issueBook(IssueRecords record) {
		String insertIssue = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";
		String logIssue = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, NULL)";
		String updateAvailability = "UPDATE books SET Availability='I' WHERE BookId=?";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(insertIssue, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, record.getBookId());
			ps.setInt(2, record.getMemberId());
			ps.setDate(3, Date.valueOf(record.getIssueDate()));
			return ps;
		}, keyHolder);
		record.setIssueId(keyHolder.getKey().intValue());

		template.update(logIssue, record.getIssueId(), record.getBookId(), record.getMemberId(), record.getStatus(),
				record.getIssueDate());
		template.update(updateAvailability, record.getBookId());
		return record;
	}

	public IssueRecords returnBook(IssueRecords record) {
		String updateIssue = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueId=?";
		String insertLog = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) "
				+ "SELECT IssueId, BookId, MemberId, 'R', IssueDate, ? FROM issue_records WHERE IssueId=?";
		String updateAvailability = "UPDATE books SET Availability='A' WHERE BookId=(SELECT BookId FROM issue_records WHERE IssueId=?)";
		LocalDate now = LocalDate.now();
		template.update(updateIssue, Date.valueOf(now), record.getIssueId());
		template.update(insertLog, Date.valueOf(now), record.getIssueId());
		template.update(updateAvailability, record.getIssueId());
		record.setReturnDate(now);
		return record;
	}

	private final RowMapper<IssueRecords> IssueRowMapper = (rs, rowNum) -> {
		IssueRecords record = new IssueRecords();
		record.setIssueId(rs.getInt("IssueId"));
		record.setBookId(rs.getInt("BookId"));
		record.setMemberId(rs.getInt("MemberId"));
		record.setStatus(rs.getString("Status"));
		record.setIssueDate(rs.getDate("IssueDate").toLocalDate());
		record.setReturnDate(rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null);
		return record;
	};

	public Optional<IssueRecords> getActiveIssueByBookId(int bookId) {
		String query = "SELECT * FROM issue_records WHERE BookId=? AND Status='I'";
		List<IssueRecords> records = template.query(query, ps -> ps.setInt(1, bookId), IssueRowMapper);
		return records.isEmpty() ? Optional.empty() : Optional.of(records.get(0));
	}

	public List<IssueRecords> getAllIssues() {

		String sql = "SELECT * FROM issue_records";
		List<IssueRecords> records = template.query(sql, (rs, row) -> {
			IssueRecords record = new IssueRecords();
			record.setIssueId(rs.getInt("IssueId"));
			record.setBookId(rs.getInt("BookId"));
			record.setMemberId(rs.getInt("MemberId"));
			record.setStatus(rs.getString("Status"));
			record.setIssueDate(rs.getDate("IssueDate").toLocalDate());
			record.setReturnDate(rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null);
			return record;
		});
		return records;
	}

	public List<Member> getValidMemberIds() {
		String sql = "SELECT * FROM member";
		List<Member> members = template.query(sql, (rs, row) -> {
			Member member = new Member();
			member.setMemberId(rs.getInt(1));
			member.setName(rs.getString(2));
			member.setEmail(rs.getString(3));
			member.setMobile(rs.getLong(4));
			member.setGender(rs.getString(5));
			member.setAddress(rs.getString(6));
			return member;
		});
		return members;
	}

	public List<Book> getAvailabeBooks() {
		
		String sql = "SELECT * FROM books WHERE availability = '"+"A"+"' and status='"+"A"+"'";
		List<Book> books = template.query(sql, (rs, row) -> {
			Book book = new Book();
			book.setBookId(rs.getInt(1));
			book.setTitle(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setCategory(rs.getString(4));
			book.setStatus(rs.getString(5));
			book.setAvailability(rs.getString(6));
			return book;
		});
		return books;
	}

	public List<Book> getIssuedBooks() {
		String sql = "SELECT * FROM books WHERE availability = '"+"I"+"'";
		List<Book> books = template.query(sql, (rs, row) -> {
			Book book = new Book();
			book.setBookId(rs.getInt(1));
			book.setTitle(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setCategory(rs.getString(4));
			book.setStatus(rs.getString(5));
			book.setAvailability(rs.getString(6));
			return book;
		});
		return books;
	}

	public IssueRecords returnBookByBookId(String bookId) {
		String query = "SELECT * FROM issue_records WHERE BookId=? ";
		List<IssueRecords> records = template.query(query, ps -> ps.setInt(1, Integer.parseInt(bookId)), IssueRowMapper);
		returnBook(records.get(0));
		return records.isEmpty() ? null: records.get(0);
	}
}
