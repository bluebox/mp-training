package com.lms.springbootlms.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lms.springbootlms.dao.ReturnBookDao;
import com.lms.springbootlms.exception.DaoException;

import java.util.List;

@Repository
public class ReturnBookDaoImpl implements ReturnBookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReturnBookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    public String fetchMemberName(String mobile) throws DaoException{
        String sql = "SELECT name FROM members WHERE mobile = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, mobile);
        } catch (DataAccessException e) {
            throw new DaoException("Failed to fetch member name for mobile: " + mobile, e);
        }
    }

    @Override
    public List<String> fetchIssuedBooks(String mobile) throws DaoException{
        String sql = """
            SELECT b.title
            FROM books b
            JOIN issue_books ib ON b.book_id = ib.book_id
            JOIN members m ON m.member_id = ib.member_id
            WHERE m.mobile = ? AND ib.actual_return_date IS NULL
        """;
        try {
            return jdbcTemplate.queryForList(sql, String.class, mobile);
        } catch (DataAccessException e) {
            throw new DaoException("Failed to fetch issued books for mobile: " + mobile, e);
        }
    }

    @Override
    @Transactional
    public boolean updateBookReturnStatus(String mobile, String bookName, String status) throws DaoException{
        try {
            String bookId = jdbcTemplate.queryForObject(
                    "SELECT book_id FROM books WHERE title = ?",
                    String.class,
                    bookName
            );
            if (bookId == null) return false;

            Integer memberId = jdbcTemplate.queryForObject(
                    "SELECT member_id FROM members WHERE mobile = ?",
                    Integer.class,
                    mobile
            );
            if (memberId == null) return false;

            jdbcTemplate.update(
                    "UPDATE books SET status = ?, availability = 'A' WHERE book_id = ?",
                    "Active".equalsIgnoreCase(status) ? "A" : "I",
                    bookId
            );

            jdbcTemplate.update(
                    "UPDATE issue_books SET actual_return_date = CURDATE() " +
                    "WHERE book_id = ? AND member_id = ? AND actual_return_date IS NULL",
                    bookId,
                    memberId
            );

            return true;
        } catch (DataAccessException e) {
            throw new DaoException("Failed to update book return status for mobile: " + mobile, e);
        }
    }
}
