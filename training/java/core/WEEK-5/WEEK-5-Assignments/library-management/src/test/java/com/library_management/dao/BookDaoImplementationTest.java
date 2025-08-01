package com.library_management.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import com.library_management.dao.implementation.BookDaoImplementation;
import com.library_management.domain.Book;
import com.library_management.domain.BookAvailability;
import com.library_management.domain.BookCategory;
import com.library_management.domain.BookStatus;
import com.library_management.exceptions.DatabaseException;
import com.library_management.utilities.DBConnection;

public class BookDaoImplementationTest {
    private BookDaoImplementation dao;
    private int generatedId;

    @Before
    public void setUp() throws Exception {
    	DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms_test");
        dao=new BookDaoImplementation();
        this.generatedId=dao.addBook(new Book("test", "test", BookCategory.FICTION));
    }

    @Test
    public void testAddBook_ShouldPass() throws DatabaseException {
    	int id=dao.addBook(new Book("Brain Rot", "Rohit", BookCategory.SCIENCE_FICTION));
    	assertTrue(id!=0);
    }
    
    @Test
    public void testUpdateBookDetails_ShouldPass() throws DatabaseException {
    	Book oldBook=new Book(this.generatedId, "test", "test", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
    	Book newBook=new Book(this.generatedId, "What is Health", "Varma", BookCategory.NON_FICTION, BookStatus.INACTIVE);
    	dao.updateBookDetails(oldBook, newBook);
    }
    
    @Test
    public void testUpdateBookAvailability_ShouldPass() throws DatabaseException{
    	System.out.println(this.generatedId);
    	Book oldBook=new Book(generatedId, "test", "test", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
    	String newAvailability="I";
    	dao.updateBookAvailability(oldBook, newAvailability);
    }
    
    @Test
    public void testSelectAllBooks_ShouldPass_ShouldReturnList() throws DatabaseException{
    	Object result=dao.selectAllBooks();
    	assertNotNull(result);
    	assertTrue(result instanceof List);
    }
    
//    success Case
    @Test
    public void testSelectBookById_ShouldPass_ShouldReturnBook() throws DatabaseException{
    	Object result=dao.selectBookById(this.generatedId);
    	assertNotNull(result);
    	assertTrue(result instanceof Book);
    }
    
//    Fail cAse
    @Test
    public void testSelectBookById_ShouldPass_ShouldReturnNull() throws DatabaseException{
    	Object result=dao.selectBookById(9999);
    	assertNull(result);
    }
    
    @Test
    public void testDeleteBook_ShoulPass() throws DatabaseException{
    	Book oldBook=new Book(this.generatedId, "test", "test", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE);
    	dao.deleteBook(oldBook);
    }

    @After
    public void tearDown() throws Exception {
    	dao.deleteBook(new Book(this.generatedId, "test", "test", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE));
    	DBConnection.closeStatement();
        DBConnection.closeConn();
    }

}
