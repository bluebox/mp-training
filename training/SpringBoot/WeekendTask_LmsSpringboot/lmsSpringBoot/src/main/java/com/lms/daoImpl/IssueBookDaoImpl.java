package com.lms.daoImpl;

import com.lms.dao.IssueBookDao;
import com.lms.model.IssueBook;
import com.lms.model.Book;
import com.lms.model.Member;
import com.lms.model.BookCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class IssueBookDaoImpl implements IssueBookDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean issueBook(IssueBook issue) {
        String sql = "INSERT INTO issue_books (member_id, book_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
        int rows = jdbcTemplate.update(sql,
                issue.getMemberId(),
                issue.getBookId(),
                Date.valueOf(issue.getIssueDate()),
                Date.valueOf(issue.getReturnDate())
        );
        if (rows > 0) {
            updateBookAvailability(issue.getBookId(), 'U');
            return true;
        }
        return false;
    }

    @Override
    public boolean returnBook(int issueId) {
        String sql = "UPDATE issue_books SET actual_return_date = CURDATE() WHERE issue_id = ?";
        int rows = jdbcTemplate.update(sql, issueId);
        return rows > 0;
    }

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) {
        String sql = "SELECT * FROM issue_books WHERE member_id = ? AND actual_return_date IS NULL";
        return jdbcTemplate.query(sql, new Object[]{memberId}, (ResultSet rs, int rowNum) -> new IssueBook(
                rs.getInt("issue_id"),
                rs.getString("book_id"),
                rs.getInt("member_id"),
                rs.getDate("issue_date").toLocalDate(),
                rs.getDate("return_date").toLocalDate(),
                rs.getDate("actual_return_date") != null ? rs.getDate("actual_return_date").toLocalDate() : null
        ));
    }

    @Override
    public List<Book> getAvailableBooksByCategory(BookCategory category) {
        String sql = "SELECT * FROM books WHERE category = ? AND availability = 'A' AND status = 'A'";
        return jdbcTemplate.query(sql, new Object[]{category.name()}, (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setBookId(rs.getString("book_id"));
            book.setBookTitle(rs.getString("title"));
            book.setBookAuthor(rs.getString("author"));
            book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
            book.setStatus(rs.getString("status"));
            book.setAvailability(rs.getString("availability"));
            return book;
        });
    }

    @Override
    public Member getMemberByMobile(String mobile) {
        String sql = "SELECT * FROM members WHERE mobile = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{mobile}, (rs, rowNum) -> new Member(
                rs.getInt("member_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("mobile"),
                rs.getString("gender"),
                rs.getString("address")
        ));
    }

    @Override
    public void updateBookAvailability(String bookId, char availability) {
        String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
        jdbcTemplate.update(sql, String.valueOf(availability), bookId);
    }
}
