package com.library.controller;

import com.library.service.IssueBookService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class ReturnBookController {

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField memberIdField;

    @FXML
    private void handleReturnBook() {
        String bookIdStr = bookIdField.getText().trim();
        String memberIdStr = memberIdField.getText().trim();

        if (bookIdStr.isEmpty() || memberIdStr.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Both Book ID and Member ID are required.");
            return;
        }

        try {
            int bookId = Integer.parseInt(bookIdStr);
            int memberId = Integer.parseInt(memberIdStr);

            IssueBookService service = new IssueBookService();
            service.returnBook(bookId, memberId);

            showAlert(Alert.AlertType.INFORMATION, "Success", "Book returned successfully!");
            clearFields();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Book ID and Member ID must be numbers.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error returning the book.");
        }
    }


    @FXML
    private void handleCancel() {
        Stage stage = (Stage) bookIdField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        bookIdField.clear();
        memberIdField.clear();
    }
}
