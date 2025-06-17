package controllerr;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import service.IssueService;
import java.time.LocalDate;

public class ReturnBookFormController {
    @FXML private TextField issueIdField;
    @FXML private TextField bookIdField;
    @FXML private DatePicker returnDatePicker;
    @FXML private Label messageLabel;

    private final IssueService issueService = new IssueService();

    @FXML
    private void handleReturnBook() {
        try {
            String issueIdText = issueIdField.getText().trim();
            if (!issueIdText.matches("\\d+")) {
                throw new IllegalArgumentException("Issue ID must be a number.");
            }
            int issueId = Integer.parseInt(issueIdText);

            String bookIdText = bookIdField.getText().trim();
            if (!bookIdText.matches("\\d+")) {
                throw new IllegalArgumentException("Book ID must be a number.");
            }
            int bookId = Integer.parseInt(bookIdText);

            LocalDate returnDate = returnDatePicker.getValue();
            if (returnDate == null) {
                throw new IllegalArgumentException("Please select a return date.");
            }

            issueService.returnBook(issueId, bookId, returnDate);
            messageLabel.setText("Book returned successfully.");
            clearForm();
        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }

    private void clearForm() {
        issueIdField.clear();
        bookIdField.clear();
        returnDatePicker.setValue(null);
    }
} 