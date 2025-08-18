
package repository;

import model.BookAvailability;
import model.Issuerecords;
import model.IssueStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Scope("prototype")
@Repository
public class IssuerecordsRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private BookRepo bookRepository; 


	private RowMapper<Issuerecords> issueRecordRowMapper = (rs, rowNum) -> new Issuerecords(rs.getInt("IssueId"),
			rs.getInt("BookId"), rs.getInt("MemberId"), IssueStatus.getIssueStatus(rs.getString("status")),
			rs.getDate("IssueDate").toLocalDate(), rs.getDate("ReturnDate").toLocalDate());

	// Create Book Issue
	public int createBookIssue(int bookId, int memberId, IssueStatus status, LocalDate issueDate,
			LocalDate returnDate) {
		if (!checkBookIssue(bookId, memberId)) {
			String sql = "INSERT INTO issue_records (BookId, MemberId, status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?)";
			// Update book availability
			bookRepository.updateAvailability(bookId,
					status == IssueStatus.RETURNED ? BookAvailability.AVAILABLE : BookAvailability.ISSUED);

			return jdbcTemplate.update(sql, bookId, memberId, status.getType(), java.sql.Date.valueOf(issueDate),
					java.sql.Date.valueOf(returnDate));
		} else {
			System.out.println("Issue already exists");
			return 0;
		}
	}

	// Return Book
	public int returnBook(int bookId, int memberId) {
		if (checkBookIssue(bookId, memberId)) {
			Issuerecords record = getIssueRecord(bookId, memberId);

			// Insert into issue_recordslog
			String insertLog = "INSERT INTO issue_recordslog (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(insertLog,
				
					record.getBookId(), record.getMemberId(), record.getStatus().getType(),
					java.sql.Date.valueOf(record.getIssueDate()), java.sql.Date.valueOf(record.getReturnDate()));

			
			String updateQuery = "UPDATE issue_records SET Status=? WHERE BookId=? AND MemberId=? AND Status=?";
			int updatedRows = jdbcTemplate.update(updateQuery, "R", bookId, memberId, "I");

			bookRepository.updateAvailability(bookId, BookAvailability.AVAILABLE);

			return updatedRows;
		} else {
			System.out.println("Issue not found");
			return 0;
		}
	}

	// Check if Book is already issued
	public boolean checkBookIssue(int bookId, int memberId) {
		String sql = "SELECT COUNT(*) FROM issue_records WHERE BookId=? AND MemberId=? AND Status=?";
		Integer count = jdbcTemplate.queryForObject(sql, Integer.class, bookId, memberId, "I");
		return count != null && count > 0;
	}

	// Get all issue records
	public List<Issuerecords> getAllIssueRecords() {
		String sql = "SELECT IssueId, BookId, MemberId, status, IssueDate, ReturnDate FROM issue_records";
		return jdbcTemplate.query(sql, issueRecordRowMapper);
	}

	// Get specific issue record
	public Issuerecords getIssueRecord(int bookId, int memberId) {
		String sql = "SELECT IssueId, BookId, MemberId, status, IssueDate, ReturnDate "
				+ "FROM issue_records WHERE BookId=? AND MemberId=? AND Status=?";
		List<Issuerecords> records = jdbcTemplate.query(sql, issueRecordRowMapper, bookId, memberId, "I");
		return records.isEmpty() ? null : records.get(0);
	}
}
