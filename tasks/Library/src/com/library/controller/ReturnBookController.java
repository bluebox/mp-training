package com.library.controller;

import com.library.services.IssueService;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReturnBookController {
    @FXML private TextField bookIdField;

    @FXML
    private Label statusLabel;
    private final IssueService issueService = new IssueService();

    @FXML
    private void handleReturnBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            issueService.returnBook(bookId);
            statusLabel.setText("Book returned successfully");
            
        } catch (Exception e) {
        	statusLabel.setText(e.getMessage());
        }
    }
}