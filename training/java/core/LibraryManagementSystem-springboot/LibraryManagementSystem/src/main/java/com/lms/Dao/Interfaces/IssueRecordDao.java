package com.lms.Dao.Interfaces;


import java.util.List;

import com.lms.Models.Book;
import com.lms.Models.IssueRecords;
import com.lms.Models.Member;

public interface IssueRecordDao {
    boolean issueBook(IssueRecords record);
    boolean returnBook(int issueId);
    IssueRecords getActiveIssueByBookId(int bookId);
    List<IssueRecords> getAllIssues();
    List<Integer> getAvailableBookIds();
   // List<Integer> getValidMemberIds();
    public List<Book> getAvailabeBooks() ;
    public List<Member> getValidMemberIds();
}

