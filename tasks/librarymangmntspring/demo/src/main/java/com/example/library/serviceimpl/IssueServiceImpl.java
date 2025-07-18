package com.example.library.serviceimpl;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.library.daoimpl.BookDAOImpl;
import com.example.library.daoimpl.IssueDAOImpl;
import com.example.library.exception.BookAlreadyIssuedException;
import com.example.library.model.Book;
import com.example.library.model.IssueRecord;
import com.example.library.service.IssueService;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class IssueServiceImpl implements IssueService {
    private final IssueDAOImpl issueDAO;
    private final BookDAOImpl bookDAO;

    @Autowired
    public IssueServiceImpl(IssueDAOImpl issueDAO, BookDAOImpl bookDAO) {
        this.issueDAO = issueDAO;
        this.bookDAO = bookDAO;
    }

    @Override
    public void issueBook(IssueRecord issue) throws Exception {
		if (issue.getBookId() < 0) {
			throw new IllegalArgumentException("Invalid Book ID");
		}
		if (issue.getMemberId() < 0) {
			throw new IllegalArgumentException("Invalid Member ID");
		}
		

        Book book = bookDAO.getBookById(issue.getBookId());
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        if (book.getAvailability() != 'A') {
            throw new BookAlreadyIssuedException("Book is already issued.");
        }
        if (book.getStatus() != 'A') {
			throw new IllegalArgumentException("Availability status must be 'A'");
		}

        issueDAO.issueBook(issue);
    }

    @Override
    public void returnBook(int issueId, int bookId, java.time.LocalDate returnDate) throws Exception {
		if (issueId < 0) {
			throw new IllegalArgumentException("Invalid Issue ID");
		}
		if (bookId < 0) {
			throw new IllegalArgumentException("Invalid Book ID");
		}
		if (returnDate == null) {
			throw new IllegalArgumentException("Return date is required");
		}
        issueDAO.returnBook(issueId, bookId, returnDate);
    }

    @Override
    public java.util.List<com.example.library.model.IssueRecord> getAllIssuedRecords() throws Exception {
        return issueDAO.getAllIssuedRecords();
    }
    
    @Override
    public List<IssueRecord> getOverdueRecords() throws Exception {
        List<IssueRecord> issueRecords = issueDAO.getAllIssuedRecords();
        List<IssueRecord> overdueBooks = issueRecords.stream()
        	    .filter(record -> record.getStatus() == 'I')
        	    .filter(record -> record.getReturnDate() == null)
        	    .filter(record -> record.getIssueDate().isBefore(LocalDate.now().minusDays(14)))
        	    .sorted(Comparator.comparing(IssueRecord::getIssueDate))
        	    .collect(Collectors.toList());
        return overdueBooks;
        
    }
}




