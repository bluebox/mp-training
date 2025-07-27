package com.library.controller.IssueAndReturn;

import com.library.controller.MainController;
import com.library.model.IssueRecord;
import com.library.service.impl.BookServiceImplementation;
import com.library.service.impl.IssueRecordServiceImplementation;
import com.library.service.impl.MemberServiceImplementation;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.time.LocalDate;

public class IssueBookController {

    @FXML private TextField memberIdField;
    @FXML private TextField bookIdField;
    @FXML private Label statusLabel;
    @FXML private AnchorPane rootPane;

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();
    private final MemberServiceImplementation memberService = new MemberServiceImplementation();
    private final BookServiceImplementation bookService = new BookServiceImplementation();

    @FXML
    public void initialize() {
        Platform.runLater(() -> rootPane.requestFocus());
    }

    @FXML
    private void handleBack() throws IOException {
        MainController.switchScene("MainDashboard.fxml");
    }

    @FXML
    private void handleSubmit() {
        String memberIdText = memberIdField.getText().trim();
        String bookIdText = bookIdField.getText().trim();

        statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        if (memberIdText.isEmpty() || bookIdText.isEmpty()) {
            statusLabel.setText("Both fields are required.");
            return;
        }

        try {
            int memberId = Integer.parseInt(memberIdText);
            int bookId = Integer.parseInt(bookIdText);

            if (!memberService.memberExists(memberId)) {
                statusLabel.setText("Member ID not found.");
                return;
            }

            if (!bookService.bookExists(bookId)) {
                statusLabel.setText("Book ID not found.");
                return;
            }

            IssueRecord record = new IssueRecord(0, bookId, memberId, 'I', LocalDate.now(), null);
            boolean success = issueService.issueBook(record);

            if (success) {
                statusLabel.setText("Book issued successfully.");
                statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                clearForm();
            } else {
                statusLabel.setText("Failed to issue. Book might already be issued.");
            }

        } catch (NumberFormatException e) {
            statusLabel.setText("IDs must be valid numbers.");
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    private void handleCancel() throws IOException {
        clearForm();
        MainController.switchScene("MainDashboard.fxml");
    }

    private void clearForm() {
        memberIdField.clear();
        bookIdField.clear();
    }
}
