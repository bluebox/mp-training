package com.library.controller.IssueAndReturn;

import java.io.IOException;

import com.library.controller.MainController;
import com.library.model.IssueRecord;
import com.library.service.impl.IssueRecordServiceImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class ReturnBookController {

    @FXML private TextField bookIdField;
    @FXML private Label statusLabel;

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }
    @FXML
    private void handleSubmit() {
        String bookIdText = bookIdField.getText().trim();

        if (bookIdText.isEmpty()) {
            statusLabel.setText("Book ID is required.");
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdText);

            IssueRecord activeIssue = issueService.getActiveIssueByBookId(bookId);

            if (activeIssue == null) {
                statusLabel.setText("This book is not currently issued.");
                return;
            }

            boolean success = issueService.returnBook(activeIssue.getIssueId());

            if (success) {
                statusLabel.setText("Book Returned successfully.");
                statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                clearForm();
                // Remove or comment out the line below
                // Stage stage = (Stage) memberIdField.getScene().getWindow();
                // stage.close();
            }


            else {
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
        bookIdField.clear();
    }
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) bookIdField.getScene().getWindow();
        stage.close();
    }
}
