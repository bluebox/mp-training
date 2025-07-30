
package com.lms.serviceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.lms.daoImpl.BookDao;
import com.lms.daoImpl.IssueBookDaoImpl;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.IssueBook;
import com.lms.model.Member;
import com.lms.service.IssueBookServiceInterface;

public class IssueBookServiceImpl implements IssueBookServiceInterface {

    private final IssueBookDaoImpl issueBookDao;

    public IssueBookServiceImpl() {
        this.issueBookDao = new IssueBookDaoImpl();
    }

    public boolean issueBook(IssueBook record) throws SQLException {
        return issueBookDao.issueBook(record);
    }

    public boolean returnBook(int issueId, LocalDate returnDate) throws SQLException {
        return issueBookDao.returnBook(issueId, returnDate);
    }

    public List<IssueBook> getAllIssueRecords() throws SQLException {
        return issueBookDao.getAllIssueRecords();
    }

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) throws SQLException {
        return issueBookDao.getActiveIssuesByMember(memberId);
    }

    @Override
    public boolean isBookAlreadyIssued(String bookId) throws SQLException {
        return issueBookDao.isBookAlreadyIssued(bookId);
    }
    public Member getMemberByMobile(String mobile) throws InvalidInputException, SQLException {
    		return issueBookDao.getMemberByMobile(mobile);
    }
    public List<Book> getAvailableBooksByCategory(BookCategory selectedCategory) throws SQLException{
    return BookDao.getAvailableBooksByCategory(selectedCategory);
    }

    public List<Book> getAllAvailableBooks(BookCategory category) throws SQLException {
        return BookDao.getAvailableBooksByCategory(category);
    }
    public void updateBookAvailability(String bookId, char availability) throws SQLException {
        BookDao.updateBookAvailability(bookId, availability);
    }


}