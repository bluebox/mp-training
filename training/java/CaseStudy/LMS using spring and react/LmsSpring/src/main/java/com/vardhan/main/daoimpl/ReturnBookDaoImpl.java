package com.vardhan.main.daoimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReturnBookDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<String> fetchMemberName(String mobile) throws DataAccessException {
        String sql = "SELECT name FROM members WHERE mobile = ?";
        
        try {
            String name = jdbcTemplate.queryForObject(sql, String.class, mobile);
            return Optional.ofNullable(name);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public List<String> fetchIssuedBooks(String mobile) throws DataAccessException {
        String sql = """
            SELECT b.title
            FROM books b
            JOIN issue_books ib ON b.book_id = ib.book_id
            JOIN members m ON m.member_id = ib.member_id
            WHERE m.mobile = ? AND ib.actual_return_date IS NULL
            ORDER BY b.title
            """;
        
        return jdbcTemplate.queryForList(sql, String.class, mobile);
    }

    @Transactional
    public boolean updateBookReturnStatus(String mobile, String bookTitle, String status) throws DataAccessException {
        try {

            String getBookIdSql = "SELECT book_id FROM books WHERE title = ?";
            String bookId = jdbcTemplate.queryForObject(getBookIdSql, String.class, bookTitle);
            
            if (bookId == null) {
                return false;
            }


            String getMemberIdSql = "SELECT member_id FROM members WHERE mobile = ?";
            Integer memberId = jdbcTemplate.queryForObject(getMemberIdSql, Integer.class, mobile);
            
            if (memberId == null) {
                return false;
            }

            
            String updateBookSql = "UPDATE books SET status = ?, availability = 'A' WHERE book_id = ?";
            char statusChar = "Active".equalsIgnoreCase(status) ? 'A' : 'I';
            int bookRowsAffected = jdbcTemplate.update(updateBookSql, String.valueOf(statusChar), bookId);

           
            String updateIssueSql = """
                UPDATE issue_books 
                SET actual_return_date = CURDATE() 
                WHERE book_id = ? AND member_id = ? AND actual_return_date IS NULL
                """;
            int issueRowsAffected = jdbcTemplate.update(updateIssueSql, bookId, memberId);

            return bookRowsAffected > 0 && issueRowsAffected > 0;

        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public Optional<Integer> getMemberIdByMobile(String mobile) throws DataAccessException {
        String sql = "SELECT member_id FROM members WHERE mobile = ?";
        
        try {
            Integer memberId = jdbcTemplate.queryForObject(sql, Integer.class, mobile);
            return Optional.ofNullable(memberId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

   
    public Optional<String> getBookIdByTitle(String title) throws DataAccessException {
        String sql = "SELECT book_id FROM books WHERE title = ?";
        
        try {
            String bookId = jdbcTemplate.queryForObject(sql, String.class, title);
            return Optional.ofNullable(bookId);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    
    public boolean isBookIssuedToMember(String mobile, String bookTitle) throws DataAccessException {
        String sql = """
            SELECT COUNT(*)
            FROM issue_books ib
            JOIN books b ON ib.book_id = b.book_id
            JOIN members m ON ib.member_id = m.member_id
            WHERE m.mobile = ? AND b.title = ? AND ib.actual_return_date IS NULL
            """;
        
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, mobile, bookTitle);
        return count != null && count > 0;
    }

   
    public List<String> getActiveIssuesByMobile(String mobile) throws DataAccessException {
        String sql = """
            SELECT CONCAT(b.title, ' (Due: ', DATE_FORMAT(ib.return_date, '%Y-%m-%d'), ')') as book_info
            FROM books b
            JOIN issue_books ib ON b.book_id = ib.book_id
            JOIN members m ON m.member_id = ib.member_id
            WHERE m.mobile = ? AND ib.actual_return_date IS NULL
            ORDER BY ib.return_date, b.title
            """;
        
        return jdbcTemplate.queryForList(sql, String.class, mobile);
    }
}
