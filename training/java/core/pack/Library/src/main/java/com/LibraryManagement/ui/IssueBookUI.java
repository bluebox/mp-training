package Library.src.main.java.com.LibraryManagement.ui;

import Library.src.main.java.com.LibraryManagement.service.IssueRecordService;
import Library.src.main.java.com.LibraryManagement.service.IssueRecordServiceImpl;
import Library.src.main.java.com.LibraryManagement.dao.IssueRecordDAOImpl;

import Library.src.main.java.com.LibraryManagement.dao.BookDAOImpl;

import Library.src.main.java.com.LibraryManagement.util.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class IssueBookUI {
    private final IssueRecordService issueService;

  
    
    public IssueBookUI() {
        try {
            Connection conn = DBConnection.getConnection();
            //this.issueService = new IssueRecordServiceImpl(new IssueRecordDAOImpl(conn));
           this.issueService= new IssueRecordServiceImpl(new IssueRecordDAOImpl(conn),new BookDAOImpl(conn));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize service: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setVgap(8);
        pane.setHgap(10);

        TextField bookIdField = new TextField();
        TextField memberIdField = new TextField();
        Button issueButton = new Button("Issue Book");
        Label messageLabel = new Label();

        pane.add(new Label("Book ID:"), 0, 0);
        pane.add(bookIdField, 1, 0);
        pane.add(new Label("Member ID:"), 0, 1);
        pane.add(memberIdField, 1, 1);
        pane.add(issueButton, 1, 2);
        pane.add(messageLabel, 1, 3);

        issueButton.setOnAction(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText());
                int memberId = Integer.parseInt(memberIdField.getText());
                issueService.issueBook(bookId, memberId);
                messageLabel.setText("Book issued successfully.");
            } catch (Exception ex) {
                messageLabel.setText("Error: " + ex.getMessage());
            }
        });

        stage.setScene(new Scene(pane, 400, 200));
        stage.setTitle("Issue Book");
        stage.show();
    }
}
