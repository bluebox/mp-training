package com.LibraryManagement;

import java.sql.Connection;
import java.util.List;

import com.LibraryManagement.dao.*;

import com.LibraryManagement.model.*;
import com.LibraryManagement.service.*;
import com.LibraryManagement.util.DBConnection;

public class App 
{
    public static void main( String[] args )
    {
    	try (Connection conn = DBConnection.getConnection()) {

            // Initialize DAO
            MemberDAO memberDAO = new MemberDAOImpl(conn);
            BookDAO bookDAO = new BookDAOImpl(conn);
            IssueRecordDAO issueRecordDAO = new IssueRecordDAOImpl(conn);

            // Initialize Services
            MemberService memberService = new MemberServiceImpl(memberDAO);
            BookService bookService = new BookServiceImpl(bookDAO);
            IssueRecordService issueService = new IssueRecordServiceImpl(issueRecordDAO, bookDAO);

            // Add a new member
            Member m = new Member();
            m.setName("John Doe");
            m.setEmail("john@example.com");
            m.setMobile("9876543210");
            m.setGender('M');
            m.setAddress("New York");
            memberService.addMember(m);
            System.out.println("Member added successfully!");

            // Add a new book
            Book b = new Book();
            b.setTitle("Clean Code");
            b.setAuthor("Robert C. Martin");
            b.setCategory("Programming");
            b.setStatus('A');
            b.setAvailability('A');
            bookService.addBook(b);
            System.out.println("Book added successfully!");

            // Issue a book
            issueService.issueBook(1, 1); // Use real bookId and memberId
            System.out.println("Book issued!");

            // Return a book
            issueService.returnBook(1, 1); // Use real bookId and memberId
            System.out.println("Book returned!");

            // Fetch all members
            List<Member> members = memberService.getAllMembers();
            members.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
