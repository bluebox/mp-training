package serviceimpl;


import model.Book;
import model.IssueRecord;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Exception.BookAlreadyIssuedException;
import daoimpl.BookDAOImpl;
import daoimpl.IssueDAOImpl;
import service.IssueService;
public class IssueServiceImpl implements IssueService {
    private final IssueDAOImpl issueDAO = new IssueDAOImpl();
    private final BookDAOImpl bookDAO = new BookDAOImpl(); 

    public void issueBook(IssueRecord issue) throws Exception {
		if (issue.getBookId() < 0) {
			throw new IllegalArgumentException("Invalid Book ID");
		}
		if (issue.getMemberId() < 0) {
			throw new IllegalArgumentException("Invalid Member ID");
		}
		if (issue.getStatus() != 'I') {
			throw new IllegalArgumentException("Issue status must be 'A'");
		}

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
		if (issueId < 0) {
			throw new IllegalArgumentException("Invalid Issue ID");
		}
		if (bookId < 0) {
			throw new IllegalArgumentException("Invalid Book ID");
		}
		if (returnDate == null) {
			throw new IllegalArgumentException("Return date is required");
		}
        issueDAO.returnBook(issueId, bookId, returnDate);
    }

    public java.util.List<model.IssueRecord> getAllIssuedRecords() throws Exception {
        return issueDAO.getAllIssuedRecords();
    }
    
    public List<IssueRecord> getOverdueRecords() throws Exception {
        List<IssueRecord> issueRecords = issueDAO.getAllIssuedRecords();
        List<IssueRecord> overdueBooks = issueRecords.stream()
        	    .filter(record -> record.getStatus() == 'I')
        	    .filter(record -> record.getReturnDate() == null)
        	    .filter(record -> record.getIssueDate().isBefore(LocalDate.now().minusDays(14)))
        	    .sorted(Comparator.comparing(IssueRecord::getIssueDate))
        	    .collect(Collectors.toList());
        return overdueBooks;
        
    }
}




