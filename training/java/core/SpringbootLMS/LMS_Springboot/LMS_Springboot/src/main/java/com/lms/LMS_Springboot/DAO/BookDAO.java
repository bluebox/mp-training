package com.lms.LMS_Springboot.DAO;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> getAllBooks() {
        return jdbcTemplate.query("SELECT * FROM books",
                (rs, rowNum) -> new Book(
                        rs.getInt("bookId"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        Status.getstatus(rs.getString("status")),
                        Availability.getstatus(rs.getString("availability"))
                ));
    }

    public Book getBookById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM books WHERE bookId=?",
                new Object[]{id},
                (rs, rowNum) -> new Book(
                        rs.getInt("bookId"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        Status.getstatus(rs.getString("status")),
                        Availability.getstatus(rs.getString("availability"))
                ));
    }

    public int addBook(Book book) {
        return jdbcTemplate.update("INSERT INTO books (title, author, category, status, availability) VALUES (?,?,?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus().getType(),
                book.getAvailability().getType());
    }

    public int updateBook(int id, Book book) {
        return jdbcTemplate.update("UPDATE books SET title=?, author=?, category=?, status=?, availability=? WHERE bookId=?",
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus().getType(),
                book.getAvailability().getType(),
                id);
    }
}
