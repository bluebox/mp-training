package Service;
import casestudy.IssueRecord; import casestudy.LibraryException;


import java.util.List; import java.util.stream.Collectors;
import java.util.Optional;
import java.time.LocalDate;

import DAO.IssueDAO;


public class IssueService { 
	
	private final IssueDAO issueDAO = new IssueDAO();
	private final BookService bookService = new BookService();
	
	public void issueBook(IssueRecord issue) throws LibraryException {
    if (issue.getBookId() <= 0 || issue.getMemberId() <= 0) {
        throw new LibraryException("Invalid book or member ID");
    }

    // Check if book is active
    if (!bookService.isBookActive(issue.getBookId())) {
        throw new LibraryException("Cannot issue book: Book is not active");
    }

    // Check if book is already issued
    boolean isAlreadyIssued = issueDAO.getAllIssues().stream()
        .anyMatch(i -> i.getBookId() == issue.getBookId() && i.getStatus() == 'I');
    
    if (isAlreadyIssued) {
        throw new LibraryException("Cannot issue book: Book is already issued");
    }

    issueDAO.issueBook(issue);
}

public void returnBookByBookId(int bookId) throws LibraryException {
    if (bookId <= 0) {
        throw new LibraryException("Invalid book ID");
    }
    
    // Find the latest active issue for this book
    Optional<IssueRecord> latestIssue = issueDAO.getAllIssues().stream()
        .filter(issue -> issue.getBookId() == bookId && issue.getStatus() == 'I')
        .max((i1, i2) -> i1.getIssueDate().compareTo(i2.getIssueDate()));
        
    if (latestIssue.isPresent()) {
        issueDAO.returnBook(latestIssue.get().getIssueId());
    } else {
        throw new LibraryException("No active issue found for book ID: " + bookId);
    }
}

public void returnBook(int issueId) throws LibraryException {
    if (issueId <= 0) {
        throw new LibraryException("Invalid issue ID");
    }
    issueDAO.returnBook(issueId);
}

public List<IssueRecord> getOverdueBooks() throws LibraryException {
    return issueDAO.getOverdueBooks();
}

public List<IssueRecord> getActiveIssuesByMember(int memberId) throws LibraryException {
    return issueDAO.getAllIssues().stream()
            .filter(issue -> issue.getMemberId() == memberId && issue.getStatus() == 'I')
            .collect(Collectors.toList());
}

public List<IssueRecord> getAllIssues() throws LibraryException {
    return issueDAO.getAllIssues();
}}