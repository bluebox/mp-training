package com.lms.service;

import java.time.LocalDate;
import java.util.List;

import com.lms.model.IssueBook;

public interface IssueBookServiceInterface {

    boolean issueBook(IssueBook record);
    boolean returnBook(int issueId, LocalDate returnDate);

    List<IssueBook> getAllIssueRecords();

    List<IssueBook> getActiveIssuesByMember(int memberId);

    boolean isBookAlreadyIssued(int bookId);
}
