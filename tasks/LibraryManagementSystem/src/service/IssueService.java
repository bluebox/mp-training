package service;


import dao.BookDAO;
import dao.IssueDAO;
import javafx.util.Callback;
import model.Book;
import model.IssueRecord;
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
            throw new IllegalStateException("Book is already issued.");
        }

        issueDAO.issueBook(issue);
    }
}




