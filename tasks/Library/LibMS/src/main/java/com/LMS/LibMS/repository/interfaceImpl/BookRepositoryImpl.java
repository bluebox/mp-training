
package com.LMS.LibMS.repository.interfaceImpl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.repository.interfaces.BookRepository;
import com.LMS.LibMS.rowmapper.BookRowMapper;

@Repository
public class BookRepositoryImpl implements BookRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public BookRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Book> getAllBooks() {

		String sqlString = "SELECT BookId, Title, Author, Category, Status, Availablity, created_at, created_by, updated_at, updated_by FROM books";

		return namedParameterJdbcTemplate.query(sqlString, new BookRowMapper());

	}

	@Override
	public void addBook(Book book) {

		String sql = "INSERT INTO books (Title, Author, Category, Status, Availablity, created_at, created_by) "

				+ "VALUES (:title, :author, :category, :status, :availablity, :createdAt, :createdBy)";

		MapSqlParameterSource params = bookparams(book, "add");
				
		namedParameterJdbcTemplate.update(sql, params);
	}

	@Override
	public int updateBook(Book book) {

		String sql = "UPDATE books SET Title = :title, Author = :author, Category = :category, "

				+ "Status = :status, Availablity = :availablity, updated_at = :updatedAt, updated_by = :updatedBy "

				+ "WHERE bookId = :bookId";

		MapSqlParameterSource params = bookparams(book, "update");

		return namedParameterJdbcTemplate.update(sql, params);

	}

	@Override
	public List<Book> findBookById(List<Integer> bookIds) {

		String sql = "SELECT BookId, Title, Author, Category, Status, Availablity, created_at, created_by, updated_at, updated_by FROM books WHERE BookId IN( :bookId)";

		List<Book> books = namedParameterJdbcTemplate.query(sql, Map.of("bookId", bookIds), new BookRowMapper());

		return books.isEmpty() ? null : books;
	}

	@Override
	public int deleteBooksById(List<Integer> bookIds) {

		String sql = "DELETE FROM books WHERE bookId IN (:ids)";

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("ids", bookIds);

		return  namedParameterJdbcTemplate.update(sql, params);


	}

	@Override
	public int updateAvailabilitiesById(List<Integer> bookIds, String updatedBy) {
		
			String sqlUpdate = "UPDATE books " +
	               		"SET Availablity = CASE WHEN Availablity = 'A' THEN 'I' ELSE 'A' END, " +
	                    "updated_at = :updatedAt, updated_by = :updatedBy " +
	                     "WHERE bookId IN (:ids)";

			MapSqlParameterSource updateParams = new MapSqlParameterSource();
			updateParams.addValue("ids", bookIds);
			updateParams.addValue("updatedAt", new Timestamp(System.currentTimeMillis()));
			updateParams.addValue("updatedBy", updatedBy);


			return namedParameterJdbcTemplate.update(sqlUpdate, updateParams);

	}

	@Override
	public boolean logBook(List<Book> books) {
		
        String sql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availablity, " +
                     "created_at, created_by, updated_at, updated_by, LogDate) " +
                     "VALUES (:bookId, :title, :author, :category, :status, :availablity, " +
                     ":createdAt, :createdBy, :updatedAt, :updatedBy, :logDate)";

        int totalInserted = 0;
        for (Book b : books) {
            MapSqlParameterSource params = bookparams(b, "logs");

            params.addValue("logDate", new Timestamp(System.currentTimeMillis()));

            totalInserted += namedParameterJdbcTemplate.update(sql, params);
        }
        return totalInserted == books.size();
	}

	@Override
	public int makeBookInactiveById(Integer bookId) {

//		String sql = "UPDATE books b JOIN issue_records ir ON b.BookId = ir.BookId "
//				+ " SET b.Status = CASE WHEN b.Status = 'A' THEN 'I' ELSE 'A' END "
//				+ "WHERE ir.Status = 'R' AND b.BookId = :bookId";
			
			String sql = """
					UPDATE books b
					SET b.Status = CASE
					    WHEN b.Status = 'A' THEN 'I'
					    ELSE 'A'
					END
					WHERE b.BookId = :bookId;

					""";
		
		

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("bookId", bookId);

		return namedParameterJdbcTemplate.update(sql, params);
		
	}
	
	
	private MapSqlParameterSource bookparams(Book book, String flag) {
		
        MapSqlParameterSource params = new MapSqlParameterSource();
   
        params.addValue("title", book.getTitle());
		params.addValue("author", book.getAuthor());
		params.addValue("category", book.getCategory().getCode());
		params.addValue("status", book.getStatus().getCode());
		params.addValue("availablity", book.getAvailability().getCode());
		
		if(flag.equals("add") || flag.equals("logs")) {
			params.addValue("createdAt", book.getCreatedAt());
			params.addValue("createdBy", book.getCreatedBy());			
		}
		
		if(flag.equals("update") || flag.equals("logs")) {
			params.addValue("bookId", book.getBookId());
			params.addValue("updatedAt", book.getUpdatedAt());
			params.addValue("updatedBy", book.getUpdatedBy());
		}
		
        return params;

	}

}
