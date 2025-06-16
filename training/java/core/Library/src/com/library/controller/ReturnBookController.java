package com.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.library.service.IssueService;

public class ReturnBookController {

    @FXML private TextField bookIdField;
    @FXML private TextField memberIdField;

    private final IssueService issueService = new IssueService();

    @FXML
    private void handleReturnBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());

            issueService.returnBook(bookId, memberId);

            showAlert(Alert.AlertType.INFORMATION, "Book returned successfully.");
            bookIdField.clear();
            memberIdField.clear();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid input. Enter valid numeric IDs.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error returning book: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Return Book");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
