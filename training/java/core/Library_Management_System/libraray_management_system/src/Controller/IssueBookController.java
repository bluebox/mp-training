package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import Service.IssueRecordService;

public class IssueBookController {

    @FXML
    public  TextField bookIdField, memberIdField;
     @FXML
    public IssueRecordService issueService = new IssueRecordService();

    public void handleIssueBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());
            if(issueService.issueBook(bookId, memberId)) {
				showAlert("Book issued successfully.");
			} else {
				showAlert("Failed to issue book. Please check the book and member IDs.");
			}
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
