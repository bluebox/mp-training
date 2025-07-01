package com.library.app.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.library.app.dao.*;
import com.library.app.model.*;

@Service
public class LibraryService {
    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;
    private final IssuedRecordDAO issueDAO;

    public LibraryService() throws SQLException {
        bookDAO = new BookDAO();
        memberDAO = new MemberDAO();
        issueDAO = new IssuedRecordDAO();
    }

    // Book Services
    public void addBook(Book book) throws Exception {
        validateBook(book);
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) throws Exception {
        if (book.getBookId() <= 0)
            throw new Exception("Invalid Book ID");
        validateBook(book);
        bookDAO.updateBookDetails(book);
    }

    public void updateBookAvailability(int bookId, Availability availability) throws Exception {
        if (availability == null)
            throw new Exception("Invalid availability status");
        bookDAO.updateAvailability(bookId, availability);
    }

    // Member Services
    public void addMember(Member member) throws Exception {
        validateMember(member);
        memberDAO.addMember(member);
    }

    public void updateMember(Member member) throws Exception {
        if (member.getMemberId() <= 0)
            throw new Exception("Invalid Member ID");
        validateMember(member);
        memberDAO.updateMember(member);
    }

    // Issue Services
    public void issueBook(int bookId, int memberId) throws Exception {
        Book book = bookDAO.getBookById(bookId);
        if (book == null || book.getAvailability() == Availability.ISSUED)
            throw new Exception("Book is not available for issue.");

        Member member = memberDAO.getMemberById(memberId);
        if (member == null)
            throw new Exception("Member not found.");

        issueDAO.issueBook(bookId, memberId);
        bookDAO.updateAvailability(bookId, Availability.ISSUED);
    }

    public void returnBook(int issueId) throws Exception {
        IssueRecord record = issueDAO.getIssueById(issueId);
        if (record == null || record.getStatus() == 'R')
            throw new Exception("Invalid or already returned record.");

        issueDAO.returnBook(issueId);
        bookDAO.updateAvailability(record.getBookId(), Availability.AVAILABLE);
    }

    // View & Stats
    public List<Book> viewAllBooks() throws Exception {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) throw new Exception("No books found");
        return books;
    }

    public List<Member> viewAllMembers() throws Exception {
        List<Member> members = memberDAO.getAllMembers();
        if (members.isEmpty()) throw new Exception("No members found");
        return members;
    }

    public List<IssueRecord> viewIssuedRecords() throws Exception {
        List<IssueRecord> records = issueDAO.getAllIssuedRecords();
        if (records.isEmpty()) throw new Exception("No records found");
        return records;
    }

    public List<IssueRecord> getOverdueBooks() throws SQLException {
        return issueDAO.getAllIssuedRecords().stream()
            .filter(r -> r.getStatus() == 'I' && r.getReturnDate().isBefore(LocalDate.now()))
            .collect(Collectors.toList());
    }

    public Map<String, Long> countBooksByCategory() throws SQLException {
        return bookDAO.getAllBooks().stream()
            .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
    }

    public List<Member> getMembersWithIssuedBooks() throws SQLException {
        Set<Integer> issuedIds = issueDAO.getAllIssuedRecords().stream()
            .filter(r -> r.getStatus() == 'I')
            .map(IssueRecord::getMemberId)
            .collect(Collectors.toSet());

        return memberDAO.getAllMembers().stream()
            .filter(m -> issuedIds.contains(m.getMemberId()))
            .collect(Collectors.toList());
    }

    // Validation
    private void validateBook(Book book) throws Exception {
        if (book.getTitle() == null || book.getTitle().trim().isEmpty())
            throw new Exception("Book title is required");
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty())
            throw new Exception("Book author is required");
        if (book.getCategory() == null || book.getCategory().trim().isEmpty())
            throw new Exception("Book category is required");
        if (book.getStatus() == null)
            throw new Exception("Invalid book status");
        if (book.getAvailability() == null)
            throw new Exception("Invalid book availability");
    }

    private void validateMember(Member member) throws Exception {
        if (member.getName() == null || member.getName().trim().isEmpty())
            throw new Exception("Member name is required");
        if (member.getEmail() == null || member.getEmail().trim().isEmpty())
            throw new Exception("Member email is required");
        if (!member.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[A-Za-z]{2,6}$"))
            throw new Exception("Invalid email format");
        if (member.getMobile() <= 0)
            throw new Exception("Member mobile number is required");
        if (!String.valueOf(member.getMobile()).matches("^[6-9][0-9]{9}$"))
            throw new Exception("Invalid mobile number");
        if (member.getGender() == null)
            throw new Exception("Invalid gender");
        if (member.getAddress() == null || member.getAddress().trim().isEmpty())
            throw new Exception("Address is required");
    }
}



