package com.library.service;
import com.library.dao.BookDao;
import com.library.dao.IssueRecordDao;
import com.library.util.AlertMsg;
import com.library.controller.Book;
import com.library.controller.IssueRecord;
import com.library.controller.Member;
import com.library.dao.MemberDao;

import java.time.LocalDate;
import java.util.List;

public class IssueService {
    private final IssueRecordDao issueDAO = new IssueRecordDao();
    private final BookDao bookDAO = new BookDao();
    private final MemberDao memberDAO=new MemberDao();

    public void issueBook(IssueRecord record) throws Exception{
        Book book = bookDAO.getBookById(record.getBookId());
        if (book == null) 
        	throw new Exception("Book not found");
        if (book.getAvailability() == 'I') 
        	throw new Exception("Book is already issued");
        Member member=memberDAO.getMemberById(record.getMemberId());
        if(member==null)
        	AlertMsg.showError("Member not found");
        issueDAO.issueBook(record);
        bookDAO.updateAvailabilityAndStatus(book.getBookId(),'I','I');
        
    }

    public void returnBook(int issueId, LocalDate returnDate) throws Exception {

        issueDAO.returnBook(issueId, returnDate);
        int bookId=issueDAO.getBookIdByIssueId(issueId);
        bookDAO.updateAvailabilityAndStatus(bookId,'A','A');
        
    }

    public List<IssueRecord> getAllIssuedRecords() throws Exception {
        return issueDAO.getAllIssuedRecords();
    }
}