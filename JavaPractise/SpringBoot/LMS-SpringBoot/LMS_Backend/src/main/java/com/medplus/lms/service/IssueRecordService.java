package com.medplus.lms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medplus.lms.dao.BookRepository;
import com.medplus.lms.dao.BookRepositoryInterface;
import com.medplus.lms.dao.IssueRecordRepository;
import com.medplus.lms.dao.IssueRecordRepositoryInterface;
import com.medplus.lms.dao.MemberRepository;
import com.medplus.lms.dao.MemberRepositoryInterface;
import com.medplus.lms.domain.AvailabilityStatus;
import com.medplus.lms.domain.Book;
import com.medplus.lms.domain.IssueRecordDto;
import com.medplus.lms.domain.IssueStatus;
import com.medplus.lms.domain.Member;
import com.medplus.lms.domain.Status;
import com.medplus.lms.exceptions.ManagementException;

@Service
public class IssueRecordService implements IssueRecordServiceInterface{

    private final IssueRecordRepositoryInterface issueRecordDao;
    private final BookRepositoryInterface bookDao;
    private final MemberRepositoryInterface memberDao;

    public IssueRecordService(IssueRecordRepository repo, BookRepository bookRepo, MemberRepository memberRepo) {
        this.issueRecordDao = repo;
        this.bookDao = bookRepo;
        this.memberDao = memberRepo;
    }

    public void issueBook(int bookId, int memberId, String issuedBy) throws ManagementException {
        Book book = bookDao.findBookById(bookId);
        if (book == null) throw new ManagementException("Book does not exist"+bookId);
        if (book.getAvailability() != AvailabilityStatus.AVAILABLE) 
            throw new ManagementException("Book is already issued"+bookId);
        Member member=memberDao.findMemberById(memberId);
        if (member == null) 
            throw new ManagementException("Member is not available "+memberId);
        if(member.getStatus()==Status.INACTIVE)
        	throw new ManagementException("Member is not active"+memberId);
        if(book.getStatus()==Status.INACTIVE)
        	throw new ManagementException("Book is Inactive : "+bookId);

        book.setAvailability(AvailabilityStatus.ISSUED);
        bookDao.updateBookAvailability(book);

        issueRecordDao.issueBook(bookId, memberId, issuedBy, LocalDateTime.now(), IssueStatus.ISSUED);
    }

    public void returnBook(int bookId, int memberId, String returnedTo) {
        Book book = bookDao.findBookById(bookId);
        if (book == null) throw new ManagementException("Book does not  exist with given id :"+bookId);
        
        if (memberDao.findMemberById(memberId) == null)
            throw new ManagementException("Member does not exists");
        if (book.getAvailability() != AvailabilityStatus.ISSUED)
            throw new ManagementException("This book is not currently issued");

        issueRecordDao.returnBook(bookId, memberId, returnedTo);

        book.setAvailability(AvailabilityStatus.AVAILABLE);
        bookDao.updateBookAvailability(book);
    }

    public List<IssueRecordDto> getAllIssues() {
        return issueRecordDao.getAllIssues();
    }
}
