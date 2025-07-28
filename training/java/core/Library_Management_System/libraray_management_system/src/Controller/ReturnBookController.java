package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import Service.IssueRecordService;

public class ReturnBookController {

    @FXML
    public TextField issueIdField, bookIdField;
@FXML
    public IssueRecordService issueService = new IssueRecordService();

    public void handleReturnBook() {
        try {
            int memberid = Integer.parseInt(issueIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            issueService.returnBook(bookId, memberid);
            showAlert("Book returned successfully.");
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}
