package repository;

import model.Book;
import model.BookAvailability;
import model.BookStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

  
    private final RowMapper<Book> bookRowMapper = new RowMapper<>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookId(rs.getInt("BookId"));
            book.setTitle(rs.getString("Title"));
            book.setAuthor(rs.getString("Author"));
            book.setCategory(rs.getString("Category"));

            String status = rs.getString("status");
            if (status != null) {
                book.setStatus(BookStatus.getStatus(status));  
            }

            String avail = rs.getString("Availablity"); 
            if (avail != null) {
                book.setAvailablity(BookAvailability.getAvailability(avail));  
            }

            return book;
        }
    };


    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql, bookRowMapper);
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE BookId = ?";
        List<Book> books = jdbcTemplate.query(sql, bookRowMapper, id);
        return books.isEmpty() ? null : books.get(0);
    }

    public int addBook(Book book) {
        String sql = "INSERT INTO books (Title, Author, Category, status, Availablity) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus() != null ? book.getStatus().getType() : null,         
                book.getAvailablity() != null ? book.getAvailablity().getType() : null 
        );
    }

    public int updateBook(Book book) {
        String sql = "UPDATE books SET Title = ?, Author = ?, Category = ?, status = ?, Availablity = ? WHERE BookId = ?";
        return jdbcTemplate.update(sql,
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus() != null ? book.getStatus().getType() : null,          
                book.getAvailablity() != null ? book.getAvailablity().getType() : null, 
                book.getBookId()
        );
    }

    public int deleteBook(int id) {
        String sql = "DELETE FROM books WHERE BookId = ?";
        return jdbcTemplate.update(sql, id);
    }


    public int updateAvailability(int bookId, BookAvailability availability) {
        String sql = "UPDATE books SET Availablity = ? WHERE BookId = ?";
        return jdbcTemplate.update(sql, availability != null ? availability.getType() : null, bookId);
    }

    public int updateStatus(int bookId, BookStatus status) {
        String sql = "UPDATE books SET status = ? WHERE BookId = ?";
        return jdbcTemplate.update(sql, status != null ? status.getType() : null, bookId); 
    }
}
