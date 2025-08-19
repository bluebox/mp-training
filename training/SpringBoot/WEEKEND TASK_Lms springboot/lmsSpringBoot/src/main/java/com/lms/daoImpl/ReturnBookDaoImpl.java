package com.lms.daoImpl;


import com.lms.dao.ReturnBookDao;
import com.lms.model.ReturnBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReturnBookDaoImpl implements ReturnBookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReturnBookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   
	@Override
    public String fetchMemberName(String mobile) {
        String sql = "SELECT name FROM members WHERE mobile = ?";
        List<String> names = jdbcTemplate.query(sql, new Object[]{mobile},
                (rs, rowNum) -> rs.getString("name"));
        return names.isEmpty() ? null : names.get(0);
    }

    @Override
    public List<String> fetchIssuedBooks(String mobile) {
        String sql = """
            SELECT b.title
            FROM books b
            JOIN issue_books ib ON b.book_id = ib.book_id
            JOIN members m ON m.member_id = ib.member_id
            WHERE m.mobile = ? AND ib.actual_return_date IS NULL
        """;
        return jdbcTemplate.query(sql, new Object[]{mobile},
                (rs, rowNum) -> rs.getString("title"));
    }

    @Override
    public boolean updateBookReturnStatus(ReturnBook returnBook) {
        try {
            String bookIdSql = "SELECT book_id FROM books WHERE title = ?";
            String bookId = jdbcTemplate.queryForObject(bookIdSql, new Object[]{returnBook.getBookName()}, String.class);

            String memberIdSql = "SELECT member_id FROM members WHERE mobile = ?";
            Integer memberId = jdbcTemplate.queryForObject(memberIdSql, new Object[]{returnBook.getMobile()}, Integer.class);

            if (bookId == null || memberId == null) {
                return false;
            }

            String updateBookSql = "UPDATE books SET status = ?, availability = 'A' WHERE book_id = ?";
            jdbcTemplate.update(updateBookSql,
                    returnBook.getStatus().equalsIgnoreCase("Active") ? "A" : "I",
                    bookId);

            String updateIssueBookSql = """
                UPDATE issue_books
                SET actual_return_date = CURDATE()
                WHERE book_id = ? AND member_id = ? AND actual_return_date IS NULL
            """;
            jdbcTemplate.update(updateIssueBookSql, bookId, memberId);

            return true;

        } catch (Exception e) {
          //  e.printStackTrace();
            return false;
        }
    }
}
