package com.LibraryManagement.controller;

import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.service.implementation.IssueRecordServiceImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ReturnBookController {

    @FXML private TextField bookIdField;
    @FXML private Label statusLabel;

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @FXML
    private void handleSubmit() {
        String bookIdText = bookIdField.getText().trim();

        if (bookIdText.isEmpty()) {
            statusLabel.setText("Book ID is required.");
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdText);
            IssueRecords activeIssue = issueService.getActiveIssueByBookId(bookId);

            if (activeIssue == null) {
                statusLabel.setText("This book is not currently issued.");
                return;
            }

            boolean success = issueService.returnBook(activeIssue.getIssueId());

            if (success) {
                statusLabel.setText("Book returned successfully.");
                statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                clearForm();
            } else {
                statusLabel.setText("Return failed. Try again.");
            }

        } catch (NumberFormatException e) {
            statusLabel.setText("Book ID must be a number.");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    private void clearForm() {
        bookIdField.clear();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) bookIdField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleBack() {
        handleCancel();
    }
}
