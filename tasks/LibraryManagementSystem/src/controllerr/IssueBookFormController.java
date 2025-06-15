package controllerr;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.IssueRecord;
import service.IssueService;

import java.time.LocalDate;

public class IssueBookFormController {

    @FXML private TextField bookIdField;
    @FXML private TextField memberIdField;
    @FXML private DatePicker issueDatePicker;
    @FXML private Label messageLabel;

    private final IssueService issueService = new IssueService();

    @FXML
    public void handleIssueBook() {
        try {
            // Validate Book ID
            String bookIdText = bookIdField.getText().trim();
            if (!bookIdText.matches("\\d+")) {
                throw new IllegalArgumentException("Book ID must be a number.");
            }
            int bookId = Integer.parseInt(bookIdText);

            // Validate Member ID
            String memberIdText = memberIdField.getText().trim();
            if (!memberIdText.matches("\\d+")) {
                throw new IllegalArgumentException("Member ID must be a number.");
            }
            int memberId = Integer.parseInt(memberIdText);

            // Validate Issue Date
            LocalDate issueDate = issueDatePicker.getValue();
            if (issueDate == null) {
                throw new IllegalArgumentException("Please select an issue date.");
            }

            // Create and issue the record
            IssueRecord issue = new IssueRecord(bookId, memberId, 'I', issueDate);
            issueService.issueBook(issue);

            messageLabel.setText("Book issued successfully.");
            clearForm();

        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }

    private void clearForm() {
        bookIdField.clear();
        memberIdField.clear();
        issueDatePicker.setValue(null);
    }
}
