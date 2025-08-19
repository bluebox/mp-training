package com.lms.Repository;





import com.lms.Models.Book;
import com.lms.Models.IssueRecords;
import com.lms.Models.Member;
import com.lms.Dao.Interfaces.IssueRecordDao;
import com.lms.Exceptions.IssueReturnDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Repository
public class IssueBookRepo  implements IssueRecordDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean issueBook(IssueRecords record) {
        try {
            IssueRecords existing = getActiveIssueByBookId(record.getBookId());
            if (existing != null) {
                throw new IssueReturnDaoException("Book is already issued.");
            }

            KeyHolder keyHolder = new GeneratedKeyHolder();

            String insertIssue = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertIssue, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, record.getBookId());
                ps.setInt(2, record.getMemberId());
                ps.setDate(3, Date.valueOf(record.getIssueDate()));
                return ps;
            }, keyHolder);

            int issueId = keyHolder.getKey().intValue();

            String logIssue = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, NULL)";
            jdbcTemplate.update(logIssue, issueId, record.getBookId(), record.getMemberId(), "I", Date.valueOf(record.getIssueDate()));

            String updateAvailability = "UPDATE books SET Availability='I' WHERE BookId=?";
            jdbcTemplate.update(updateAvailability, record.getBookId());

            return true;

        } catch (Exception e) {
            throw new IssueReturnDaoException("Error while issuing book: " + e.getMessage(), e);
        }
    }

    public boolean returnBook(int issueId) {
        try {
            LocalDate now = LocalDate.now();

            String updateIssue = "UPDATE issue_records SET Status='R', ReturnDate=? WHERE IssueId=?";
            int rowsAffected = jdbcTemplate.update(updateIssue, Date.valueOf(now), issueId);

            if (rowsAffected != 1) {
                throw new IssueReturnDaoException("Failed to update issue record for return.");
            }

            String insertLog = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) "
                    + "SELECT IssueId, BookId, MemberId, 'R', IssueDate, ? FROM issue_records WHERE IssueId=?";
            jdbcTemplate.update(insertLog, Date.valueOf(now), issueId);

            String updateAvailability = "UPDATE books SET Availability='A' WHERE BookId=(SELECT BookId FROM issue_records WHERE IssueId=?)";
            jdbcTemplate.update(updateAvailability, issueId);

            return true;

        } catch (Exception e) {
            throw new IssueReturnDaoException("Error while returning book: " + e.getMessage(), e);
        }
    }

    public IssueRecords getActiveIssueByBookId(int bookId) {
        String query = "SELECT * FROM issue_records WHERE BookId=? AND Status='I'";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{bookId}, (rs, rowNum) -> new IssueRecords(
                    rs.getInt("IssueId"),
                    rs.getInt("BookId"),
                    rs.getInt("MemberId"),
                    rs.getString("Status"),
                    rs.getDate("IssueDate").toLocalDate(),
                    rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null
            ));
        } catch (Exception e) {
            return null;
        }
    }

    public List<IssueRecords> getAllIssues() {
        String query = "SELECT * FROM issue_records";
        return jdbcTemplate.query(query, (rs, rowNum) -> new IssueRecords(
                rs.getInt("IssueId"),
                rs.getInt("BookId"),
                rs.getInt("MemberId"),
                rs.getString("Status"),
                rs.getDate("IssueDate").toLocalDate(),
                rs.getDate("ReturnDate") != null ? rs.getDate("ReturnDate").toLocalDate() : null
        ));
    }

    public List<Integer> getAvailableBookIds() {
        String query = "SELECT bookId FROM books WHERE availability='A' and status='A'";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getInt("bookId"));
    }

//    public List<Integer> getValidMemberIds() {
//        String query = "SELECT memberId FROM member";
//        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getInt("memberId"));
//    }

	public List<Book> getAvailabeBooks() {
		
		String sql = "SELECT * FROM books WHERE availability = '"+"A"+"' and status='"+"A"+"'";
		List<Book> books = jdbcTemplate.query(sql, (rs, row) -> {
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
	public List<Member> getValidMemberIds() {
		String sql = "SELECT * FROM member";
		List<Member> members =jdbcTemplate.query(sql, (rs,row) -> {
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
}


