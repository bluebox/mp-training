package Library.src.test.java.com.LibraryManagement.service;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Library.src.main.java.com.LibraryManagement.dao.BookDAO;
import Library.src.main.java.com.LibraryManagement.dao.IssueRecordDAO;
import Library.src.main.java.com.LibraryManagement.model.Book;
import Library.src.main.java.com.LibraryManagement.model.IssueRecord;
import Library.src.main.java.com.LibraryManagement.service.IssueRecordServiceImpl;

public class IssueRecordServiceImplTest {

    private IssueRecordDAO issueRecordDAO;
    private BookDAO bookDAO;
    private IssueRecordServiceImpl issueService;

    @Before
    public void setUp() {
        issueRecordDAO = mock(IssueRecordDAO.class);
        bookDAO = mock(BookDAO.class);
        issueService = new IssueRecordServiceImpl(issueRecordDAO, bookDAO);
    }

    @Test
    public void testIssueBook_Success() throws Exception {
        Book book = new Book(1, "Java", "Author","education",'A','A');
        when(bookDAO.getBookById(1)).thenReturn(book);

        issueService.issueBook(1, 100);
        verify(issueRecordDAO).addIssueRecord(any(IssueRecord.class));
        verify(bookDAO).updateBookAvailability(1, 'I');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIssueBook_BookNotFound() throws Exception {
        when(bookDAO.getBookById(1)).thenReturn(null);
        issueService.issueBook(1, 100);
    }
//category,status,availability,
    @Test(expected = IllegalStateException.class)
    public void testIssueBook_BookAlreadyIssued() throws Exception {
        Book book = new Book(1, "Java", "Author","education", 'I','A');
        when(bookDAO.getBookById(1)).thenReturn(book);
        issueService.issueBook(1, 100);
    }

    @Test
    public void testReturnBook_Success() throws Exception {
    	 Book book = new Book(1, "Java", "Author","education", 'I','A');
    	 //int issueId,bookid,memberid,status,issuedate,returndate
        IssueRecord issued = new IssueRecord(1, 1, 100, 'A',LocalDate.now(), null);
        when(bookDAO.getBookById(1)).thenReturn(book);
        when(issueRecordDAO.getActiveIssuesByMember(100)).thenReturn(Arrays.asList(issued));

        issueService.returnBook(1, 100);
        verify(issueRecordDAO).updateIssueRecord(any(IssueRecord.class));
        verify(bookDAO).updateBookAvailability(1, 'A');
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReturnBook_BookNotFound() throws Exception {
        when(bookDAO.getBookById(1)).thenReturn(null);
        issueService.returnBook(1, 100);
    }

    @Test(expected = IllegalStateException.class)
    public void testReturnBook_NoActiveIssue() throws Exception {
   	 Book book = new Book(1, "Java", "Author","education", 'I','A');
        when(bookDAO.getBookById(1)).thenReturn(book);
        when(issueRecordDAO.getActiveIssuesByMember(100)).thenReturn(Arrays.asList());

        issueService.returnBook(1, 100);
    }

    @Test
    public void testGetAllIssueRecords() throws Exception {
        when(issueRecordDAO.getAllIssueRecords()).thenReturn(Arrays.asList(new IssueRecord()));
        assertEquals(1, issueService.getAllIssueRecords().size());
    }

    @Test
    public void testGetOverdueBooks() throws Exception {
        when(issueRecordDAO.getOverdueBooks()).thenReturn(Arrays.asList(new IssueRecord()));
        assertEquals(1, issueService.getOverdueBooks().size());
    }

    @Test
    public void testGetActiveIssuesByMember() throws Exception {
        when(issueRecordDAO.getActiveIssuesByMember(100)).thenReturn(Arrays.asList(new IssueRecord()));
        assertEquals(1, issueService.getActiveIssuesByMember(100).size());
    }
}
