package com.lms.springbootlms.service;

import java.time.LocalDate;
import java.util.List;

import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.BookCategory;
import com.lms.springbootlms.model.IssueBook;
import com.lms.springbootlms.model.Member;


public interface IssueBookService {

    boolean issueBook(IssueBook record) throws ServiceException;

    boolean returnBook(int issueId, LocalDate returnDate) throws ServiceException;

    List<IssueBook> getAllIssueRecords() throws ServiceException;

    List<IssueBook> getActiveIssuesByMember(int memberId) throws ServiceException;

    boolean isBookAlreadyIssued(String bookId) throws ServiceException;

    Member getMemberByMobile(String mobile) throws InvalidInputException, ServiceException;

    List<Book> getAvailableBooksByCategory(BookCategory selectedCategory) throws ServiceException;

    List<Book> getAllAvailableBooks(BookCategory category) throws ServiceException;

    void updateBookAvailability(String bookId, char availability) throws ServiceException;
}
