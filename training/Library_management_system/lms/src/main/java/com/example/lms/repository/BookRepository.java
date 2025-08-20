package com.example.lms.repository;

import com.example.lms.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final JdbcTemplate jdbc;

    public int save(Book book) {
        return jdbc.update(
            "INSERT INTO books (title, author, category, status, availability) VALUES (?,?,?,?,?)",
            book.getTitle(),
            book.getAuthor(),
            book.getCategory(),
            book.getStatus(),
            book.getAvailability()
        );
    }

    public int update(Book book) {
        return jdbc.update(
            "UPDATE books SET title=?, author=?, category=?, status=?, availability=? WHERE book_id=?",
            book.getTitle(),
            book.getAuthor(),
            book.getCategory(),
            book.getStatus(),
            book.getAvailability(),
            book.getBookId()
        );
    }

    public int deleteById(Long id) {
        return jdbc.update("DELETE FROM books WHERE book_id=?", id);
    }

    public Book findById(Long id) {
        List<Book> result = jdbc.query(
            "SELECT * FROM books WHERE book_id=?",
            (rs, rowNum) -> {
                Book b = new Book();
                b.setBookId(rs.getLong("book_id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setCategory(rs.getString("category"));
                b.setStatus(rs.getString("status"));         
                b.setAvailability(rs.getString("availability"));
                return b;
            },
            id
        );
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Book> findAll() {
        return jdbc.query(
            "SELECT * FROM books",
            (rs, rowNum) -> {
                Book b = new Book();
                b.setBookId(rs.getLong("book_id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setCategory(rs.getString("category"));
                b.setStatus(rs.getString("status"));          
                b.setAvailability(rs.getString("availability"));
                return b;
            }
        );
    }
}
