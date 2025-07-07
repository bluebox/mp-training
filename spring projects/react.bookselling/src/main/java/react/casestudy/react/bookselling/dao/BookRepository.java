package react.casestudy.react.bookselling.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import react.casestudy.react.bookselling.domain.Book;

@Repository
public class BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addBook(Book book) {
        String sql = "INSERT INTO Book (title, author, categroy, cost, quantity, published) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategroy(),
                book.getCost(), book.getQuantity(), book.getPublished());
    }

    public int updateBook(int id, Book book) {
        String sql = "UPDATE Book SET title=?, author=?, categroy=?, cost=?, quantity=?, published=? WHERE bookId=?";
        return jdbcTemplate.update(sql, book.getTitle(), book.getAuthor(), book.getCategroy(),
                book.getCost(), book.getQuantity(), book.getPublished(), id);
    }

    public Book getBookById(int id) {
        String sql = "SELECT bookId , title, author, categroy, cost, quantity, published FROM Book WHERE bookId = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), id);
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT bookId ,title, author, categroy, cost, quantity, published  FROM Book";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategroy(rs.getString("categroy"));
            book.setCost(rs.getBigDecimal("cost"));
            book.setQuantity(rs.getInt("quantity"));
            book.setPublished(rs.getDate("published"));
            return book;
        });
    }

    public List<Book> searchBooksByName(String name) {
        String sql = "SELECT bookId ,title, author, categroy, cost, quantity, published FROM Book WHERE title LIKE ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class), "%" + name + "%");
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM Book WHERE bookId = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
