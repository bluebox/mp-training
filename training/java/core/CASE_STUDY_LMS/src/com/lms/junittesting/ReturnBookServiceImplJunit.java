package com.lms.junittesting;

import com.lms.daoImpl.BookDao;
import com.lms.daoImpl.MemberDao;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReturnBookServiceImplJunit {

    private IssueBookServiceImpl issueService;
    private ReturnBookServiceImpl returnService;

    private Member testMember;
    private Book testBook;

    @BeforeAll
    void setupServices() {
        issueService = new IssueBookServiceImpl();
        returnService = new ReturnBookServiceImpl();
    }

    @BeforeEach
    void setUpTestData() throws Exception {

        testMember = new Member(0, "JUnit Member", "junit@test.com", "1212121212", "FICTION", "JUnit Address");
        MemberDao.addMember(testMember);
        testMember = issueService.getMemberByMobile("1212121212");

        Book newBook = new Book(null, "JUnit Book", "JUnit Author", BookCategory.FICTION, 'A', 'A');
        testBook = BookDao.addBook(newBook);

        System.out.println("Generated Book ID: " + testBook.getBookId());
        System.out.println("Member ID: " + testMember.getMemberId());

        IssueBook issue = new IssueBook();
        issue.setBookId(testBook.getBookId());
        issue.setMemberId(testMember.getMemberId());
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(LocalDate.now().plusDays(7));

        assertTrue(issueService.issueBook(issue), "Book should be issued successfully.");
    }


    @Test
    void testFetchMemberNameByMobile() {
        String name = returnService.getMemberNameByMobile("1212121212");
        assertEquals("JUnit Member", name, "Member name should match.");
    }

    @Test
    void testFetchIssuedBooksByMobile() {
        List<String> issuedBooks = returnService.getIssuedBooksByMobile("1212121212");
        assertNotNull(issuedBooks, "Issued books list should not be null.");
        assertFalse(issuedBooks.isEmpty(), "Issued books list should not be empty.");
        assertTrue(issuedBooks.contains(testBook.getBookTitle()), "Book title should match.");
    }

    @Test
    void testReturnBook() {
        List<String> issuedBooks = returnService.getIssuedBooksByMobile("1212121212");
        assertFalse(issuedBooks.isEmpty(), "Issued books should exist before return.");

        boolean result = returnService.returnBook("1212121212", testBook.getBookTitle(), "Active");
        assertTrue(result, "Book should be returned successfully.");

        List<String> postReturn = returnService.getIssuedBooksByMobile("1234567890");
        assertFalse(postReturn.contains(testBook.getBookTitle()), "Book should no longer appear as issued.");
    }

    @AfterEach
    void cleanupTestData() {
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
    }

}
