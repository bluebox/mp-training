package com.LibraryManagement;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.LibraryManagement.dao.impl.BookDaoImpl;
import com.LibraryManagement.utilites.pojos.Book;

public class BookDaoImplTest {

    private static BookDaoImpl bookDao;
    private static final String TEST_TITLE = "Test AddBook";
    private static final String TEST_AUTHOR = "Test Author";
    private static final String TEST_CATEGORY = "Test Category";

    @BeforeClass
    public static void setup() {
        bookDao = new BookDaoImpl();
    }

    @Before
    public void cleanUpBefore() throws Exception {
        try (Connection con = com.LibraryManagement.utilites.DBConnection.connectDB()) {
            PreparedStatement pst = con.prepareStatement("DELETE FROM books WHERE Title = ?");
            pst.setString(1, TEST_TITLE);
            pst.executeUpdate();
        }
    }

    @Test
    public void testAddBook_Success() throws Exception {
        Book book = new Book(1, TEST_TITLE, TEST_AUTHOR, TEST_CATEGORY, 'A', 'A');
        boolean result = bookDao.addBook(book);
        assertTrue("Book should be added successfully", result);
    }
   

    private int getBookIdByTitle(String title) throws Exception {
        try (Connection con = com.LibraryManagement.utilites.DBConnection.connectDB()) {
            PreparedStatement pst = con.prepareStatement("SELECT book_id FROM books WHERE Title = ?");
            pst.setString(1, title);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getInt("book_id");
            } else {
                throw new Exception("Book not found with title: " + title);
            }
        }
    }
}
