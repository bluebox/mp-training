package com.example.library.service;

import java.time.LocalDate;
import java.util.List;

import com.example.library.model.IssueRecord;

public interface IssueService {
    void issueBook(IssueRecord issue) throws Exception;
    void returnBook(int issueId, int bookId, LocalDate returnDate) throws Exception;
    List<IssueRecord> getAllIssuedRecords() throws Exception;
    List<IssueRecord> getOverdueRecords() throws Exception;
}
