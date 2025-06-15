package service;


import dao.BookDAO;
import dao.IssueDAO;
import javafx.util.Callback;
import model.Book;
import model.IssueRecord;
public class IssueService {
    private final IssueDAO issueDAO = new IssueDAO();
    private final BookDAO bookDAO = new BookDAO(); // to fetch book status

    public void issueBook(IssueRecord issue) throws Exception {
        if (issue.getBookId() <= 0) throw new IllegalArgumentException("Invalid Book ID");
        if (issue.getMemberId() <= 0) throw new IllegalArgumentException("Invalid Member ID");
        if (issue.getStatus() != 'I') throw new IllegalArgumentException("Issue status must be 'A'");

        // Step 1: Check book availability
        Book book = bookDAO.getBookById(issue.getBookId());
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        if (book.getAvailability() != 'A') {
            throw new IllegalStateException("Book is already issued.");
        }

        // Step 2: Proceed with issuing the book (inserts + updates inside DAO)
        issueDAO.issueBook(issue);
    }

    public void returnBook(int issueId, int bookId, java.time.LocalDate returnDate) throws Exception {
        if (issueId <= 0) throw new IllegalArgumentException("Invalid Issue ID");
        if (bookId <= 0) throw new IllegalArgumentException("Invalid Book ID");
        if (returnDate == null) throw new IllegalArgumentException("Return date is required");
        issueDAO.returnBook(issueId, bookId, returnDate);
    }
}




