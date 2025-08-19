package com.lms.daoImpl;

import com.lms.dao.ReportsDao;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.Member;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReportsDaoImpl implements ReportsDao {

    private final JdbcTemplate jdbcTemplate;

    public ReportsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book b = new Book();
            b.setBookId(rs.getString("book_id"));
            b.setBookTitle(rs.getString("title"));
            b.setBookAuthor(rs.getString("author"));
            b.setBookCategory(BookCategory.valueOf(rs.getString("category")));
            b.setStatus(rs.getString("status"));
            b.setAvailability(rs.getString("availability"));
            return b;
        }
    }

    private static class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Member(
                    rs.getInt("member_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("mobile"),
                    rs.getString("gender"),
                    rs.getString("address")
            );
        }
    }

    @Override
    public List<Book> findOverdueBooks() {
        final String sql = """
            SELECT b.book_id, b.title, b.author, b.category, b.status, b.availability
            FROM books b 
            JOIN issue_books ib ON b.book_id = ib.book_id
            WHERE ib.actual_return_date IS NULL
              AND ib.return_date < CURRENT_DATE
            ORDER BY b.title
        """;

        try {
            List<Book> books = jdbcTemplate.query(sql, new BookRowMapper());
            return books.stream()
                    .sorted(Comparator.comparing(Book::getBookTitle, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public Map<String, Long> findBookCountByCategory() {
        final String sql = "SELECT book_id, title, author, category, status, availability FROM books";
        try {
            List<Book> all = jdbcTemplate.query(sql, new BookRowMapper());
            return all.stream()
                    .collect(Collectors.groupingBy(b -> b.getBookCategory().toString(), Collectors.counting()));
        } catch (DataAccessException ex) {
            throw ex;
        }
    }

    @Override
    public List<Member> findMembersWithActiveIssues() {
        final String sql = """
            SELECT DISTINCT m.member_id, m.name, m.email, m.mobile, m.gender, m.address
            FROM members m
            JOIN issue_books ib ON m.member_id = ib.member_id
            WHERE ib.actual_return_date IS NULL
        """;

        try {
            List<Member> members = jdbcTemplate.query(sql, new MemberRowMapper());
            return members.stream()
                    .sorted(Comparator.comparing(Member::getName, String.CASE_INSENSITIVE_ORDER))
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            throw ex;
        }
    }
}
