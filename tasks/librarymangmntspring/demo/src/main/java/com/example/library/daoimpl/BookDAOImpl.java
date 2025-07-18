package com.example.library.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.library.dao.BookDAO;
import com.example.library.exception.DatabaseException;
import com.example.library.model.Book;

@Repository
public class BookDAOImpl implements BookDAO{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookDAOImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, rowNum) -> new Book(
			rs.getInt("bookId"),
			rs.getString("title"),
			rs.getString("author"),
			rs.getString("category"),
			rs.getString("status").charAt(0),
			rs.getString("availability").charAt(0)
	);

	@Override
	public Book getBookById(int bookId) throws DatabaseException {
		String query = "SELECT bookId, title, author, category, status, availability FROM books WHERE bookId = ?";
		try {
			return jdbcTemplate.queryForObject(query, BOOK_ROW_MAPPER, bookId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void addBook(Book book) throws DatabaseException {
		String insert = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(insert,
				book.getTitle(),
				book.getAuthor(),
				book.getCategory(),
				String.valueOf(book.getStatus()),
				String.valueOf(book.getAvailability())
			);
		} catch (Exception e) {
			throw new DatabaseException("Error adding book", e);
		}
	}

	@Override
	public List<Book> getAllBooks() throws Exception {
		String query = "SELECT bookId, title, author, category, status, availability FROM books";
		return jdbcTemplate.query(query, BOOK_ROW_MAPPER);
	}

	@Override
	public void updateBook(Book book) throws Exception {
		String update = "UPDATE books SET title=?, author=?, category=?, status=?, availability=? WHERE bookId=?";
		String insertLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
		Book existingBook = getBookById(book.getBookId());
		if (existingBook == null) throw new Exception("Book not found");
		jdbcTemplate.update(insertLog,
			existingBook.getBookId(),
			existingBook.getTitle(),
			existingBook.getAuthor(),
			existingBook.getCategory(),
			String.valueOf(existingBook.getStatus()),
			String.valueOf(existingBook.getAvailability())
		);
		jdbcTemplate.update(update,
			book.getTitle(),
			book.getAuthor(),
			book.getCategory(),
			String.valueOf(book.getStatus()),
			String.valueOf(book.getAvailability()),
			book.getBookId()
		);
	}

	@Override
	public void updateBookAvailability(int bookId, Character availability) throws Exception {
		String update = "UPDATE books SET availability=? WHERE bookId=?";
		String insertLog = "INSERT INTO books_log (bookId, title, author, category, status, availability) VALUES (?, ?, ?, ?, ?, ?)";
		Book existingBook = getBookById(bookId);
		if (existingBook == null) throw new Exception("Book not found");
		if (existingBook.getAvailability().equals(availability)) {
			throw new Exception("No change in availability");
		}
		jdbcTemplate.update(insertLog,
			existingBook.getBookId(),
			existingBook.getTitle(),
			existingBook.getAuthor(),
			existingBook.getCategory(),
			String.valueOf(existingBook.getStatus()),
			String.valueOf(existingBook.getAvailability())
		);
		int rowsAffected = jdbcTemplate.update(update, String.valueOf(availability), bookId);
		if (rowsAffected <= 0) {
			throw new Exception("Error in update query");
		}
	}
}
