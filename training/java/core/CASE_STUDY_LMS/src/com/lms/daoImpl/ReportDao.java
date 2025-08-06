package com.lms.daoImpl;

import com.lms.dao.ReportDaoInterface;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.Member;
import com.lms.util.DBUtil;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReportDao implements ReportDaoInterface {

    private static ReportDao instance;

    private ReportDao() {}

    public static ReportDao getInstance() {
        if (instance == null) {
            instance = new ReportDao();
        }
        return instance;
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getString("book_id"));
        book.setBookTitle(rs.getString("title"));
        book.setBookAuthor(rs.getString("author"));
        book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
        book.setStatus(rs.getString("status").charAt(0));
        book.setAvailability(rs.getString("availability").charAt(0));
        return book;
    }

    private Member mapMember(ResultSet rs) throws SQLException {
        return new Member(
                rs.getInt("member_id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("mobile"),
                rs.getString("gender"),
                rs.getString("address")
        );
    }
    
    
    @Override
    public List<Book> getOverdueBooks() {
        String query = """
            SELECT b. book_id, b.title, b.author, b.category, b.status, b.availability
            FROM books b 
            JOIN issue_books ib ON b.book_id = ib.book_id
			WHERE ib.actual_return_date IS NULL
			AND ib.return_date < CURRENT_DATE
			ORDER BY b.title
        """;
       

        return executeBookQuery(query);
    }
    private List<Book> executeBookQuery(String query) {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                books.add(mapBook(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books.stream()
                .sorted(Comparator.comparing(Book::getBookTitle))
                .collect(Collectors.toList());
    }
    @Override
    public Map<String, Long> getBookCountByCategory() {
        List<Book> allBooks = new ArrayList<>();
        String query = "SELECT book_id, title, author, category, status, availability FROM books";	

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                allBooks.add(mapBook(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBooks.stream()
                .collect(Collectors.groupingBy(book -> book.getBookCategory().toString(), Collectors.counting()));
    }

    @Override
    public List<Member> getMembersWithActiveIssues() {
        Set<Member> members = new HashSet<>();
        String query = """
            SELECT DISTINCT m.member_id, m.name, m.email, m.mobile, m.gender, m.address FROM members m
            JOIN issue_books ib ON m.member_id = ib.member_id
            WHERE ib.actual_return_date IS NULL
        """;
        

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                members.add(mapMember(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return members.stream()
                .sorted(Comparator.comparing(Member::getName))
                .collect(Collectors.toList());
    }
}
