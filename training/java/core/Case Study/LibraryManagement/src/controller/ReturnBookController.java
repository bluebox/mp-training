package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ReturnBookController {
    @FXML private TextField bookIdField;
    @FXML private TextField memberIdField;

    @FXML
    private void handleReturnBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText().trim());
            int memberId = Integer.parseInt(memberIdField.getText().trim());

            // Simulate book return
            showAlert(Alert.AlertType.INFORMATION, "Book returned successfully.");
            bookIdField.clear();
            memberIdField.clear();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid input. Enter numeric IDs.");
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
