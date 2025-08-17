package com.lms.springbootlms.dao;

import java.time.LocalDate;
import java.util.List;

import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.IssueBook;
import com.lms.springbootlms.model.Member;

public interface BookIssueDao {
    boolean issueBook(IssueBook record) throws DaoException;

    boolean returnBook(int issueId, LocalDate actualReturnDate) throws DaoException;

    List<IssueBook> getAllIssueRecords() throws DaoException;

    List<IssueBook> getActiveIssuesByMember(int memberId) throws DaoException;

    boolean isBookAlreadyIssued(String bookId) throws DaoException;

    List<Book> getAvailableBooksByCategory(String category) throws DaoException;

    Member getMemberByMobile(String mobile) throws DaoException;

    void updateBookAvailability(String bookId, char availability) throws DaoException;
}
