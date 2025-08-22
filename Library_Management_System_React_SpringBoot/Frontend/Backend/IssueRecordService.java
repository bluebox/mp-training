package com.library.Library.service;

import com.library.Library.model.Book;
import com.library.Library.model.IssueRecord;
import com.library.Library.model.IssueRecordLog;
import com.library.Library.repository.BookRepository;
import com.library.Library.repository.IssueRecordLogRepository;
import com.library.Library.repository.IssueRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IssueRecordService {

    @Autowired
    private IssueRecordRepository issueRepo;

    @Autowired
    private IssueRecordLogRepository logRepo;

    @Autowired
    private BookRepository bookRepo;

    // Issue a book
    public IssueRecord issueBook(IssueRecord record) {
        record.setStatus('I'); // Mark as issued
        record.setIssueDate(LocalDate.now());
        record.setReturnDate(null);

        IssueRecord saved = issueRepo.save(record);

        // Update book availability to 'I' (Issued)
        bookRepo.findById(record.getBookId()).ifPresent(book -> {
            book.setAvailability('I');
            bookRepo.save(book);
        });

        // Save log
        IssueRecordLog log = new IssueRecordLog();
        log.setIssueId(saved.getIssueId());
        log.setBookId(saved.getBookId());
        log.setMemberId(saved.getMemberId());
        log.setStatus(saved.getStatus());
        log.setIssueDate(saved.getIssueDate());
        log.setReturnDate(saved.getReturnDate());
        logRepo.save(log);

        return saved;
    }

    // Return a book
    public IssueRecord returnBook(Integer id) {
        return issueRepo.findById(id).map(record -> {
            // Log current state
            IssueRecordLog log = new IssueRecordLog();
            log.setIssueId(record.getIssueId());
            log.setBookId(record.getBookId());
            log.setMemberId(record.getMemberId());
            log.setStatus(record.getStatus());
            log.setIssueDate(record.getIssueDate());
            log.setReturnDate(record.getReturnDate());
            logRepo.save(log);

            // Update IssueRecord
            record.setStatus('R'); // Returned
            record.setReturnDate(LocalDate.now());
            IssueRecord updated = issueRepo.save(record);

            // Update book availability to 'A' (Available)
            bookRepo.findById(record.getBookId()).ifPresent(book -> {
                book.setAvailability('A');
                bookRepo.save(book);
            });

            return updated;
        }).orElse(null);
    }

    public List<IssueRecord> getAll() {
        return issueRepo.findAll();
    }

    public List<IssueRecord> getIssued() {
        return issueRepo.findByStatus('I');
    }
}
