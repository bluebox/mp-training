package com.LibraryManagement.service;


import java.time.LocalDate;
import java.util.List;

import com.LibraryManagement.dao.BookDAO;
import com.LibraryManagement.dao.IssueRecordDAO;
import com.LibraryManagement.dao.MemberDAO;
import com.LibraryManagement.model.*;
import com.LibraryManagement.model.IssueRecord;

public class IssueRecordServiceImpl implements IssueRecordService {

    private final IssueRecordDAO issueRecordDAO;
    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;

    public IssueRecordServiceImpl(IssueRecordDAO issueRecordDAO, BookDAO bookDAO, MemberDAO memberDAO) {
        this.issueRecordDAO = issueRecordDAO;
        this.bookDAO = bookDAO;
        this.memberDAO=memberDAO;
    }

    @Override
    public void issueBook(int bookId, int memberId) throws Exception {
        // Check book availability
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }
        Member member=memberDAO.getMemberById(memberId);
        if(member==null){
            throw new IllegalArgumentException("Member doesn't exists");
        }
        if (book.getAvailability() == 'I') {
            throw new IllegalStateException("Book is already issued");
        }

        // Create issue record
        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setBookId(bookId);
        issueRecord.setMemberId(memberId);
        issueRecord.setStatus('I');
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setReturnDate(null);

        issueRecordDAO.addIssueRecord(issueRecord);

       
        bookDAO.updateBookAvailability(bookId, 'I');
    }

    @Override
    public void returnBook(int bookId, int memberId) throws Exception {
        List<IssueRecord> activeIssues = issueRecordDAO.getActiveIssuesByMember(memberId);
        IssueRecord issueToUpdate = null;

        for (IssueRecord ir : activeIssues) {
            if (ir.getBookId() == bookId && ir.getStatus() == 'I') {
                issueToUpdate = ir;
                break;
            }
        }

        if (issueToUpdate == null) {
            throw new IllegalStateException("This book is not currently issued to the member");
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