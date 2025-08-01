package com.library_management.services;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;

import com.library_management.dao.implementation.BookDaoImplementation;
import com.library_management.domain.Book;
import com.library_management.domain.BookAvailability;
import com.library_management.domain.BookCategory;
import com.library_management.domain.BookStatus;
import com.library_management.exceptions.DatabaseException;
import com.library_management.exceptions.InvalidDetailsException;
import com.library_management.services.implementation.BookServiceImplementation;
import com.library_management.utilities.DBConnection;


public class BookServiceImplementationTest {
	private BookServiceImplementation service;
	private int generatedId;

    @Before
    public void setUp() throws DatabaseException {
        service = new BookServiceImplementation();
        BookDaoImplementation dao=new BookDaoImplementation();
        DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms_test");
        this.generatedId=dao.addBook(new Book("test", "test", BookCategory.FICTION));
    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateAddBook_WithEmptyTitle_ShouldThrowException() throws Exception {
        service.validateAddBook("", "Visaal", BookCategory.NON_FICTION);
    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateAddBook_WithNullCategory_ShouldThrowException() throws Exception {
        service.validateAddBook("Love Story", "Rohit", null);
    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateAddBook_WithTooLongTitle_ShouldThrowException() throws Exception {
    	StringBuilder sb=new StringBuilder("");
    	for(int i=1; i<=256; i++) {
    		sb.append("R");
    	}
        String overflowedTitle = sb.toString();
        service.validateAddBook(overflowedTitle, "Author", BookCategory.SCIENCE_FICTION);
    }

    @Test
    public void testValidateViewAllBooks_ShouldReturnList() throws Exception {
        assertNotNull(service.validateViewAllBooks());
    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateUpdateBookDetails_WithEmptyFields_ShouldThrowException() throws Exception {
        service.validateUpdateBookDetails(this.generatedId, "", "", BookCategory.FICTION, BookStatus.ACTIVE);
    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateUpdateBookDetails_WithUnchangedData_ShouldThrowException() throws Exception {
        service.validateUpdateBookDetails(this.generatedId, "test", "test", BookCategory.FICTION, BookStatus.ACTIVE);
    }

    @Test
    public void testValidateUpdateBookDetails_WithChanges_ShouldPass() throws Exception {
        service.validateUpdateBookDetails(this.generatedId, "Ross", "Varma", BookCategory.MYSTERY, BookStatus.INACTIVE);
    }
    
    @After
    public void tearDown() throws Exception {
    	BookDaoImplementation dao=new BookDaoImplementation();
    	dao.deleteBook(new Book(this.generatedId, "test", "test", BookCategory.FICTION, BookStatus.ACTIVE, BookAvailability.AVAILABLE));
    	DBConnection.closeStatement();
        DBConnection.closeConn();
    }
}
