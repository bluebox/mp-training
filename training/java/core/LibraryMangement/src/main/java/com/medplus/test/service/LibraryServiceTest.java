package com.medplus.test.service;


import org.junit.*;
import com.medplus.service.LibraryService;
import com.medplus.model.*;
import java.util.*;
import java.time.*;

public class LibraryServiceTest {
    private LibraryService service;
    @Before
    public void setUp() { service = new LibraryService(); }
    @Test
    public void testGetAllBooksNotNull() throws Exception {
        Assert.assertNotNull(service.getAllBooks());
    }
    @Test
    public void testIssueBook() throws Exception {
        service.issueBook(1, 1);
    }
    @Test
    public void testReturnBook() throws Exception {
        service.returnBook(1);
    }
    @Test
    public void testGetOverdueBooks() throws Exception {
        List<IssueRecord> overdue = service.getOverdueBooks();
        Assert.assertNotNull(overdue);
    }
    @Test
    public void testGetBooksCountByCategory() throws Exception {
        long count = service.getBooksCountByCategory("Fiction");
        Assert.assertTrue(count >= 0);
    }
    @Test
    public void testGetMembersWithActiveIssues() throws Exception {
        List<Member> members = service.getMembersWithActiveIssues();
        Assert.assertNotNull(members);
    }
}