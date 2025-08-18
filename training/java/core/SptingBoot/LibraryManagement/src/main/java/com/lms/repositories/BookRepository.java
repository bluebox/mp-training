package com.lms.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.lms.model.Book;

@Repository
public class BookRepository {

	private JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public Book addBook(Book book) {

		String sql = "INSERT INTO books(title, author,category,status,availability) VALUES (?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getCategory());
			ps.setString(4, "A");
			ps.setString(5, "A");
			return ps;
		}, keyHolder);
		book.setBookId(keyHolder.getKey().intValue());

		return book;
	}
	
	private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
        Book book = new Book();
        book.setBookId(rs.getInt("bookId"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setCategory(rs.getString("category"));
        book.setStatus(rs.getString("status"));
        book.setAvailability(rs.getString("availability"));
        return book;
    };
    
	public Book updateBook(Book book) {
		String sql = "UPDATE books SET title=?, author=?, category=?, `status`=? WHERE BookId=?";
		String sqlLog = "INSERT INTO books_log(bookId,title,author,category,`status`,availability,operation_type) VALUES (?,?,?,?,?,?,?)";

		template.update(sqlLog, book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(),
				book.getStatus(), book.getAvailability(), "Update");
		template.update(sql, book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus(), book.getBookId());

		return book;
	}

	public Book updatAvailability(Book book) {
		String updateBookAvailability = "UPDATE books SET Availability=? WHERE BookId=?";
		String updateBook_log = "INSERT INTO books_log(bookId,title,author,category,status,availability,operation_type) VALUES ( ?, ?,?,?,?,?,?)";

		template.update(updateBook_log, book.getBookId(), book.getTitle(), book.getAuthor(), book.getCategory(),
				book.getStatus(), book.getAvailability(),"update Availability");
		template.update(updateBookAvailability, book.getAvailability(), book.getBookId());

		return book;
	}

	public List<Book> getAllBooks() {
		String sql = "SELECT * FROM books";
		List<Book> books = template.query(sql, (rs, row) -> {
			Book book = new Book();
			book.setBookId(rs.getInt(1));
			book.setTitle(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setCategory(rs.getString(4));
			book.setStatus(rs.getString(5));
			book.setAvailability(rs.getString(6));
			return book;
		});
		return books;
	}

	public Optional<Book> getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE bookId = ?";
        List<Book> books = template.query(sql, ps -> ps.setInt(1, bookId), bookRowMapper);
        return books.isEmpty() ? Optional.empty() : Optional.of(books.get(0));
    }

}

