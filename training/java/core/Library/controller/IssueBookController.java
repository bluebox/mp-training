package controller;

import dao.IssueDAO;
import enums.Availability;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import model.IssueRecord;

import java.time.LocalDate;

public class IssueBookController {

    @FXML
    private TextField txtbookId;

    @FXML
    private TextField txtmemberId;

    @FXML
    public void handleConfirmIssue() {
        try {
            int bookId = Integer.parseInt(txtbookId.getText());
            int memberId = Integer.parseInt(txtmemberId.getText());

            IssueRecord issue = new IssueRecord(
                    0,
                    bookId,
                    memberId,
                    Availability.Issued,
                    LocalDate.now(),
                    null);

            IssueDAO dao = new IssueDAO();
            boolean success = dao.issueBook(issue);

            if (success) {
                showAlert("Success", "Book issued successfully.");
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
