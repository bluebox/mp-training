package service;

import java.sql.Connection;
import java.util.List;

import dao.IssueRecordDao;
import dao.MemberManagementDao;
import dao.BookDao;
import domain.Book;
import domain.IssueRecord;

public class IssueService {
    private final IssueRecordDao issueRecordDAO;
    private final MemberManagementDao memberDAO;
    private final BookDao bookDAO;

    public IssueService(Connection connection) {
        this.issueRecordDAO = new IssueRecordDao(connection);
        this.memberDAO = new MemberManagementDao(); 
        this.bookDAO = new BookDao();               
    }

    public String issueBookToMember(int bookId, int memberId, String issuedBy) {
        try {
        	Book book = new Book();
        	book.setBookId(bookId);
            if (!bookDAO.bookExists(book)) {
                return "Error: Book ID " + bookId + " does not exist.";
            }

            if (!memberDAO.memberExists(memberId)) {
                return "Error: Member ID " + memberId + " does not exist.";
            }

            return issueRecordDAO.issueBook(bookId, memberId, issuedBy);

        } catch (Exception e) {
            e.printStackTrace();
            return "Error while issuing book: " + e.getMessage();
        }
    }
    public String returnBookFromMember(int bookId, int memberId) {
        return issueRecordDAO.returnBook(bookId, memberId);
    }

    public boolean isBookAlreadyIssued(int bookId) {
        return issueRecordDAO.isBookAlreadyIssued(bookId);
    }

    public String canBookBeIssued(int bookId) {
        return issueRecordDAO.canBookBeIssued(bookId);
    }

    public List<IssueRecord> getAllIssueRecords() {
        return issueRecordDAO.getAllIssueRecords();
    }
}