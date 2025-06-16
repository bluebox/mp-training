package Service;

import casestudy.IssueRecord;
import casestudy.LibraryException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class IssueServiceTest {

    private IssueService issueService;

    @Before
    public void setUp() {
        issueService = new IssueService();
    }

    @Test(expected = LibraryException.class)
    public void testIssueBookWithInvalidIds() throws LibraryException {
        IssueRecord issue = new IssueRecord(0, 0, 'I', LocalDate.now(), null);
        issueService.issueBook(issue);
    }

    @Test(expected = LibraryException.class)
    public void testReturnBookByInvalidBookId() throws LibraryException {
        issueService.returnBookByBookId(-1);
    }

    @Test(expected = LibraryException.class)
    public void testReturnBookWithInvalidIssueId() throws LibraryException {
        issueService.returnBook(0);
    }

    @Test
    public void testGetOverdueBooks() throws LibraryException {
        List<IssueRecord> overdue = issueService.getOverdueBooks();
        assertNotNull(overdue);
    }

    @Test
    public void testGetActiveIssuesByMember() throws LibraryException {
        List<IssueRecord> issues = issueService.getActiveIssuesByMember(1);
        assertNotNull(issues);
    }

    @Test
    public void testGetAllIssues() throws LibraryException {
        List<IssueRecord> allIssues = issueService.getAllIssues();
        assertNotNull(allIssues);
    }
}
