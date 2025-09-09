package com.lms.dao;

import java.time.LocalDate;
import java.util.List;

import com.lms.model.Book;
import com.lms.model.IssueBook;

public interface BookIssueDaoInterface {

    boolean issueBook(IssueBook record);

    boolean returnBook(int issueId, LocalDate returnDate);

    List<IssueBook> getAllIssueRecords();

    List<IssueBook> getActiveIssuesByMember(int memberId);

    boolean isBookAlreadyIssued(int bookId);

    List<Book> getAvailableBooksByCategory(String category);
}