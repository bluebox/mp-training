package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.IssueService;
import exception.BookAlreadyIssuedException;

public class IssueBookController {

    @FXML private TextField bookIdField;
    @FXML private TextField memberIdField;

    private final IssueService issueService = new IssueService();

    @FXML
    private void handleIssueBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText().trim());
            int memberId = Integer.parseInt(memberIdField.getText().trim());

            issueService.issueBook(bookId, memberId);

            showAlert(Alert.AlertType.INFORMATION, "Book issued successfully.");
            bookIdField.clear();
            memberIdField.clear();

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid input. Enter valid numeric IDs.");
        } catch (BookAlreadyIssuedException e) {
            showAlert(Alert.AlertType.WARNING, "This book is already issued.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error issuing book: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Issue Book");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
