package com.library.library_management_system.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.library.library_management_system.domain.Book;
import com.library.library_management_system.exception.BookNotFoundException;
import com.library.library_management_system.mapper.BookRowMapper;
import com.library.library_management_system.utils.BookAvailability;
import com.library.library_management_system.utils.BookStatus;
import com.library.library_management_system.utils.SQLQueries;

@Repository
public class BookRepository {
	private final JdbcTemplate jdbcTemplate;

	public BookRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addBook(Book book) {

		return jdbcTemplate.update(SQLQueries.BOOK_INSERT, book.getTitle(), book.getAuthor(),
				book.getCategory().getCategory(), BookStatus.ACTIVE.getDbName(),
				BookAvailability.AVAILABLE.getDbName());

	}

	public boolean existsByTitleAndAuthor(String title, String author) {

		Integer count = jdbcTemplate.queryForObject(SQLQueries.BOOK_SELECT_BY_TITLE_AUTHOR, Integer.class, title,
				author);
		return count != null && count > 0;

	}

	public int updateBook(Book book) {

		return jdbcTemplate.update(SQLQueries.BOOK_UPDATE, book.getTitle(), book.getAuthor(),
				book.getCategory().getCategory(), book.getStatus().getDbName(), book.getAvailability().getDbName(),
				book.getId());

	}

	public boolean existsByTitleAndAuthorExceptId(String title, String author, int excludeId) {

		Integer count = jdbcTemplate.queryForObject(SQLQueries.BOOK_EXISTS_BY_TITLE_AUTHOR_EXCEPT_ID, Integer.class,
				title, author, excludeId);
		return count != null && count > 0;
	}

	public Book getBookById(int id) {

		List<Book> books = jdbcTemplate.query(SQLQueries.BOOK_SELECT_BY_ID, new BookRowMapper(), id);
		if (books.isEmpty()) {
			throw new BookNotFoundException("Book with id " + id + " not found");
		}
		return books.get(0);

	}

	public int updateBookAvailability(Book book, BookAvailability availability) {

		return jdbcTemplate.update(SQLQueries.BOOK_UPDATE_AVAILABILITY, availability.getDbName(), book.getId());

	}

	public List<Book> findAll() {

		return jdbcTemplate.query(SQLQueries.BOOK_SELECT_ALL, new BookRowMapper());

	}

	public int deleteBook(Book book) {

		return jdbcTemplate.update(SQLQueries.BOOK_DELETE, book.getId());

	}

	public int bookLog(Book book) {

		return jdbcTemplate.update(SQLQueries.BOOKS_LOG_INSERT, book.getId(), book.getTitle(), book.getAuthor(),
				book.getCategory().getCategory(), book.getStatus().getDbName(), book.getAvailability().getDbName());

	}
}
