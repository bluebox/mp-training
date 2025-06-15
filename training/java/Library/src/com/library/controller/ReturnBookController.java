package com.library.controller;

import com.library.services.IssueService;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReturnBookController {
    @FXML private TextField bookIdField;

    private final IssueService issueService = new IssueService();

    @FXML
    private void handleReturnBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            issueService.returnBook(bookId);
            //new Alert(Alert.AlertType.INFORMATION, "Book returned successfully").show();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}