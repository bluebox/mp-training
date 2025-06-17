package service;


import dao.BookDAO;
import dao.IssueDAO;
import javafx.util.Callback;
import model.Book;
import model.IssueRecord;
import Exception.BookAlreadyIssuedException;
public class IssueService {
    private final IssueDAO issueDAO = new IssueDAO();
    private final BookDAO bookDAO = new BookDAO(); 

    public void issueBook(IssueRecord issue) throws Exception {
        if (issue.getBookId() <= 0) throw new IllegalArgumentException("Invalid Book ID");
        if (issue.getMemberId() <= 0) throw new IllegalArgumentException("Invalid Member ID");
        if (issue.getStatus() != 'I') throw new IllegalArgumentException("Issue status must be 'A'");

        Book book = bookDAO.getBookById(issue.getBookId());
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        if (book.getAvailability() != 'A') {
            throw new BookAlreadyIssuedException("Book is already issued.");
        }

        issueDAO.issueBook(issue);
    }

    public void returnBook(int issueId, int bookId, java.time.LocalDate returnDate) throws Exception {
        if (issueId <= 0) throw new IllegalArgumentException("Invalid Issue ID");
        if (bookId <= 0) throw new IllegalArgumentException("Invalid Book ID");
        if (returnDate == null) throw new IllegalArgumentException("Return date is required");
        issueDAO.returnBook(issueId, bookId, returnDate);
    }

    public java.util.List<model.IssueRecord> getAllIssuedRecords() throws Exception {
        return issueDAO.getAllIssuedRecords();
    }
}




