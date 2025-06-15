package com.library.controller;

import com.library.domain.IssueRecord;
import com.library.service.IssueBookService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IssueBookController {

    @FXML private TextField bookIdField;
    @FXML private TextField memberIdField;
    @FXML private Label messageLabel;

    private IssueBookService service = new IssueBookService();

    @FXML
    private void handleSubmit() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());

            IssueRecord record = new IssueRecord(bookId, memberId);

            if (service.issueBook(record)) {
                messageLabel.setText("Book issued successfully!");
            } else {
                messageLabel.setText("Book is not available.");
            }

        } catch (Exception e) {
            messageLabel.setText("Error: " + e.getMessage());
        }
    }
}
