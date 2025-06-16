package com.LibraryManagement.ui;

import com.LibraryManagement.service.TransactionService;
import com.LibraryManagement.service.TransactionServiceImpl;
import com.LibraryManagement.dao.TransactionDAOImpl;
import com.LibraryManagement.util.DBConnection;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;

public class ReturnBookUI {
    private final TransactionService transactionService;

    public ReturnBookUI() {
        try {
            Connection conn = DBConnection.getConnection();
            this.transactionService = new TransactionServiceImpl(new TransactionDAOImpl(conn));
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
        Button returnButton = new Button("Return Book");
        Label messageLabel = new Label();

        pane.add(new Label("Book ID:"), 0, 0);
        pane.add(bookIdField, 1, 0);
        pane.add(returnButton, 1, 1);
        pane.add(messageLabel, 1, 2);

        returnButton.setOnAction(e -> {
            try {
                int bookId = Integer.parseInt(bookIdField.getText());
                transactionService.returnBook(bookId);
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
