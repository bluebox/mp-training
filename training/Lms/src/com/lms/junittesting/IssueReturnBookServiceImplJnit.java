package com.lms.junittesting;

import com.lms.daoImpl.BookDao;
import com.lms.daoImpl.MemberDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.IssueBook;
import com.lms.model.Member;
import com.lms.serviceImpl.IssueBookServiceImpl;
import com.lms.serviceImpl.ReturnBookServiceImpl;
import com.lms.util.DBUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IssueReturnBookServiceImplJnit {

    private static IssueBookServiceImpl issueService;
    private static ReturnBookServiceImpl returnService;

    private static String testBookId;
    private static String testBookTitle;
    private static int testMemberId;
    private static String testMobile = "9999999999";

    @BeforeAll
    static void setup() throws InvalidInputException {
        issueService = new IssueBookServiceImpl();
        returnService = new ReturnBookServiceImpl();

        Member member;
        try {
            member = issueService.getMemberByMobile(testMobile);
        } catch (InvalidInputException e) {
            Member newMember = new Member(0, "Test JUnit", "test@junit.com", testMobile, "Other", "JUnit Address");
            MemberDao.addMember(newMember);
            member = issueService.getMemberByMobile(testMobile);  
        }

        assertNotNull(member);
        testMemberId = member.getMemberId();

        List<Book> books = issueService.getAvailableBooksByCategory(BookCategory.FICTION);
        if (books.isEmpty()) {
            Book newBook = BookDao.getInstance().addBook("JUnit Test Book", "Author JUnit", BookCategory.FICTION, 'A', 'A');
            assertNotNull(newBook);
            testBookId = newBook.getBookId();
            testBookTitle = newBook.getBookTitle();
        } else {
            Book book = books.get(0);
            testBookId = book.getBookId();
            testBookTitle = book.getBookTitle();
        }
    }


    @Test
    @Order(1)
    void testIssueBook() {
        IssueBook record = new IssueBook();
        record.setBookId(testBookId);
        record.setMemberId(testMemberId);
        record.setIssueDate(LocalDate.now());
        record.setReturnDate(LocalDate.now().plusDays(7));

        boolean result = issueService.issueBook(record);
        assertTrue(result);
    }




    @Test
    @Order(3)
    void testGetAllIssueRecords() {
        List<IssueBook> list = issueService.getAllIssueRecords();
        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    @Order(4)
    void testGetActiveIssuesByMember() {
        List<IssueBook> activeIssues = issueService.getActiveIssuesByMember(testMemberId);
        assertFalse(activeIssues.isEmpty());
    }

    @Test
    @Order(5)
    void testReturnBookByIssueId() {
        List<IssueBook> activeIssues = issueService.getActiveIssuesByMember(testMemberId);
        assertFalse(activeIssues.isEmpty());

        IssueBook issue = activeIssues.get(0);
        boolean result = issueService.returnBook(issue.getIssueId(), LocalDate.now());
        assertTrue(result);
    }

    @Test
    @Order(6)
    void testUpdateBookAvailability() {
        issueService.updateBookAvailability(testBookId, 'A');
        Book updated = issueService.getAllAvailableBooks(BookCategory.FICTION).stream()
                .filter(b -> b.getBookId().equals(testBookId))
                .findFirst().orElse(null);
        assertNotNull(updated);
        assertEquals('A', updated.getAvailability());
    }

    @Test
    @Order(7)
    void testGetMemberNameByMobile() {
        String name = returnService.getMemberNameByMobile(testMobile);
        assertNotNull(name);
        assertFalse(name.trim().isEmpty());
    }

    @Test
    @Order(8)
    void testGetIssuedBooksByMobile() {
        List<String> issuedBooks = returnService.getIssuedBooksByMobile(testMobile);
        assertNotNull(issuedBooks);
    }

    @Test
    @Order(9)
    void testReturnBookByTitle() {
        List<String> books = returnService.getIssuedBooksByMobile(testMobile);
        if (!books.isEmpty()) {
            String bookName = books.get(0);
            boolean result = returnService.returnBook(testMobile, bookName, "Active");
            assertTrue(result);
        } else {
            System.out.println("No books to return for test case.");
        }
    }

    @AfterAll
    static void cleanup() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM issue_books WHERE member_id = " + testMemberId);
            stmt.executeUpdate("DELETE FROM members WHERE member_id = " + testMemberId);
            stmt.executeUpdate("DELETE FROM books WHERE book_id = '" + testBookId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}