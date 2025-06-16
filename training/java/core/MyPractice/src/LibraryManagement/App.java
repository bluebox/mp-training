package com.LibraryManagement;

import java.sql.Connection;
import java.util.List;

import com.LibraryManagement.dao.BookDAO;
import com.LibraryManagement.dao.BookDAOImpl;
import com.LibraryManagement.dao.IssueRecordDAO;
import com.LibraryManagement.dao.IssueRecordDAOImpl;
import com.LibraryManagement.dao.MemberDAO;
import com.LibraryManagement.dao.MemberDAOImpl;
import com.LibraryManagement.model.Book;
import com.LibraryManagement.model.Member;
import com.LibraryManagement.service.BookService;
import com.LibraryManagement.service.BookServiceImpl;
import com.LibraryManagement.service.IssueRecordService;
import com.LibraryManagement.service.IssueRecordServiceImpl;
import com.LibraryManagement.service.MemberService;
import com.LibraryManagement.service.MemberServiceImpl;
import com.LibraryManagement.util.DBConnection;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {

            // Initialize DAO
            MemberDAO memberDAO = new MemberDAOImpl(conn);
            BookDAO bookDAO = new BookDAOImpl(conn);
            IssueRecordDAO issueRecordDAO = new IssueRecordDAOImpl(conn);

            // Initialize Services
            MemberService memberService = new MemberServiceImpl(memberDAO);
            BookService bookService = new BookServiceImpl(bookDAO);
            IssueRecordService issueService = new IssueRecordServiceImpl(issueRecordDAO, bookDAO, memberDAO);
            // Display all members
            System.out.println("----- List of Members -----");
            List<Member> members = memberService.getAllMembers();
            for (Member m : members) {
                System.out.println(m);
            }

            // Display all books
            System.out.println("\n----- List of Books -----");
            List<Book> books = bookService.getAllBooks();
            for (Book b : books) {
                System.out.println(b);
            }

            // issue book
            // return book
            // === Issue a Book ===
            int bookIdToIssue = 17; // Update with a valid book ID from the list
            int memberIdToIssue = 20225; // Update with a valid member ID from the list

            try {
                issueService.issueBook(bookIdToIssue, memberIdToIssue);

                System.out.println("\n Book with ID " + bookIdToIssue + " issued to member ID " + memberIdToIssue);
            } catch (Exception e) {
                System.out.println("\n Failed to issue book: " + e.getMessage());
            }

            System.out.println("\n----- All Issue Records -----");
            issueService.getAllIssueRecords().forEach(System.out::println);
            // === Return the Book ===
            try {
                issueService.returnBook(bookIdToIssue, memberIdToIssue);
                System.out.println("Book with ID " + bookIdToIssue + " returned by member ID " + memberIdToIssue);
            } catch (Exception e) {
                System.out.println("\nFailed to return book: " + e.getMessage());
            }

            // === Display All Issue Records ===
            System.out.println("\n----- All Issue Records -----");
            issueService.getAllIssueRecords().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
