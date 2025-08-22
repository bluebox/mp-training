package com.example.library.daoImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.library.constants.IssueRecordStatus;
import com.example.library.dao.IssueRecordDao;
import com.example.library.domain.Book;
import com.example.library.domain.IssueRecord;

@Repository
public class IssueRecordDaoImpl implements IssueRecordDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<IssueRecord> issueRowMapper = (rs, rowNum) -> {

		IssueRecord issue = new IssueRecord();

		issue.setIssueId(rs.getInt("issue_id"));
		issue.setBookId(rs.getInt("book_id"));
		issue.setMemberId(rs.getInt("member_id"));
		issue.setStatus(IssueRecordStatus.getEnumConstant(rs.getString("status")));
		issue.setIssueDate(rs.getDate("issue_date").toLocalDate());
		if (rs.getDate("return_date") != null) {
			issue.setReturnDate(rs.getDate("return_date").toLocalDate());
		}
		return issue;

	};
	@Override
	public int issueBook(IssueRecord issue) {
		String sql = "insert into issue_records(book_id,member_id,status,issue_date,return_date) values (?,?,?,?,?)";
		return jdbcTemplate.update(sql, issue.getBookId(), issue.getMemberId(), issue.getStatus().getStringValue(),
				issue.getIssueDate(), issue.getReturnDate());
	}
	@Override
	public int returnBook(int issueId, LocalDate returnDate) {
		logIssue(issueId);
		String sql = "update issue_records set status=?, return_date=? where issue_id=?";
		return jdbcTemplate.update(sql, IssueRecordStatus.RETURNED.getStringValue(), returnDate, issueId);
	}
	@Override
	public List<IssueRecord> getAllIssues() {
		String sql = "select * from issue_records";
		return jdbcTemplate.query(sql, issueRowMapper);
	}
	@Override
	public IssueRecord findActiveIssue(int memberId, int bookId) {
		String sql = "select * from issue_records where member_id=? and book_id=?";
		List<IssueRecord> list = jdbcTemplate.query(sql, issueRowMapper, memberId, bookId);
		return list.isEmpty() ? null : list.get(0);
	}
	
	@Override
	public List<Book> getIssuedBooksForMember(int memberId) {
		String sql = "select b.* from books b join issue_records i on b.book_id = i.book_id where i.member_id = ? and i.status = ?";
		return jdbcTemplate.query(sql, (rs, rowNum) -> {
			Book book = new Book();
			book.setBookId(rs.getInt("book_id"));
			book.setTitle(rs.getString("title"));
			book.setAuthor(rs.getString("author"));
			return book;
		}, memberId, IssueRecordStatus.ISSUED.getStringValue());
	}
	
	@Override
	public int logIssue(int issueId) {
		String sql = "insert into issue_records_log(issue_id, book_id, member_id, status, issue_date, return_date) "
				+ "select issue_id, book_id, member_id, status, issue_date, return_date from issue_records where issue_id=?";
		return jdbcTemplate.update(sql, issueId);
	}
}
