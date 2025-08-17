package com.lms.springbootlms.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.springbootlms.dao.BookIssueDao;
import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.BookCategory;
import com.lms.springbootlms.model.IssueBook;
import com.lms.springbootlms.model.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class IssueBookDaoImpl implements BookIssueDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IssueBookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<IssueBook> issueBookMapper = (rs, rowNum) -> {
        LocalDate actualReturn = rs.getDate("actual_return_date") != null
                ? rs.getDate("actual_return_date").toLocalDate()
                : null;

        return new IssueBook(
                rs.getInt("issue_id"),
                rs.getString("book_id"),
                rs.getInt("member_id"),
                rs.getString("status").charAt(0), 
                rs.getDate("issue_date").toLocalDate(),
                rs.getDate("return_date").toLocalDate(),
                actualReturn
        );
    };

    
    @Override
    public boolean issueBook(IssueBook record) throws DaoException {
        try {
            String query = "INSERT INTO issue_books (member_id, book_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
            int rows = jdbcTemplate.update(query,
                    record.getMemberId(),
                    record.getBookId(),
                    record.getIssueDate(),
                    record.getReturnDate()
            );
            if (rows > 0) {
                updateBookAvailability(record.getBookId(), 'U');
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new DaoException("Failed to issue book", e);
        }
    }

    @Override
    public boolean returnBook(int issueId, LocalDate actualReturnDate) throws DaoException {
        try {
            String query = "UPDATE issue_books SET actual_return_date = ? WHERE issue_id = ?";
            return jdbcTemplate.update(query, actualReturnDate, issueId) > 0;
        } catch (Exception e) {
            throw new DaoException("Failed to return book", e);
        }
    }

    @Override
    public List<IssueBook> getAllIssueRecords() throws DaoException {
        try {
            String query = "SELECT * FROM issue_books";
            return jdbcTemplate.query(query, issueBookMapper);
        } catch (Exception e) {
            throw new DaoException("Failed to fetch issue records", e);
        }
    }

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) throws DaoException {
        try {
            String query = "SELECT * FROM issue_books WHERE member_id = ? AND actual_return_date IS NULL";
            return jdbcTemplate.query(query, issueBookMapper, memberId);
        } catch (Exception e) {
            throw new DaoException("Failed to fetch active issues for memberId=" + memberId, e);
        }
    }

    @Override
    public boolean isBookAlreadyIssued(String bookId) throws DaoException {
        try {
            String query = "SELECT COUNT(*) FROM issue_books WHERE book_id = ? AND actual_return_date IS NULL";
            Integer count = jdbcTemplate.queryForObject(query, Integer.class, bookId);
            return count != null && count > 0;
        } catch (Exception e) {
            throw new DaoException("Failed to check if book is already issued", e);
        }
    }

    @Override
    public List<Book> getAvailableBooksByCategory(String category) throws DaoException {
        try {
            String query = "SELECT book_id, title, author, category, status, availability " +
                    "FROM books WHERE category = ? AND availability = 'A'";
            return jdbcTemplate.query(query, (rs, rowNum) -> {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookTitle(rs.getString("title"));
                book.setBookAuthor(rs.getString("author"));
                book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
                book.setStatus(rs.getString("status").charAt(0));
                book.setAvailability(rs.getString("availability").charAt(0));
                return book;
            }, category);
        } catch (Exception e) {
            throw new DaoException("Failed to fetch available books by category", e);
        }
    }

    @Override
    public Member getMemberByMobile(String mobile) throws DaoException {
        try {
            String sql = "SELECT member_id, name, email, mobile, gender, address FROM members WHERE mobile = ?";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                    new Member(
                            rs.getInt("member_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("mobile"),
                            rs.getString("gender"),
                            rs.getString("address")
                    ), mobile);
        } catch (Exception e) {
            throw new DaoException("Failed to fetch member with mobile=" + mobile, e);
        }
    }

    @Override
    public void updateBookAvailability(String bookId, char availability) throws DaoException {
        try {
            String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
            jdbcTemplate.update(sql, String.valueOf(availability), bookId);
        } catch (Exception e) {
            throw new DaoException("Failed to update book availability", e);
        }
    }
}
