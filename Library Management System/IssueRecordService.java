package com.LibraryManagement.service.interfaces;

import java.util.List;

import com.LibraryManagement.models.IssueRecords;

public interface IssueRecordService {

    boolean issueBook(IssueRecords record) throws Exception;
    boolean returnBook(int issueId) throws Exception;
    IssueRecords getActiveIssueByBookId(int bookId) throws Exception;
    List<IssueRecords> getAllIssues() throws Exception;
}

