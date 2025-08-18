package service;

import model.Book;
import model.BookAvailability;
import model.Issuerecords;
import model.IssueStatus;
import model.Member;
import org.springframework.stereotype.Service;
import repository.BookRepo;
import repository.IssuerecordsRepo;
import repository.MemberRepository;
import service.ServiceLayerInterface;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServiceLayer implements ServiceLayerInterface {

    private final BookRepo bookRepo;
    private final MemberRepository memberRepo;
    private final IssuerecordsRepo issuerecordsRepo;

    public ServiceLayer(BookRepo bookRepo, MemberRepository memberRepo, IssuerecordsRepo issuerecordsRepo) {
        this.bookRepo = bookRepo;
        this.memberRepo = memberRepo;
        this.issuerecordsRepo = issuerecordsRepo;
    }

   

    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public Book getBookById(int bookId) {
        return bookRepo.getBookById(bookId);
    }

    public int addBook(Book book) {
        return bookRepo.addBook(book);
    }

    public int updateBook(Book book) {
        return bookRepo.updateBook(book);
    }

    public int deleteBook(int bookId) {
        return bookRepo.deleteBook(bookId);
    }

    public int updateBookAvailability(int bookId, BookAvailability availability) {
        return bookRepo.updateAvailability(bookId, availability);
    }

 

    public int addMember(Member member) {
        return memberRepo.addMember(member);
    }

    public Member updateMember(Member member) throws Exception {
        return memberRepo.updateMember(member);
    }

    public List<Member> getAllMembers() {
        return memberRepo.getAllMembers();
    }

    public Member getMemberById(int memberId) {
        return memberRepo.getMemberById(memberId);
    }

    

    public int issueBook(int bookId, int memberId, IssueStatus status, LocalDate issueDate, LocalDate returnDate) {
        return issuerecordsRepo.createBookIssue(bookId, memberId, status, issueDate, returnDate);
    }

    public int returnBook(int bookId, int memberId) {
        return issuerecordsRepo.returnBook(bookId, memberId);
    }

    public boolean checkBookIssued(int bookId, int memberId) {
        return issuerecordsRepo.checkBookIssue(bookId, memberId);
    }

    public List<Issuerecords> getAllIssueRecords() {
        return issuerecordsRepo.getAllIssueRecords();
    }

    public Issuerecords getIssueRecord(int bookId, int memberId) {
        return issuerecordsRepo.getIssueRecord(bookId, memberId);
    }

// REPORTS 

    public List<Book> getOverdueBooks() {
        LocalDate today = LocalDate.now();

        return issuerecordsRepo.getAllIssueRecords().stream()
                .filter(record -> record.getStatus() == IssueStatus.ISSUED)
                .filter(record -> record.getIssueDate().plusDays(14).isBefore(today))
                .map(record -> bookRepo.getBookById(record.getBookId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Map<String, Long> getBookCountByCategory() {
        List<Book> books = bookRepo.getAllBooks();

        return books.stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
    }

    public List<Member> getMembersWithActiveIssuedBooks() {
        List<Issuerecords> records = issuerecordsRepo.getAllIssueRecords();

        Set<Integer> memberIdsWithIssuedBooks = records.stream()
                .filter(r -> r.getStatus() == IssueStatus.ISSUED)
                .map(Issuerecords::getMemberId)
                .collect(Collectors.toSet());

        List<Member> allMembers = memberRepo.getAllMembers();

        return allMembers.stream()
                .filter(m -> memberIdsWithIssuedBooks.contains(m.getId()))
                .collect(Collectors.toList());
    }
}
