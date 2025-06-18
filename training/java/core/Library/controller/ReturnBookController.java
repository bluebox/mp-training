package controller;

import dao.IssueDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class ReturnBookController {

    @FXML
    private TextField issueIdField;

    @FXML
    private TextField bookIdField;

    @FXML
    public void handleConfirmReturn() {
        try {
            int issueId = Integer.parseInt(issueIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());

            IssueDAO dao = new IssueDAO();
            boolean success = dao.returnBook(issueId, bookId);

            if (success) {
                showAlert("Success", "Book returned successfully.");
            }

        } catch (Exception e) {
            showAlert("Error", e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
