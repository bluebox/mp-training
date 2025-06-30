package com.librarymanagement.service;
import java.time.LocalDate;
import com.librarymanagement.model.*;
import com.librarymanagement.dao.*;
import com.librarymanagement.exceptions.IssuedBookException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service

public class IssueRecordServiceImpl implements IssueRecordService {

    private final IssueRecordDAO issueRecordDAO;
    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;
    @Autowired
    public IssueRecordServiceImpl(IssueRecordDAO issueRecordDAO, BookDAO bookDAO,MemberDAO memberDAO) {
        this.issueRecordDAO = issueRecordDAO;
        this.bookDAO = bookDAO;
       this.memberDAO=memberDAO;
    }

    @Override
    public void issueBook(int bookId, int memberId) throws Exception {
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }
        if (book.getAvailability() == 'I') {
            throw new IllegalStateException("Book is already issued");
        }
        Member member=memberDAO.getMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found");
        }
        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setBookId(bookId);
        issueRecord.setMemberId(memberId);
        issueRecord.setStatus('I');
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setReturnDate(LocalDate.now().plusDays(13));
        issueRecordDAO.addIssueRecord(issueRecord);
        bookDAO.updateBookAvailability(bookId, 'I');
    }

    @Override
    public void returnBook(int bookId, int memberId) throws Exception {
    	Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }
        Member member=memberDAO.getMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found");
        }
        List<IssueRecord> activeIssues = issueRecordDAO.getActiveIssuesByMember(memberId);
        IssueRecord issueToUpdate = null;

        for (IssueRecord ir : activeIssues) {
            if (ir.getBookId() == bookId && ir.getStatus() == 'I') {
                issueToUpdate = ir;
                break;
            }
        }

        if (issueToUpdate == null) {
            throw new IssuedBookException("This book is not currently issued to the member");
        }

        issueToUpdate.setStatus('R');
        issueToUpdate.setReturnDate(LocalDate.now());

        
        issueRecordDAO.updateIssueRecord(issueToUpdate);
        bookDAO.updateBookAvailability(bookId, 'A');
    }

    @Override
    public List<IssueRecord> getAllIssueRecords() throws Exception {
    	return issueRecordDAO.getAllIssueRecords();
        
    }

    @Override
    public List<IssueRecord> getActiveIssuesByMember(int memberId) throws Exception {
        return issueRecordDAO.getActiveIssuesByMember(memberId);
    }

    @Override
    public List<IssueRecord> getOverdueBooks() throws Exception {
        return issueRecordDAO.getOverdueBooks();
    }
}