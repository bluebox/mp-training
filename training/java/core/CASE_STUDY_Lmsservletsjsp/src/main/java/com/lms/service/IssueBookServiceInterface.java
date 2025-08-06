package com.lms.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.lms.model.IssueBook;

public interface IssueBookServiceInterface {

    boolean issueBook(IssueBook record) throws SQLException;
    boolean returnBook(int issueId, LocalDate returnDate) throws SQLException;

    List<IssueBook> getAllIssueRecords() throws SQLException;

    List<IssueBook> getActiveIssuesByMember(int memberId) throws SQLException;

	boolean isBookAlreadyIssued(String bookId) throws SQLException;
}
