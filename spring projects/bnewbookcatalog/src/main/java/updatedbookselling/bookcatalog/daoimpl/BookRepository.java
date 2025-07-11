package updatedbookselling.bookcatalog.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import updatedbookselling.bookcatalog.domain.Book;


@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Book getBookById(int id) {
        String sql = "SELECT bookId , title, cost, quantity , discount FROM Book WHERE bookId = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT bookId ,title, cost, quantity , discount FROM Book";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setTitle(rs.getString("title"));
            book.setCost(rs.getDouble("cost"));
            book.setQuantity(rs.getInt("quantity"));
            book.setDiscount(rs.getDouble("discount"));
            return book;
        });
    }

    public List<Book> searchBooksByName(String name) {
        String sql = "SELECT bookId ,title,cost, quantity, discount FROM Book WHERE title LIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), "%" + name + "%");
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Book WHERE bookId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
    
    String pageSql = "SELECT bookId ,title,cost, quantity, discount FROM Book LIMIT ? OFFSET ?";

    
    public List<Book> getBooksByPage(int pageNumber, int pageSize) {
        int offset = (pageNumber) * pageSize;
        return jdbcTemplate.query(pageSql,  new BookRowMapper() , pageSize , offset);
    }

    private static class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getInt("bookId"),
                    rs.getString("title"),
                    rs.getDouble("cost"),
                    rs.getInt("quantity"),
                    rs.getDouble("discount")
            );
        }
    }
    
    public List<Book> findAllPaged(int offset, int limit) {
        String sql = "SELECT bookId ,title,cost, quantity, discount FROM Book LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BookRowMapper(), limit, offset);
    }

    public List<Book> findAvailableBooks(int offset, int limit) {
        String sql = "SELECT bookId ,title,cost, quantity, discount FROM Book WHERE quantity > 0 LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BookRowMapper(), limit, offset);
    }

    public List<Book> findUnavailableBooks(int offset, int limit) {
        String sql = "SELECT bookId ,title,cost, quantity, discount FROM Book WHERE quantity = 0 LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BookRowMapper(), limit, offset);
    }

    public List<Book> findByTitleContaining(String title, int offset, int limit) {
        String sql = "SELECT bookId ,title,cost, quantity, discount FROM Book WHERE LOWER(title) LIKE ? LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BookRowMapper(), "%" + title.toLowerCase() + "%", limit, offset);
    }
}
