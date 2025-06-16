package DAO;

import casestudy.IssueRecord;
import casestudy.Book;
import casestudy.Member;
import casestudy.LibraryException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class IssueDAOTest {

    private IssueDAO issueDAO;
    private MemberDAO memberDAO;
    private BookDAO bookDAO;

    @Before
    public void setUp() {
        issueDAO = new IssueDAO();
        memberDAO = new MemberDAO();
        bookDAO = new BookDAO();
    }

    @Test
    public void testIssueAndReturnBook() throws LibraryException {
        // Add member
        Member member = new Member("M1", "m23@test.com",
                123456789L, 'M', "Test Addr");
        memberDAO.addMember(member);
        List<Member> members = memberDAO.getAllMembers();
        Member latestMember = members.get(members.size() - 1);

        // Add book
        Book book = new Book("Book1", "Author1", "Cat1", 'A', 'A');
        bookDAO.addBook(book);
        List<Book> books = bookDAO.getAllBooks();
        Book latestBook = books.get(books.size() - 1);

        // Issue book
        IssueRecord issue = new IssueRecord();
        issue.setBookId(latestBook.getBookId());
        issue.setMemberId(latestMember.getMemberId());
        issue.setIssueDate(LocalDate.now());
        issue.setStatus('I');

        issueDAO.issueBook(issue);

        List<IssueRecord> issues = issueDAO.getAllIssues();
        IssueRecord lastIssue = issues.get(issues.size() - 1);
        assertEquals('I', lastIssue.getStatus());

        // Return book
        issueDAO.returnBook(lastIssue.getIssueId());

        List<IssueRecord> updated = issueDAO.getAllIssues();
        IssueRecord returned = null;
        for (IssueRecord i : updated) {
            if (i.getIssueId() == lastIssue.getIssueId()) {
                returned = i;
                break;
            }
        }

        assertNotNull("Issue record not found after return", returned);
        assertEquals('R', returned.getStatus());
    }

    @Test
    public void testGetOverdueBooks() throws LibraryException {
        List<IssueRecord> overdue = issueDAO.getOverdueBooks();
        assertNotNull(overdue);
    }

    @Test
    public void testGetAllIssues() throws LibraryException {
        List<IssueRecord> all = issueDAO.getAllIssues();
        assertNotNull(all);
    }
}
