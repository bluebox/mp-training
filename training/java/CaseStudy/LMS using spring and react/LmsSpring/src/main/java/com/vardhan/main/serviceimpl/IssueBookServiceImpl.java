package com.vardhan.main.serviceimpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.vardhan.main.dao.BookDao;
import com.vardhan.main.dao.IssueBookDao;
import com.vardhan.main.dao.MemberDao;
import com.vardhan.main.model.Book;
import com.vardhan.main.model.IssueBook;
import com.vardhan.main.service.IssueBookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Validated
@Slf4j
public class IssueBookServiceImpl implements IssueBookService {

    @Autowired
    private IssueBookDao issueBookDao;
    
    @Autowired
    private BookDao bookDao;
    
    @Autowired
    private MemberDao memberDao;

    @Value("${library.issue.duration:14}")
    private int defaultIssueDurationDays;

    @Value("${library.max.books.per.member:3}")
    private int maxBooksPerMember;

    @Override
    public IssueBook issueBook( IssueBook issueBook) throws DataAccessException {
        log.info("Attempting to issue book {} to member {}", issueBook.getBookId(), issueBook.getMemberId());
        
        
        if (!validateIssueRequest(issueBook.getMemberId(), issueBook.getBookId())) {
            throw new IllegalArgumentException("Cannot issue book - validation failed");
        }
        
        
        if (issueBook.getIssueDate() == null) {
            issueBook.setIssueDate(LocalDate.now());
        }
        
        
        if (issueBook.getReturnDate() == null) {
            issueBook.setReturnDate(calculateDueDate(issueBook.getIssueDate()));
        }
        
        IssueBook savedIssue = issueBookDao.save(issueBook);
        log.info("Successfully issued book with issue ID: {}", savedIssue.getIssueId());
        return savedIssue;
    }

    @Override
    public boolean returnBook( Integer issueId, LocalDate actualReturnDate) throws DataAccessException {
        log.info("Attempting to return book for issue ID: {}", issueId);
        
        Optional<IssueBook> issueOpt = issueBookDao.findById(issueId);
        if (issueOpt.isEmpty()) {
            throw new IllegalArgumentException("Issue record not found with ID: " + issueId);
        }
        
        IssueBook issue = issueOpt.get();
        if (issue.getActualReturnDate() != null) {
            throw new IllegalArgumentException("Book has already been returned");
        }
        
        boolean returned = issueBookDao.returnBook(issueId, actualReturnDate);
        if (returned) {
            log.info("Successfully returned book for issue ID: {}", issueId);
        }
        return returned;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IssueBook> findIssueById( Integer issueId) throws DataAccessException {
        return issueBookDao.findById(issueId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueBook> getAllIssueRecords() throws DataAccessException {
        return issueBookDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueBook> getActiveIssuesByMember( Integer memberId) throws DataAccessException {
        return issueBookDao.findActiveIssuesByMember(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueBook> getOverdueBooks() throws DataAccessException {
        return issueBookDao.findOverdueBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueBook> getIssueHistory( String bookId) throws DataAccessException {
        return issueBookDao.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isBookCurrentlyIssued( String bookId) throws DataAccessException {
        return issueBookDao.isBookCurrentlyIssued(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean canMemberIssueBook( Integer memberId,  String bookId) throws DataAccessException {
        
        if (issueBookDao.hasMemberIssuedBook(memberId, bookId)) {
            return false;
        }
        
        
        long activeIssues = issueBookDao.countActiveIssuesByMember(memberId);
        return activeIssues < maxBooksPerMember;
    }

    @Override
    @Transactional(readOnly = true)
    public long getActiveIssueCountByMember( Integer memberId) throws DataAccessException {
        return issueBookDao.countActiveIssuesByMember(memberId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateIssueRequest( Integer memberId,  String bookId) throws DataAccessException {
        
        if (!memberDao.findById(memberId).isPresent()) {
            log.warn("Member not found: {}", memberId);
            return false;
        }
        
        
        Optional<Book> bookOpt = bookDao.findByBookId(bookId);
        if (bookOpt.isEmpty()) {
            log.warn("Book not found: {}", bookId);
            return false;
        }
        
        Book book = bookOpt.get();
        
        
        if (!book.isActive() || !book.isAvailable()) {
            log.warn("Book is not available for issue: {}", bookId);
            return false;
        }
        
        
        if (isBookCurrentlyIssued(bookId)) {
            log.warn("Book is already issued: {}", bookId);
            return false;
        }
        
        
        if (!canMemberIssueBook(memberId, bookId)) {
            log.warn("Member cannot issue more books: {}", memberId);
            return false;
        }
        
        return true;
    }

    @Override
    public LocalDate calculateDueDate( LocalDate issueDate) throws DataAccessException {
        return issueDate.plusDays(defaultIssueDurationDays);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalActiveIssues() throws DataAccessException {
        return issueBookDao.findActiveIssues().size();
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalOverdueIssues() throws DataAccessException {
        return issueBookDao.countOverdueBooks();
    }
}
