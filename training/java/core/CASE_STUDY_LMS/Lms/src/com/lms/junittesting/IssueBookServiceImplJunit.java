package com.lms.junittesting;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.*;

import com.lms.daoImpl.BookDao;
import com.lms.daoImpl.MemberDao;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.IssueBook;
import com.lms.model.Member;
import com.lms.serviceImpl.IssueBookServiceImpl;
import com.lms.util.DBUtil;

public class IssueBookServiceImplJunit {

    private IssueBookServiceImpl issueService;
    private Member testMember;
    private Book testBook;
    private IssueBook testIssue;

    @BeforeEach
    void setUp() throws Exception {
        issueService = new IssueBookServiceImpl();

        cleanup();

        testMember = new Member(0, "JUnit Issue Member", "test@lms.com", "1212121212", "Other", "JUnit Address");
        MemberDao.addMember(testMember);
        testMember = issueService.getMemberByMobile("1212121212");
        assertNotNull(testMember, "Test member should be created and fetched");

        Book newBook = new Book(null, "JUnit Issue Book", "JUnit Author", BookCategory.FICTION, 'A', 'A');
        testBook = BookDao.addBook(newBook);
        assertNotNull(testBook, "Test book should be created and fetched");

        testIssue = new IssueBook();
        testIssue.setBookId(testBook.getBookId());
        testIssue.setMemberId(testMember.getMemberId());
        testIssue.setIssueDate(LocalDate.now());
        testIssue.setReturnDate(LocalDate.now().plusDays(7));

        boolean issued = issueService.issueBook(testIssue);
        assertTrue(issued, "Book should be issued to the member successfully.");
    }

    @Test
    void testGetActiveIssuesByMember() throws Exception {
        List<IssueBook> issues = issueService.getActiveIssuesByMember(testMember.getMemberId());
        assertNotNull(issues, "Active issues should not be null");
        assertFalse(issues.isEmpty(), "There should be at least one active issue for the member");
    }

    @Test
    void testUpdateBookAvailability() throws Exception {
        issueService.updateBookAvailability(testBook.getBookId(), 'A');
        List<Book> availableBooks = issueService.getAvailableBooksByCategory(BookCategory.FICTION);

        assertTrue(availableBooks.stream()
                .anyMatch(book -> book.getBookId().equals(testBook.getBookId())),
                "Test book should be available after updating availability");
    }

    @Test
    void testIsBookAlreadyIssued() throws Exception {
        boolean alreadyIssued = issueService.isBookAlreadyIssued(testBook.getBookId());
        assertTrue(alreadyIssued, "Book should be reported as already issued");
    }

    @Test
    void testGetAllAvailableBooks() throws Exception {
        List<Book> books = issueService.getAllAvailableBooks(BookCategory.FICTION);
        assertNotNull(books, "Available books should not be null");
    }

    @AfterEach
    void cleanup() throws Exception {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            if (testMember != null) {
                stmt.executeUpdate("DELETE FROM issue_books WHERE member_id = " + testMember.getMemberId());
                stmt.executeUpdate("DELETE FROM members WHERE member_id = " + testMember.getMemberId());
            }
            if (testBook != null) {
                stmt.executeUpdate("DELETE FROM books WHERE book_id = '" + testBook.getBookId() + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        testMember = null;
        testBook = null;
        testIssue = null;
    }
}