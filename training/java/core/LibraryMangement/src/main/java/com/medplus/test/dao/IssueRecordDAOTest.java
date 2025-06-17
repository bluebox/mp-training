package com.medplus.test.dao;

import com.medplus.dao.impl.IssueRecordDAOImpl;
import com.medplus.model.IssueRecord;
import org.junit.*;
import java.time.LocalDate;
import java.util.*;

public class IssueRecordDAOTest {
    private IssueRecordDAOImpl dao;
    @Before
    public void setUp() { dao = new IssueRecordDAOImpl(); }
    @Test
    public void testIssueBook() throws Exception {
        IssueRecord r = new IssueRecord(0, 1, 1, 'I', LocalDate.now(), null);
        dao.issueBook(r);
        Assert.assertNotNull(dao.getIssueById(r.getIssueId()));
    }
    @Test
    public void testReturnBook() throws Exception {
        dao.returnBook(1);
        IssueRecord returnedIssue = dao.getIssueById(1);
        Assert.assertEquals('R', returnedIssue.getStatus());
    }
    @Test
    public void testGetAllIssuedBooks() throws Exception {
        Assert.assertNotNull(dao.getAllIssuedBooks());
    }
    @Test
    public void testGetIssuedBooksByMember() throws Exception {

    }
}