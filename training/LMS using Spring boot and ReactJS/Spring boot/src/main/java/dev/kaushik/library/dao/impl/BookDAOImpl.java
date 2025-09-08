package dev.kaushik.library.dao.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import dev.kaushik.library.dao.BookDAO;
import dev.kaushik.library.model.Book;
import dev.kaushik.library.model.enums.*;

@Repository
public class BookDAOImpl implements BookDAO {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public BookDAOImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	private final RowMapper<Book> bookRowMapper = (rs, rowNum) -> {
		Book book = new Book();
		book.setBookId(rs.getInt("bookId"));
		book.setTitle(rs.getString("title"));
		book.setAuthor(rs.getString("author"));
		book.setCategory(BookCategory.fromDisplayName(rs.getString("category")));
		book.setStatus(BookStatus.fromCode(rs.getString("status")));
		book.setAvailability(BookAvailability.fromCode(rs.getString("availability")));
		if (rs.getTimestamp("created_at") != null) {
			book.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
		}
		book.setCreatedBy(rs.getString("created_by"));
		if (rs.getTimestamp("updated_at") != null) {
			book.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
		}
		book.setUpdatedBy(rs.getString("updated_by"));
		return book;
	};

	@Override
	public int addBook(Book book) {
		String sql = "INSERT INTO books (title,author,category,status,availability,created_by) "
				+ "VALUES (:title,:author,:category,:status,:availability,:createdBy)";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("title", book.getTitle())
				.addValue("author", book.getAuthor()).addValue("category", book.getCategory().getDisplayName())
				.addValue("status", book.getStatus().getCode())
				.addValue("availability", book.getAvailability().getCode()).addValue("createdBy", "ADMIN");
		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(sql, params, keyHolder, new String[] { "bookId" });
		return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : -1;
	}

	@Override
	public boolean updateBook(Book book) {
		Book oldBook = findBooks(null).stream().filter(b -> book.getBookId() == book.getBookId()).findFirst()
				.orElse(null);

		String sql = "UPDATE books SET title=:title, author=:author, category=:category, "
				+ "status=:status, availability=:availability, updated_by=:updated_by, updated_at=CURRENT_TIMESTAMP "
				+ "WHERE bookId=:bookId";
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("title", book.getTitle())
				.addValue("author", book.getAuthor()).addValue("category", book.getCategory().getDisplayName())
				.addValue("status", book.getStatus().getCode())
				.addValue("availability", book.getAvailability().getCode()).addValue("updated_by", "ADMIN")
				.addValue("bookId", book.getBookId());
		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		if (rowsAffected > 0) {
			logBookChange(oldBook);
			return true;
		}
		return false;
	}

	@Override
	public int deleteBooksInBatch(Integer... bookIds) {
		List<Integer> bookIdList = Arrays.asList(bookIds);
		List<Book> booksToLog = findBooks(null);
		booksToLog = booksToLog.stream().filter(book -> bookIdList.contains(book.getBookId()))
				.collect(Collectors.toList());

		String sql = "DELETE FROM books WHERE bookId IN (:bookIds)";
		MapSqlParameterSource params = new MapSqlParameterSource("bookIds", bookIdList);
		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		if (rowsAffected > 0) {
			booksToLog.forEach(book -> logBookChange(book));
		}
		return rowsAffected;
	}

	@Override
	public List<Book> findBooks(Book criteria) {
		StringBuilder sqlBuilder = new StringBuilder("SELECT * FROM books WHERE 1=1");
		MapSqlParameterSource params = new MapSqlParameterSource();
		if (criteria != null) {
			if (criteria.getBookId() != null) {
				sqlBuilder.append(" AND bookId = :bookId");
				params.addValue("bookId", criteria.getBookId());
			}
			if (criteria.getTitle() != null && !criteria.getTitle().isBlank()) {
				sqlBuilder.append(" AND title LIKE :title");
				params.addValue("title", "%" + criteria.getTitle().trim() + "%");
			}
			if (criteria.getAuthor() != null && !criteria.getAuthor().isBlank()) {
				sqlBuilder.append(" AND author LIKE :author");
				params.addValue("author", "%" + criteria.getAuthor().trim() + "%");
			}
			if (criteria.getCategory() != null) {
				sqlBuilder.append(" AND category = :category");
				params.addValue("category", criteria.getCategory().getDisplayName());
			}
			if (criteria.getStatus() != null) {
				sqlBuilder.append(" AND status = :status");
				params.addValue("status", criteria.getStatus().getCode());
			}
			if (criteria.getAvailability() != null) {
				sqlBuilder.append(" AND availability = :availability");
				params.addValue("availability", criteria.getAvailability().getCode());
			}
		}

		return namedParameterJdbcTemplate.query(sqlBuilder.toString(), params, bookRowMapper);
	}



	@Override
	public int updateBookStatusBatch(List<Integer> bookIds) {
		List<Book> booksToLog = findBooks(null).stream().filter(book -> bookIds.contains(book.getBookId()))
				.collect(Collectors.toList());
		String sql = ""
		    + "UPDATE books "
		    + "SET status = CASE status "
		    + "WHEN :active THEN :inactive "
		    + "WHEN :inactive THEN :active "
		    + "END, "
		    + "updated_by = :updatedBy "
		    + "WHERE bookId IN (:bookIds)";

		MapSqlParameterSource params = new MapSqlParameterSource()
		    .addValue("bookIds", bookIds)
		    
		    .addValue("active", BookStatus.ACTIVE.getCode())
		    .addValue("inactive", BookStatus.INACTIVE.getCode())
		    .addValue("updatedBy", "ADMIN");

		namedParameterJdbcTemplate.update(sql, params);
		int rowsAffected = namedParameterJdbcTemplate.update(sql, params);
		if (rowsAffected > 0) {
			booksToLog.forEach(book -> logBookChange(book));
		}
		return rowsAffected;
	}

	private void logBookChange(Book oldBook) {
		String sql = "INSERT INTO books_log (BookId,Title,Author,Category,Status,Availability,"
				+ "original_created_at,original_created_by,original_updated_at,original_updated_by,LogDate) "
				+ "VALUES (:bookId,:title,:author,:category,:status,:availability, "
				+ ":createdAt,:createdBy,:updatedAt,:updatedBy,CURRENT_TIMESTAMP)";

		MapSqlParameterSource params = new MapSqlParameterSource().addValue("bookId", oldBook.getBookId())
				.addValue("title", oldBook.getTitle()).addValue("author", oldBook.getAuthor())
				.addValue("category", oldBook.getCategory().getDisplayName())
				.addValue("status", oldBook.getStatus().getCode())
				.addValue("availability", oldBook.getAvailability().getCode())
				.addValue("createdAt", oldBook.getCreatedAt()).addValue("createdBy", oldBook.getCreatedBy())
				.addValue("updatedAt", oldBook.getUpdatedAt()).addValue("updatedBy", oldBook.getUpdatedBy());

		namedParameterJdbcTemplate.update(sql, params);
	}

}
