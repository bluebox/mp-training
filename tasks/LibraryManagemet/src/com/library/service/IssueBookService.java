package com.library.service;

import java.sql.SQLException;
import java.util.List;

import com.library.dao.IssueBookDAO;
import com.library.domain.IssueRecord;

public class IssueBookService {

    private IssueBookDAO dao = new IssueBookDAO();

    public boolean issueBook(IssueRecord record) throws SQLException {
        if (dao.isBookAvailable(record.getBookId())) {
            dao.issueBook(record);
            return true;
        }
        return false;
    }
    public boolean isBookIssuedToMember(int bookId, int memberId) throws SQLException {
        return dao.isBookIssuedToMember(bookId, memberId);
    }



    public void returnBook(int bookId, int memberId) throws SQLException {
        dao.returnBook(bookId, memberId);
    }

    public List<IssueRecord> getAllIssuedBooks() throws SQLException {
        return dao.getAllIssuedBooks();
    }
}
