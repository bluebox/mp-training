package com.example.lms.service.impl;

import com.example.lms.model.Book;
import com.example.lms.model.IssueRecord;
import com.example.lms.repository.BookRepository;
import com.example.lms.repository.IssueRepository;
import com.example.lms.service.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;

    @Override
    @Transactional
    public String issueBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            return "Book not found";
        }

        if ("I".equalsIgnoreCase(book.getAvailability())) {
            return "Book is already issued";
        }

        book.setAvailability("I");
        bookRepository.update(book);

        IssueRecord record = new IssueRecord();
        record.setBookId(bookId);
        record.setMemberId(memberId);
        record.setStatus("I");
        record.setIssueDate(LocalDate.now());
        issueRepository.save(record);

        return "Book issued successfully";
    }

    @Override
    @Transactional
    public String returnBook(Long issueId) {
        IssueRecord record = issueRepository.findById(issueId);
        if (record == null) {
            return "Issue record not found";
        }
        if ("R".equalsIgnoreCase(record.getStatus())) {
            return "Book already returned";
        }

        issueRepository.markReturned(issueId, LocalDate.now());

        Book book = bookRepository.findById(record.getBookId());
        if (book != null) {
            book.setAvailability("A");
            bookRepository.update(book);
        }

        return "Book returned successfully";
    }

    @Override
    public IssueRecord getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    @Override
    public List<IssueRecord> getAllIssues() {
        return issueRepository.findAll();
    }
}
