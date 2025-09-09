package com.vardhan.main.daoimpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.vardhan.main.dao.ReportDao;
import com.vardhan.main.model.Book;
import com.vardhan.main.model.IssueBook;
import com.vardhan.main.model.Member;

@Repository
public class ReportDaoImpl implements ReportDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Book> REPORT_BOOK_ROW_MAPPER = new RowMapper<Book>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .bookId(rs.getString("book_id"))
                    .title(rs.getString("title"))
                    .author(rs.getString("author"))
                    .category(rs.getString("category"))
                    .status(rs.getString("status"))        
                    .availability(rs.getString("availability"))
                    .build();
        }
    };

    private static final RowMapper<Member> REPORT_MEMBER_ROW_MAPPER = new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Member.builder()
                    .memberId(rs.getInt("member_id"))
                    .name(rs.getString("name"))
                    .email(rs.getString("email"))
                    .mobile(rs.getString("mobile"))
                    .gender(rs.getString("gender"))
                    .address(rs.getString("address"))
                    .status(rs.getString("status")) 
                    .build();
        }
    };

    private static final RowMapper<IssueBook> OVERDUE_BOOK_ROW_MAPPER = new RowMapper<IssueBook>() {
        @Override
        public IssueBook mapRow(ResultSet rs, int rowNum) throws SQLException {
            Date actualReturnDate = rs.getDate("actual_return_date");
            return IssueBook.builder()
                    .issueId(rs.getInt("issue_id"))
                    .bookId(rs.getString("book_id"))
                    .memberId(rs.getInt("member_id"))
                    .issueDate(rs.getDate("issue_date").toLocalDate())
                    .returnDate(rs.getDate("return_date").toLocalDate())
                    .actualReturnDate(actualReturnDate != null ? actualReturnDate.toLocalDate() : null)
                    .bookTitle(rs.getString("title"))
                    .memberName(rs.getString("name"))
                    .build();
        }
    };

    @Override
    public List<IssueBook> getOverdueBooks() throws DataAccessException {
        String sql = """
            SELECT ib.issue_id, ib.book_id, ib.member_id, ib.issue_date, 
                   ib.return_date, ib.actual_return_date, b.title, m.name
            FROM issue_books ib
            JOIN books b ON ib.book_id = b.book_id
            JOIN members m ON ib.member_id = m.member_id
            WHERE ib.actual_return_date IS NULL 
            AND ib.return_date < CURRENT_DATE
            AND m.status != 'DELETED'
            ORDER BY ib.return_date, b.title
            """;

        return jdbcTemplate.query(sql, OVERDUE_BOOK_ROW_MAPPER);
    }

    @Override
    public Map<String, Long> getBookCountByCategory() throws DataAccessException {
        String sql = "SELECT category, COUNT(*) as count FROM books WHERE status != 'D' GROUP BY category ORDER BY category";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Map<String, Long> result = new HashMap<>();

        for (Map<String, Object> row : rows) {
            String category = (String) row.get("category");
            Long count = ((Number) row.get("count")).longValue();
            result.put(category, count);
        }

        return result;
    }

    @Override
    public List<Book> getMostIssuedBooks(int limit) throws DataAccessException {
        String sql = """
            SELECT b.book_id, b.title, b.author, b.category, b.status, b.availability,
                   COUNT(ib.book_id) as issue_count
            FROM books b
            LEFT JOIN issue_books ib ON b.book_id = ib.book_id
            WHERE b.status != 'D'
            GROUP BY b.book_id, b.title, b.author, b.category, b.status, b.availability
            ORDER BY issue_count DESC, b.title
            LIMIT ?
            """;

        return jdbcTemplate.query(sql, REPORT_BOOK_ROW_MAPPER, limit);
    }

    @Override
    public List<Book> getAvailableBooksByCategory(String category) throws DataAccessException {
        String sql = """
            SELECT book_id, title, author, category, status, availability 
            FROM books 
            WHERE category = ? AND availability = 'A' AND status = 'A'
            ORDER BY title
            """;

        return jdbcTemplate.query(sql, REPORT_BOOK_ROW_MAPPER, category);
    }

    @Override
    public List<Member> getMembersWithActiveIssues() throws DataAccessException {
        String sql = """
            SELECT DISTINCT m.member_id, m.name, m.email, m.mobile, m.gender, m.address, m.status
            FROM members m
            JOIN issue_books ib ON m.member_id = ib.member_id
            WHERE ib.actual_return_date IS NULL
            AND m.status != 'DELETED'
            ORDER BY m.name
            """;

        return jdbcTemplate.query(sql, REPORT_MEMBER_ROW_MAPPER);
    }

    @Override
    public List<Member> getMembersWithOverdueBooks() throws DataAccessException {
        String sql = """
            SELECT DISTINCT m.member_id, m.name, m.email, m.mobile, m.gender, m.address, m.status
            FROM members m
            JOIN issue_books ib ON m.member_id = ib.member_id
            WHERE ib.actual_return_date IS NULL 
            AND ib.return_date < CURRENT_DATE
            AND m.status != 'DELETED'
            ORDER BY m.name
            """;

        return jdbcTemplate.query(sql, REPORT_MEMBER_ROW_MAPPER);
    }

    @Override
    public Map<Integer, Long> getMemberIssueCount() throws DataAccessException {
        String sql = """
            SELECT m.member_id, COUNT(ib.issue_id) as issue_count
            FROM members m
            LEFT JOIN issue_books ib ON m.member_id = ib.member_id
            WHERE ib.actual_return_date IS NULL
            AND m.status != 'DELETED'
            GROUP BY m.member_id
            HAVING issue_count > 0
            ORDER BY issue_count DESC
            """;

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        Map<Integer, Long> result = new HashMap<>();

        for (Map<String, Object> row : rows) {
            Integer memberId = (Integer) row.get("member_id");
            Long count = ((Number) row.get("issue_count")).longValue();
            result.put(memberId, count);
        }

        return result;
    }

    @Override
    public List<IssueBook> getIssuesBetweenDates(LocalDate startDate, LocalDate endDate) throws DataAccessException {
        String sql = """
            SELECT ib.issue_id, ib.book_id, ib.member_id, ib.issue_date, 
                   ib.return_date, ib.actual_return_date, b.title, m.name
            FROM issue_books ib
            JOIN books b ON ib.book_id = b.book_id
            JOIN members m ON ib.member_id = m.member_id
            WHERE ib.issue_date BETWEEN ? AND ?
            AND m.status != 'DELETED'
            ORDER BY ib.issue_date DESC
            """;

        return jdbcTemplate.query(sql, OVERDUE_BOOK_ROW_MAPPER,
                Date.valueOf(startDate), Date.valueOf(endDate));
    }

    @Override
    public List<IssueBook> getReturnsBetweenDates(LocalDate startDate, LocalDate endDate) throws DataAccessException {
        String sql = """
            SELECT ib.issue_id, ib.book_id, ib.member_id, ib.issue_date, 
                   ib.return_date, ib.actual_return_date, b.title, m.name
            FROM issue_books ib
            JOIN books b ON ib.book_id = b.book_id
            JOIN members m ON ib.member_id = m.member_id
            WHERE ib.actual_return_date BETWEEN ? AND ?
            AND m.status != 'DELETED'
            ORDER BY ib.actual_return_date DESC
            """;

        return jdbcTemplate.query(sql, OVERDUE_BOOK_ROW_MAPPER,
                Date.valueOf(startDate), Date.valueOf(endDate));
    }

    @Override
    public long getTotalActiveIssues() throws DataAccessException {
        String sql = """
            SELECT COUNT(*) 
            FROM issue_books ib
            JOIN members m ON ib.member_id = m.member_id
            WHERE ib.actual_return_date IS NULL
            AND m.status != 'DELETED'
            """;
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count != null ? count : 0;
    }

    @Override
    public long getTotalOverdueIssues() throws DataAccessException {
        String sql = """
            SELECT COUNT(*) 
            FROM issue_books ib
            JOIN members m ON ib.member_id = m.member_id
            WHERE ib.actual_return_date IS NULL 
            AND ib.return_date < CURRENT_DATE
            AND m.status != 'DELETED'
            """;
        Long count = jdbcTemplate.queryForObject(sql, Long.class);
        return count != null ? count : 0;
    }

    @Override
    public Map<String, Object> getDashboardStats() throws DataAccessException {
        Map<String, Object> stats = new HashMap<>();

        Long totalBooks = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM books WHERE status != 'D'", Long.class);
        stats.put("totalBooks", totalBooks != null ? totalBooks : 0);

        Long availableBooks = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM books WHERE availability = 'A' AND status = 'A'", Long.class);
        stats.put("availableBooks", availableBooks != null ? availableBooks : 0);

        Long totalMembers = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM members WHERE status != 'DELETED'", Long.class);
        stats.put("totalMembers", totalMembers != null ? totalMembers : 0);

        stats.put("activeIssues", getTotalActiveIssues());

        stats.put("overdueIssues", getTotalOverdueIssues());

        return stats;
    }
}
