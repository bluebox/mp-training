package com.lms.junittesting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.lms.daoImpl.BookDao;
import com.lms.daoImpl.IssueBookDaoImpl;
import com.lms.daoImpl.MemberDao;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.IssueBook;
import com.lms.model.Member;
import com.lms.util.DBUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class IssueBookDaoImplTest {

    private static int testMemberId;
    private static String testBookId;
    private static int issuedRecordId;
    private final IssueBookDaoImpl dao = new IssueBookDaoImpl();

    @BeforeAll
    static void setup() {
        Member testMember = new Member(0, "JUnit Member", "junit@example.com", "8888888888", "Other", "JUnit Addr");
        MemberDao.addMember(testMember);
        Member added = MemberDao.getMemberByMobile("8888888888");
        testMemberId = added.getMemberId();

        Book testBook = BookDao.getInstance().addBook("JUnit Book", "Tester", BookCategory.COMICS, 'A', 'A');
        testBookId = testBook.getBookId();
    }

    @Test
    @Order(1)
    void testIssueBook() {
        IssueBook issue = new IssueBook();
        issue.setBookId(testBookId);
        issue.setMemberId(testMemberId);
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(LocalDate.now().plusDays(7));

        boolean result = dao.issueBook(issue);
        assertTrue(result);

        List<IssueBook> allIssues = dao.getAllIssueRecords();
        assertFalse(allIssues.isEmpty());

        for (IssueBook ib : allIssues) {
            if (ib.getBookId().equals(testBookId) && ib.getMemberId() == testMemberId) {
                issuedRecordId = ib.getIssueId();
                break;
            }
        }
        assertTrue(issuedRecordId > 0);
    }

    @Test
    @Order(2)
    void testGetActiveIssuesByMember() {
        List<IssueBook> active = dao.getActiveIssuesByMember(testMemberId);
        assertFalse(active.isEmpty());
        assertEquals(testMemberId, active.get(0).getMemberId());
    }

    @Test
    @Order(3)
    void testIsBookAlreadyIssued() {
        boolean issued = dao.isBookAlreadyIssued(testBookId);
        assertTrue(issued);
    }



    @Test
    @Order(4)
    void testReturnBook() {
        boolean returned = dao.returnBook(issuedRecordId, LocalDate.now());
        assertTrue(returned);
    }

    @AfterAll
    static void cleanup() {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM issue_books WHERE issue_id = " + issuedRecordId);
            stmt.executeUpdate("DELETE FROM books WHERE book_id = '" + testBookId + "'");
            stmt.executeUpdate("DELETE FROM members WHERE member_id = " + testMemberId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}