
package com.casestudy.fx;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.casestudy.domain.Book;
import com.casestudy.domain.IssueRecord;
import com.casestudy.domain.Member;
import com.casestudy.serviceimpl.Service;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainMenu {
    private BorderPane root;
    private HBox navigationBar;
    private VBox contentArea;

    public MainMenu() {
        root = new BorderPane();
        navigationBar = new HBox(10);
        contentArea = new VBox(10);
        
        showMainMenu();
        
        setupNavigation();
        
        setupLayout();
       
    }

    private void setupNavigation() {
    	
    	
        Button booksBtn = new Button("Books");
        Button membersBtn = new Button("Members");
        
        Button homeBtn = new Button("Main Menu");
        homeBtn.setOnAction(e -> {
        	showMainMenu();
        });
        
        Button viewReports = new Button("View Reports");
        
        viewReports.setOnAction(e -> {
            contentArea.getChildren().clear();

            Button overdueBooks = new Button("List of Overdue Books");
            Button booksPerCategory = new Button("Count of Books per Category");
            Button activeIssuedMembers = new Button("List of Members with Active Issued Books");

            List<Button> reportButtons = Arrays.asList(overdueBooks, booksPerCategory, activeIssuedMembers);
            reportButtons.forEach(btn -> btn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;"));

            overdueBooks.setOnAction(ev -> {
                contentArea.getChildren().clear();
                Service service = new Service();
                List<IssueRecord> getOverdueBooks = service.getOverdueBooks();
                contentArea.getChildren().add(new OverdueBooksReport(getOverdueBooks));
            });

            booksPerCategory.setOnAction(ev -> {
                contentArea.getChildren().clear();
                Service service = new Service();
                Map<String, Long> getBooksCountPerCategory = service.getBooksCountPerCategory();
                contentArea.getChildren().add(new BooksPerCategoryReport(getBooksCountPerCategory));
            });

            activeIssuedMembers.setOnAction(ev -> {
                contentArea.getChildren().clear();
                Service service = new Service();
                List<IssueRecord> getActiveIssuedBooks = service.getActiveIssuedBooksSerivce();
                contentArea.getChildren().add(new ActiveIssuedMembersReport(getActiveIssuedBooks));
            });

            VBox reportLayout = new VBox(10, overdueBooks, booksPerCategory, activeIssuedMembers);
            reportLayout.setPadding(new Insets(20));
            contentArea.getChildren().add(reportLayout);
        });

        List<Button> navButtons = Arrays.asList(booksBtn , homeBtn , membersBtn , viewReports);
        navButtons.forEach(btn -> btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;"));
        
        booksBtn.setOnAction(e -> {
            showBookActions();
        });

        membersBtn.setOnAction(e -> {
            showMemberActions();
        });
        
        showMainMenu();

        navigationBar.getChildren().addAll(homeBtn , booksBtn, membersBtn , viewReports );
        navigationBar.setAlignment(Pos.CENTER);
        navigationBar.setPadding(new Insets(15));
    }
    
    private void showMainMenu() {
        contentArea.getChildren().clear();

        Text welcomeText = new Text("Welcome to the Library Management System");
        welcomeText.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        VBox mainLayout = new VBox(10); // Increased spacing for cleaner layout
        mainLayout.setPadding(new Insets(10));
        mainLayout.setAlignment(Pos.TOP_LEFT);

        // Add Search and Issue Form
        SearchAndIssueBookForm searchForm = new SearchAndIssueBookForm();

        // Record Action Buttons
        Button issueBook = new Button("Issue Book");
        Button returnBook = new Button("Return Book");
        Button viewRecords = new Button("View All Issued Records");

        // Apply consistent styling (optional)
        List<Button> allButtons = Arrays.asList(issueBook, returnBook, viewRecords);
        allButtons.forEach(btn -> btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;"));

        issueBook.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(new IssueBookForm());
        });

        returnBook.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(new ReturnBookForm());
        });   
        
        viewRecords.setOnAction(e -> {
            contentArea.getChildren().clear();
            Service service = new Service();
            List<IssueRecord> getAllIssuedRecords = service.getAllIssuedRecordsService();
            contentArea.getChildren().add(new ViewAllRecords(getAllIssuedRecords));  
        }); 
        

        HBox recordButtons = new HBox(10, issueBook, returnBook, viewRecords);
        recordButtons.setAlignment(Pos.TOP_LEFT);

        mainLayout.getChildren().addAll(welcomeText, searchForm, recordButtons);
        contentArea.getChildren().add(mainLayout);
    }

    private void setupLayout() {
        root.setTop(navigationBar);
        contentArea.setAlignment(Pos.TOP_CENTER);
        contentArea.setPadding(new Insets(20));
        root.setCenter(contentArea);
    }

    private void showBookActions() {
        contentArea.getChildren().clear();

        Button addBook = new Button("Add Book");
        
        addBook.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(new AddBookForm());
        });
        
        Button updateBook = new Button("Update Book");    
        Button viewBooks = new Button("View All Books");
        
        List<Button> bookButtons = Arrays.asList(addBook , updateBook , viewBooks);
        bookButtons.forEach(btn -> btn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;"));
        updateBook.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(new UpdateBookForm());
        });

        viewBooks.setOnAction(e -> {
            contentArea.getChildren().clear();
            Service service = new Service();
            List<Book> books =  service.viewAllBooksService();
            contentArea.getChildren().add(new ViewAllBooks(books));
        });

        contentArea.getChildren().addAll(addBook, updateBook, viewBooks);
       

    }

    private void showMemberActions() {
        contentArea.getChildren().clear();

        Button addMember = new Button("Add Member");
        
        addMember.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(new AddMemberForm());
        });
        
        Button updateMember = new Button("Update Member");
        Button viewMembers = new Button("View All Members");
        
        List<Button> memberButtons = Arrays.asList(addMember , updateMember , viewMembers);
        memberButtons.forEach(btn -> btn.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;"));
        
        updateMember.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(new UpdateMemberForm());
        });

        viewMembers.setOnAction(e -> {
            contentArea.getChildren().clear();
            
            Service service = new Service();
            List<Member> members = service.getAllMembersService();
            contentArea.getChildren().add(new ViewAllMembers(members));
        });

        contentArea.getChildren().addAll(addMember, updateMember, viewMembers);
    }


    public BorderPane getRoot() {
        return root;
    }
}

