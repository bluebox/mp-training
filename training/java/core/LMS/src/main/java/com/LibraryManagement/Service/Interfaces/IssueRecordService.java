package com.LibraryManagement.Service.Interfaces;

import java.util.List;

import com.LibraryManagement.Models.IssueRecords;

public interface IssueRecordService {

    boolean issueBook(IssueRecords record) throws Exception;
    boolean returnBook(int issueId) throws Exception;
    IssueRecords getActiveIssueByBookId(int bookId) throws Exception;
    List<IssueRecords> getAllIssues() throws Exception;
	List<Integer> getAvailableBookIds();
	List<Integer> getValidMemberIds();
}

