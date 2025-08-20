package com.lms.springbootlms.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lms.springbootlms.dao.ReportDao;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.BookCategory;
import com.lms.springbootlms.model.Member;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReportDaoImpl implements ReportDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReportDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setBookId(rs.getString("book_id"));
        book.setBookTitle(rs.getString("title"));
        book.setBookAuthor(rs.getString("author"));
        book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
        book.setStatus(rs.getString("status").charAt(0));
        book.setAvailability(rs.getString("availability").charAt(0));
        return book;
    };

    private final RowMapper<Member> memberRowMapper = (rs, rowNum) -> new Member(
            rs.getInt("member_id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("mobile"),
            rs.getString("gender"),
            rs.getString("address")
    );

    @Override
    public List<Book> getOverdueBooks() throws DataAccessException {
        String sql = """
            SELECT b.book_id, b.title, b.author, b.category, b.status, b.availability
            FROM books b
            JOIN issue_books ib ON b.book_id = ib.book_id
            WHERE ib.actual_return_date IS NULL
              AND ib.return_date < CURRENT_DATE
            ORDER BY b.title
        """;

        return jdbcTemplate.query(sql, bookRowMapper)
                .stream()
                .sorted(Comparator.comparing(Book::getBookTitle))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getBookCountByCategory() throws DataAccessException {
        String sql = "SELECT book_id, title, author, category, status, availability FROM books";

        List<Book> allBooks = jdbcTemplate.query(sql, bookRowMapper);
        return allBooks.stream()
                .collect(Collectors.groupingBy(
                        b -> b.getBookCategory().toString(),
                        Collectors.counting()
                ));
    }

    @Override
    public List<Member> getMembersWithActiveIssues() throws DataAccessException {
        String sql = """
            SELECT DISTINCT m.member_id, m.name, m.email, m.mobile, m.gender, m.address
            FROM members m
            JOIN issue_books ib ON m.member_id = ib.member_id
            WHERE ib.actual_return_date IS NULL
        """;

        Set<Member> members = new HashSet<>(jdbcTemplate.query(sql, memberRowMapper));
        return members.stream()
                .sorted(Comparator.comparing(Member::getName))
                .collect(Collectors.toList());
    }
}
