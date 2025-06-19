package librarySystem.tests;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import librarySystem.DAO.*;
import model.*;
public class ServiceTests {
	@Mock
    private BookDao bookDao;
    @Mock
    private MemberDao memberDao;
    @Mock
    private IssueRecordDao issueDao;

    @InjectMocks
    private libraryServices service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddBook_ValidBook() {
        BookPojo book = new BookPojo();
        book.setTitle("Title");
        book.setAuthor("Author");

        when(bookDao.insertBook(book)).thenReturn(true);

        assertTrue(service.addBook(book));
    }

    @Test
    public void testAddBook_InvalidBook() {
        BookPojo book = new BookPojo();
        book.setTitle("");

        assertFalse(service.addBook(book));
    }

    @Test
    public void testUpdateBookDetails_Valid() {
        BookPojo oldBook = new BookPojo();
        BookPojo newBook = new BookPojo();
        when(bookDao.updateBookDetails(oldBook, newBook)).thenReturn(true);

        assertTrue(service.updateBookDetails(oldBook, newBook));
    }

    @Test
    public void testUpdateBookDetails_Invalid() {
        assertFalse(service.updateBookDetails(null, null));
    }

    @Test
    public void testViewAllBooks() {
        List<BookPojo> books = Arrays.asList(new BookPojo(), new BookPojo());
        when(bookDao.getBooks()).thenReturn(books);

        assertEquals(2, service.viewAllBooks().size());
    }

    @Test
    public void testRegisterMember_Success() {
        MemberPojo member = new MemberPojo();
        member.setName("John");

        when(memberDao.isPresent(member)).thenReturn(false);
        when(memberDao.registerMember(member)).thenReturn(true);

        assertTrue(service.registerMember(member));
    }

    @Test
    public void testRegisterMember_AlreadyExists() {
        MemberPojo member = new MemberPojo();
        member.setName("John");

        when(memberDao.isPresent(member)).thenReturn(true);

        assertFalse(service.registerMember(member));
    }

    @Test
    public void testIssueBook_Success() {
        BookPojo book = new BookPojo();
        book.setBookId(1);
        book.setAvailability('A');

        when(bookDao.getBookById(1)).thenReturn(book);
        when(issueDao.issueBook(any())).thenReturn(true);
        when(bookDao.updateBookAvailability(1, 'I')).thenReturn(true);

        assertTrue(service.issueBook(1, 1, Date.valueOf(LocalDate.now())));
    }

    @Test
    public void testReturnBook_Success() {
        BookPojo book = new BookPojo();
        book.setBookId(1);

        when(issueDao.returnBook(1, 1, Date.valueOf(LocalDate.now()))).thenReturn(true);
        when(bookDao.getBookById(1)).thenReturn(book);
        when(bookDao.updateBookAvailability(1, 'A')).thenReturn(true);

        assertTrue(service.returnBook(1, 1, Date.valueOf(LocalDate.now())));
    }

    @Test
    public void testGetOverdueBooks() {
        IssueRecordPojo record = new IssueRecordPojo();
        record.setStatus('I');
        record.setIssueDate(Date.valueOf(LocalDate.now().minusDays(20)));

        when(issueDao.getAllRecords()).thenReturn(Collections.singletonList(record));

        assertEquals(1, service.getOverdueBooks(7).size());
    }

    @Test
    public void testGetBookCountPerCategory() {
        BookPojo b1 = new BookPojo(); b1.setCategory("Sci-Fi");
        BookPojo b2 = new BookPojo(); b2.setCategory("Sci-Fi");
        BookPojo b3 = new BookPojo(); b3.setCategory("Horror");

        when(bookDao.getBooks()).thenReturn(Arrays.asList(b1, b2, b3));

        Map<String, Long> result = service.getBookCountPerCategory();
        assertEquals(2, result.get("Sci-Fi"));
        assertEquals(1, result.get("Horror"));
    }
}
	