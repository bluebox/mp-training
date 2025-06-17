package com.medplus.service;
import com.medplus.dao.impl.BookDAOImpl;
import com.medplus.dao.impl.MemberDAOImpl;
import com.medplus.dao.impl.IssueRecordDAOImpl;
import com.medplus.model.Book;
import com.medplus.model.IssueRecord;
import com.medplus.model.Member;

//import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {
    private BookDAOImpl bookDAO = new BookDAOImpl();
    private MemberDAOImpl memberDAO = new MemberDAOImpl();
    private IssueRecordDAOImpl issueDAO = new IssueRecordDAOImpl();

    public void addBook(Book book) throws Exception {
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) throws Exception {
        bookDAO.updateBook(book);
    }

    public void updateAvailability(int bookId, char availability) throws Exception {
        bookDAO.updateAvailability(bookId, availability);
    }

    public List<Book> getAllBooks() throws Exception {
        return bookDAO.getAllBooks();
    }

    public void registerMember(Member member) throws Exception {
        memberDAO.addMember(member);
    }

    public void updateMember(Member member) throws Exception {
        memberDAO.updateMember(member);
    }

    public List<Member> getAllMembers() throws Exception {
        return memberDAO.getAllMembers();
    }

    public void issueBook(int bookId, int memberId) throws Exception {
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found with ID: " + bookId);
        }
        if (book.getAvailability() == 'I') {
            throw new IllegalStateException("Book is already issued.");
        }
        
        IssueRecord newIssue = new IssueRecord(bookId, memberId, 'I', LocalDate.now());
        issueDAO.issueBook(newIssue);
    }

    public void returnBook(int issueId) throws Exception {
        IssueRecord issue = issueDAO.getIssueById(issueId);
        if (issue == null) {
            throw new IllegalArgumentException("Issue record not found with ID: " + issueId);
        }
        if (issue.getStatus() == 'R') {
            throw new IllegalStateException("Book already returned for this issue record.");
        }
        
        issueDAO.returnBook(issueId);
        bookDAO.updateAvailability(issue.getBookId(), 'A');
    }

    public List<IssueRecord> getAllIssues() throws Exception {
        return issueDAO.getAllIssuedBooks();
    }

    public List<IssueRecord> getOverdueBooks() throws Exception {
        return getAllIssues().stream()
                .filter(record -> record.getStatus() == 'I' && record.getIssueDate().plusDays(15).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
    }

    public long getBooksCountByCategory(String category) throws Exception {
        return getAllBooks().stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .count();
    }

    public List<Member> getMembersWithActiveIssues() throws Exception {
        List<IssueRecord> activeIssues = getAllIssues().stream()
                .filter(i -> i.getStatus() == 'I')
                .collect(Collectors.toList());

        return getAllMembers().stream()
                .filter(member -> activeIssues.stream().anyMatch(i -> i.getMemberId() == member.getMemberId()))
                .collect(Collectors.toList());
    }
}