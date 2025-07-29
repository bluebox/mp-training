
package com.lms.serviceImpl;

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

    public boolean issueBook(IssueBook record) {
        return issueBookDao.issueBook(record);
    }

    public boolean returnBook(int issueId, LocalDate returnDate) {
        return issueBookDao.returnBook(issueId, returnDate);
    }

    public List<IssueBook> getAllIssueRecords() {
        return issueBookDao.getAllIssueRecords();
    }

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) {
        return issueBookDao.getActiveIssuesByMember(memberId);
    }

    @Override
    public boolean isBookAlreadyIssued(String bookId) {
        return issueBookDao.isBookAlreadyIssued(bookId);
    }
    public Member getMemberByMobile(String mobile) throws InvalidInputException {
    		return issueBookDao.getMemberByMobile(mobile);
    }
    public List<Book> getAvailableBooksByCategory(BookCategory selectedCategory){
    return BookDao.getInstance().getAvailableBooksByCategory(selectedCategory);
    }

    public List<Book> getAllAvailableBooks(BookCategory category) {
        return BookDao.getInstance().getAvailableBooksByCategory(category);
    }
    public void updateBookAvailability(String bookId, char availability) {
        BookDao.getInstance().updateBookAvailability(bookId, availability);
    }


}