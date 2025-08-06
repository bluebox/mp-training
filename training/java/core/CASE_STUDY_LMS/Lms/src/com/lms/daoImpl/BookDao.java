package com.lms.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.util.DBUtil;

public class BookDao {
    private static BookDao instance = new BookDao();

    private BookDao() {}

    public static BookDao getInstance() {
        return instance;
    }
    
    public static boolean isTitleExists(String title) throws SQLException {
        String sql = "SELECT COUNT(book_id) FROM books WHERE title = ?";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        
        return false;
    }


    public static Book getBookByTitle(String title) throws SQLException{
        String query = "SELECT book_id, title, author, category, status, availability FROM books WHERE title = ?";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                    rs.getString("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    BookCategory.valueOf(rs.getString("category")),
                    rs.getString("status").charAt(0),
                    rs.getString("availability").charAt(0)
                );
            }
        
        return null;
    }



    public static ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT book_id, title, author, category, status, availability FROM books";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        BookCategory.valueOf(rs.getString("category")),
                        rs.getString("status").charAt(0),
                        rs.getString("availability").charAt(0)));
            }
       
        return books;
    }

    public static  Book getBookById(String bookId) throws SQLException {
        String query = "SELECT book_id, title, author, category, status, availability FROM books WHERE book_id = ?";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getString("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        BookCategory.valueOf(rs.getString("category")),
                        rs.getString("status").charAt(0),
                        rs.getString("availability").charAt(0));
            }
       
        return null;
    }


   
    public static List<Book> getAvailableBooksByCategory(BookCategory category) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query;

        if (category == null) {
            query = "SELECT book_id, title, author, category, status, availability FROM books WHERE availability = 'A' AND status = 'A'";
        } else {
            query = "SELECT book_id, title, author, category, status, availability FROM books WHERE category = ? AND availability = 'A' AND status = 'A'";
        }

        Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(query);

            if (category != null) {
                ps.setString(1, category.name());
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookTitle(rs.getString("title"));
                book.setBookAuthor(rs.getString("author"));
                book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
                book.setAvailability(rs.getString("availability").charAt(0));
                book.setStatus(rs.getString("status").charAt(0));
                books.add(book);
            }


        return books;
    }

    public static void updateBookAvailability(String bookId, char availability) throws SQLException {
        String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(availability));
            stmt.setString(2, bookId);
            stmt.executeUpdate();
        
    }

	public static Book addBook(Book newBook) throws SQLException  {
		String query = "INSERT INTO books (title, author, category, status, availability) VALUES (?, ?, ?, ?, ?)";

        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,newBook.getBookTitle());
            stmt.setString(2, newBook.getBookAuthor());
            stmt.setString(3, newBook.getBookCategory().name());
            stmt.setString(4, String.valueOf(newBook.getStatus()));
            stmt.setString(5, String.valueOf(newBook.getAvailability()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement getStmt = conn.prepareStatement("SELECT book_id FROM books WHERE id = ?");
                    getStmt.setInt(1, id);
                    ResultSet bookRs = getStmt.executeQuery();
                    if (bookRs.next()) {
                        String bookId = bookRs.getString("book_id");
                        return new Book(bookId, newBook.getBookTitle(), newBook.getBookAuthor(), 
										newBook.getBookCategory(), newBook.getStatus(), newBook.getAvailability());
                    }
                }
            }
        
        return null;
			}

	public static Boolean updateBook(Book updateBook) throws SQLException {
	    String query = "UPDATE books SET title = ?, author = ?, category = ?, status = ?, availability = ? WHERE book_id = ?";
	    Connection conn = DBUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);

	        stmt.setString(1, updateBook.getBookTitle());
	        stmt.setString(2, updateBook.getBookAuthor());
	        stmt.setString(3, updateBook.getBookCategory().name());
	        stmt.setString(4, String.valueOf(updateBook.getStatus()));
	        stmt.setString(5, String.valueOf(updateBook.getAvailability()));
	        stmt.setString(6, updateBook.getBookId());

	        return stmt.executeUpdate() > 0;

	}

	public static void deleteBookById(String bookId) {
		String query = "DELETE FROM books WHERE book_id = ?";
		try (Connection conn = DBUtil.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, bookId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}