package Library.src.main.java.com.LibraryManagement.ui;

import Library.src.main.java.com.LibraryManagement.service.IssueRecordService;
import Library.src.main.java.com.LibraryManagement.service.IssueRecordServiceImpl;
import Library.src.main.java.com.LibraryManagement.dao.BookDAOImpl;
import Library.src.main.java.com.LibraryManagement.dao.IssueRecordDAOImpl;
import Library.src.main.java.com.LibraryManagement.util.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;

public class ReturnBookUI {
    private final IssueRecordService issueService;

    public ReturnBookUI() {
        try {
            Connection conn = DBConnection.getConnection();
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
        Button returnButton = new Button("Return Book");
        Label messageLabel = new Label();

        pane.add(new Label("Book ID:"), 0, 0);
        pane.add(bookIdField, 1, 0);
        pane.add(returnButton, 1, 1);
        pane.add(messageLabel, 1, 2);
        
        pane.add(new Label("Member ID:"), 2, 3);
        pane.add(memberIdField, 1, 0);
      

        returnButton.setOnAction(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText());
                int memberId=Integer.parseInt(memberIdField.getText());
                issueService.returnBook(bookId,memberId);
                messageLabel.setText("Book returned successfully.");
            } catch (Exception ex) {
                messageLabel.setText("Error: " + ex.getMessage());
            }
        });

        stage.setScene(new Scene(pane, 400, 180));
        stage.setTitle("Return Book");
        stage.show();
    }
}
