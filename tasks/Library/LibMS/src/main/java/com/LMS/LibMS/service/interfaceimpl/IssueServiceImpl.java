package com.LMS.LibMS.service.interfaceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.LMS.LibMS.model.Book;
import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.model.Member;
import com.LMS.LibMS.model.enums.BookAvailability;
import com.LMS.LibMS.model.enums.BookStatus;
import com.LMS.LibMS.model.enums.IssueStatus;
import com.LMS.LibMS.repository.interfaces.IssueRecordRepository;
import com.LMS.LibMS.service.interfaces.BookService;
import com.LMS.LibMS.service.interfaces.IssueService;
import com.LMS.LibMS.service.interfaces.MemberService;

@Service
public class IssueServiceImpl implements IssueService {

    private final BookService bookService;
    private final IssueRecordRepository issueRecordRepository;
    private final MemberService memberService;
    private final String CURRENT_USER = "ADMIN";

    @Autowired
    public IssueServiceImpl(BookService bookService, IssueRecordRepository issueRecordRepository,MemberService memberService) {
			this.bookService = bookService;
			this.issueRecordRepository = issueRecordRepository;
			this.memberService = memberService;
    }

    

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void issueBook(int bookId, int memberId, String issuedBy) throws Exception {
        Book book = bookService.findBookById(bookId);
        
        if (book == null) 
        	throw new Exception("Book with Id "+bookId+" not found ");
        if (book.getAvailability() == BookAvailability.ISSUED) 
        	throw new Exception("Book already issued");
        if (book.getStatus() == BookStatus.INACTIVE) 
        	throw new Exception("Book is inactive");


        Member member = memberService.getMemberById(memberId);
        if (member == null)
        	throw new Exception("Member with Id "+memberId+" not found");

        IssueRecord newIssue = new IssueRecord();
        newIssue.setBookId(bookId);
        newIssue.setMemberId(memberId);
        newIssue.setStatus(IssueStatus.ISSUED);
        newIssue.setIssueDate(LocalDateTime.now());
        newIssue.setIssuedBy(issuedBy);        
        issueRecordRepository.addIssueRecord(newIssue);

        book.setAvailability(BookAvailability.ISSUED);
        bookService.updateAvailabilitiesById(List.of(book.getBookId()));
    }

	@Override
    @Transactional(rollbackFor = Exception.class)
    public void returnBook(int bookId, String returnedBy) throws Exception {
        IssueRecord activeIssue = issueRecordRepository.getActiveIssueRecordByBookId(bookId);
        if (activeIssue == null)
        	throw new Exception("Book not currently issued");
        
        issueRecordRepository.logIssueRecord(activeIssue.getBookId());

        activeIssue.setStatus(IssueStatus.RETURNED);
        activeIssue.setReturnDate(LocalDateTime.now());
        activeIssue.setReturnedBy(returnedBy);

        issueRecordRepository.updateIssueRecord(activeIssue);
        
        Book book = bookService.findBookById(bookId);
        if (book == null) 
        	throw new Exception("Book not found");

        book.setAvailability(BookAvailability.AVAILABLE);
        bookService.updateAvailabilitiesById(List.of(book.getBookId()));
    }

    @Override
    public List<IssueRecord> getAllIssuedRecords() {
        return issueRecordRepository.getAllIssuedRecords();
    }

    @Override
    public List<IssueRecord> getOverdueBooks(int dueDays) {
        List<IssueRecord> allIssued = getAllIssuedRecords();
        LocalDateTime now = LocalDateTime.now();
        return allIssued.stream()
                .filter(record -> record.getStatus() == IssueStatus.ISSUED && record.getReturnDate() == null)
                .filter(record -> record.getIssueDate().plusDays(dueDays).isBefore(now))
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> getMembersWithActiveBooks() {
        List<IssueRecord> allIssued = getAllIssuedRecords();
        return allIssued.stream()
                .filter(record -> record.getStatus() == IssueStatus.ISSUED && record.getReturnDate() == null)
                .map(record -> memberService.getMemberById(record.getMemberId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}