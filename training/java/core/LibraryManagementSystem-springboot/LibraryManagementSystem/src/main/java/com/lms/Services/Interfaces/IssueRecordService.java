package com.lms.Services.Interfaces;

import com.lms.Models.Book;
import com.lms.Models.IssueRecords;
import com.lms.Models.Member;

import java.util.List;

public interface IssueRecordService {

    boolean issueBook(IssueRecords record) throws Exception;

    boolean returnBook(int issueId) throws Exception;

   // IssueRecords getActiveIssueByBookId(int bookId) throws Exception;

    List<IssueRecords> getAllIssues() throws Exception;
    public List<Book> getAvailabeBooks() ;
    public List<Member> getValidMemberIds();
}

