package com.library.library_management_system.dao.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.dao.BookDaoInterface;
import com.library.library_management_system.domain.Book;
import com.library.library_management_system.row_mapper.BookRowMapper;

@Repository
public class BookDaoImpl implements BookDaoInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Book addBook(Book book) {
		String sql = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";
		int rows = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategory(), book.getStatus(),
				book.getAvailability());
		if (rows == 0) {
		    return null;
		}
		return book;
	}

	@Override
	public Book updateBook(Book book) {
		String sql = "UPDATE books SET title=?, author=?, category=? WHERE book_id=? AND availability='A'";
		int rows = jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategory(), book.getBookId());
		if (rows == 0) {
		    return null;
		}
		return getBookById(book.getBookId());
	}

	@Override
	public int updateBookAvailability(int bookId, String availability) {
		String sql = "UPDATE books SET availability=? WHERE book_id=?";
		return jdbcTemplate.update(sql, availability, bookId);
	}

	@Override
	public int  deleteBook(int bookId) {
		String sql = "UPDATE books SET status='I' WHERE book_id=? AND availability='A'";
		return jdbcTemplate.update(sql, bookId);
	}

	@Override
	public List<Book> getAllBooks() {
		String sql = "SELECT * FROM books WHERE status='A'";
		return jdbcTemplate.query(sql, new BookRowMapper());
	}

	@Override
	public Book getBookById(int id) {
		try {
			String sql = "SELECT * FROM books WHERE book_id=? AND status='A'";
			return jdbcTemplate.queryForObject(sql, new BookRowMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<Book> getBooksByMember(int memberId) {
		String sql = "SELECT b.* FROM books b JOIN issue_records i ON b.book_id = i.book_id WHERE i.member_id=? AND i.status='I' AND b.status='A'";
		return jdbcTemplate.query(sql, new BookRowMapper(), memberId);
	}

}
