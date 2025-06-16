package com.library.test;

import com.library.dao.BookDAO;
import com.library.domain.Book;
import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class BookDAOTest {

    private static Connection connection;
    private static BookDAO bookDAO;

    @BeforeClass
    public static void setupDatabaseConnection() throws Exception {
    	connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library",
                "manoj",
                "Manoj@123"
            );

            bookDAO = new BookDAO();
            bookDAO.setTestMode(true);
    }



    @Before
    public void clearBooksTable() throws Exception {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM books_test");
        stmt.executeUpdate("ALTER TABLE books_test AUTO_INCREMENT = 1");
    }


    @Test
    public void testInsertBook_validData_returnsTrue() {
        Book book = new Book("Clean Code", "Robert Martin", "Software", "A", "A");
        boolean result = bookDAO.insertBook(book);
        assertTrue(result);

        List<Book> books = bookDAO.getAllBooks();
        assertEquals(1, books.size());
        assertEquals("Clean Code", books.get(0).getTitle());
    }
    

    @Test
    public void testInsertBook_invalidStatus_returnsFalse() {
        Book book = new Book("Invalid", "Bad Data", "Error", "AA", "I");
        boolean result = bookDAO.insertBook(book);
        assertFalse(result);
    }
    

    @Test
    public void testGetAllBooks_multipleEntries_returnsList() {
        bookDAO.insertBook(new Book("Java", "Author A", "Tech", "A", "A"));
        bookDAO.insertBook(new Book("Python", "Author B", "Tech", "A", "I"));

        List<Book> books = bookDAO.getAllBooks();
        assertEquals(2, books.size());
    }


    @AfterClass
    public static void tearDown() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}