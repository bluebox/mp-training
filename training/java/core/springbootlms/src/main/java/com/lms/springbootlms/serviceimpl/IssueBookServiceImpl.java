package com.lms.springbootlms.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.springbootlms.dao.BookDao;
import com.lms.springbootlms.dao.BookIssueDao;
import com.lms.springbootlms.exception.DaoException;
import com.lms.springbootlms.exception.InvalidInputException;
import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.BookCategory;
import com.lms.springbootlms.model.IssueBook;
import com.lms.springbootlms.model.Member;
import com.lms.springbootlms.service.IssueBookService;

@Service
public class IssueBookServiceImpl implements IssueBookService {

    private final BookIssueDao issueBookDao;
    private final BookDao bookDao;

    @Autowired
    public IssueBookServiceImpl(BookIssueDao issueBookDao, BookDao bookDao) {
        this.issueBookDao = issueBookDao;
        this.bookDao = bookDao;
    }

    @Override
    public boolean issueBook(IssueBook record) throws ServiceException {
        try {
            return issueBookDao.issueBook(record);
        } catch (DaoException e) {
            throw new ServiceException("Error issuing book", e);
        }
    }

    @Override
    public boolean returnBook(int issueId, LocalDate returnDate) throws ServiceException {
        try {
            return issueBookDao.returnBook(issueId, returnDate);
        } catch (DaoException e) {
            throw new ServiceException("Error returning book", e);
        }
    }

    @Override
    public List<IssueBook> getAllIssueRecords() throws ServiceException {
        try {
            return issueBookDao.getAllIssueRecords();
        } catch (DaoException e) {
            throw new ServiceException("Error fetching issue records", e);
        }
    }

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) throws ServiceException {
        try {
            return issueBookDao.getActiveIssuesByMember(memberId);
        } catch (DaoException e) {
            throw new ServiceException("Error fetching active issues for member", e);
        }
    }

    @Override
    public boolean isBookAlreadyIssued(String bookId) throws ServiceException {
        try {
            return issueBookDao.isBookAlreadyIssued(bookId);
        } catch (DaoException e) {
            throw new ServiceException("Error checking if book is already issued", e);
        }
    }

    @Override
    public Member getMemberByMobile(String mobile) throws InvalidInputException, ServiceException {
        try {
            return issueBookDao.getMemberByMobile(mobile);
        } catch (DaoException e) {
            throw new ServiceException("Error fetching member by mobile", e);
        }
    }

    @Override
    public List<Book> getAvailableBooksByCategory(BookCategory selectedCategory) throws ServiceException {
        try {
            return bookDao.getAvailableBooksByCategory(selectedCategory);
        } catch (DaoException e) {
            throw new ServiceException("Error fetching available books by category", e);
        }
    }

    @Override
    public List<Book> getAllAvailableBooks(BookCategory category) throws ServiceException {
        try {
            return bookDao.getAvailableBooksByCategory(category);
        } catch (DaoException e) {
            throw new ServiceException("Error fetching all available books", e);
        }
    }

    @Override
    public void updateBookAvailability(String bookId, char availability) throws ServiceException {
        try {
            bookDao.updateBookAvailability(bookId, availability);
        } catch (DaoException e) {
            throw new ServiceException("Error updating book availability", e);
        }
    }
}
