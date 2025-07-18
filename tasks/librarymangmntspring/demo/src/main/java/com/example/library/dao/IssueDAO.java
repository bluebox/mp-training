package com.example.library.dao;

import java.time.LocalDate;
import java.util.List;

import com.example.library.model.IssueRecord;

public interface IssueDAO {
    void issueBook(IssueRecord issue) throws Exception;
    void returnBook(int issueId, int bookId, LocalDate returnDate) throws Exception;
    List<IssueRecord> getAllIssuedRecords() throws Exception;
    IssueRecord getRecordById(int issueId) throws Exception;
}
