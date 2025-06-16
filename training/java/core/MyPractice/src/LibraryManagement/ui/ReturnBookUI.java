package com.LibraryManagement.ui;
import com.LibraryManagement.dao.*;
import com.LibraryManagement.service.*;
import com.LibraryManagement.util.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.*;

public class ReturnBookUI {
    private final IssueRecordService issueService;

    public ReturnBookUI() {
        try {
            Connection conn = DBConnection.getConnection();
            this.issueService = new IssueRecordServiceImpl(
                    new IssueRecordDAOImpl(conn),
                    new BookDAOImpl(conn));
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize service: " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(15));
        pane.setVgap(10);
        pane.setHgap(10);

        Label bookIdLabel = new Label("Book ID:");
        TextField bookIdField = new TextField();

        Label memberIdLabel = new Label("Member ID:");
        TextField memberIdField = new TextField();

        Button returnButton = new Button("Return Book");
        Label messageLabel = new Label();

        pane.add(bookIdLabel, 0, 0);
        pane.add(bookIdField, 1, 0);

        pane.add(memberIdLabel, 0, 1);
        pane.add(memberIdField, 1, 1);

        pane.add(returnButton, 1, 2);
        pane.add(messageLabel, 1, 3);

        returnButton.setOnAction(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText().trim());
                int memberId = Integer.parseInt(memberIdField.getText().trim());
                issueService.returnBook(bookId, memberId);
                messageLabel.setText("Book returned successfully.");
            } catch (NumberFormatException nfe) {
                messageLabel.setText("Please enter valid numeric IDs.");
            } catch (Exception ex) {
                messageLabel.setText("Error: " + ex.getMessage());
            }
        });

        Scene scene = new Scene(pane, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Return Book");
        stage.show();
    }
}
