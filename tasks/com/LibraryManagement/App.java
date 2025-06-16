package com.LibraryManagement;

import com.LibraryManagement.ui.AddBookUI;
import com.LibraryManagement.ui.AddMemberUI;
import com.LibraryManagement.ui.IssueBookUI;
import com.LibraryManagement.ui.ReturnBookUI;
import com.LibraryManagement.ui.ViewBooksUI;
import com.LibraryManagement.ui.ViewMembersUI;

import com.LibraryManagement.ui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button addBookBtn = new Button("Add Book");
        Button addMemberBtn = new Button("Add Member");
        Button issueBookBtn = new Button("Issue Book");
        Button returnBookBtn = new Button("Return Book");
        Button viewBooksBtn = new Button("View Books");
        Button viewMembersBtn = new Button("View Members");

        // Launch respective UIs
        addBookBtn.setOnAction(e -> new AddBookUI().start(new Stage()));
        addMemberBtn.setOnAction(e -> new AddMemberUI().start(new Stage()));
        issueBookBtn.setOnAction(e -> new IssueBookUI().start(new Stage()));
        returnBookBtn.setOnAction(e -> new ReturnBookUI().start(new Stage()));
        viewBooksBtn.setOnAction(e -> new ViewBooksUI().start(new Stage()));
        viewMembersBtn.setOnAction(e -> new ViewMembersUI().start(new Stage()));

        VBox root = new VBox(10, addBookBtn, addMemberBtn, issueBookBtn, returnBookBtn, viewBooksBtn, viewMembersBtn);
        root.setPadding(new javafx.geometry.Insets(20));

        primaryStage.setTitle("Library Management System");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//
//import java.sql.Connection;
//import java.util.List;
//
//import com.LibraryManagement.dao.*;
//
//import com.LibraryManagement.model.*;
//import com.LibraryManagement.service.*;
//import com.LibraryManagement.util.DBConnection;
//
//public class App 
//{
//    public static void main( String[] args )
//    {
//    	try (Connection conn = DBConnection.getConnection()) {
//
//            // Initialize DAO
//            MemberDAO memberDAO = new MemberDAOImpl(conn);
//            BookDAO bookDAO = new BookDAOImpl(conn);
//            IssueRecordDAO issueRecordDAO = new IssueRecordDAOImpl(conn);
//
//            // Initialize Services
//            MemberService memberService = new MemberServiceImpl(memberDAO);
//            BookService bookService = new BookServiceImpl(bookDAO);
//            IssueRecordService issueService = new IssueRecordServiceImpl(issueRecordDAO, bookDAO);
//
//            // Add a new member
//            Member m = new Member();
//            m.setName("John Doe");
//            m.setEmail("john@example.com");
//            m.setMobile("9876543210");
//            m.setGender('M');
//            m.setAddress("New York");
//            memberService.addMember(m);
//            System.out.println("Member added successfully!");
//
//            // Add a new book
//            Book b = new Book();
//            b.setTitle("Clean Code");
//            b.setAuthor("Robert C. Martin");
//            b.setCategory("Programming");
//            b.setStatus('A');
//            b.setAvailability('A');
//            bookService.addBook(b);
//            System.out.println("Book added successfully!");
//
//            // Issue a book
//            issueService.issueBook(1, 1); // Use real bookId and memberId
//            System.out.println("Book issued!");
//
//            // Return a book
//            issueService.returnBook(1, 1); // Use real bookId and memberId
//            System.out.println("Book returned!");
//
//            // Fetch all members
//            List<Member> members = memberService.getAllMembers();
//            members.forEach(System.out::println);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
