package com.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class IssueBookController {

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField memberIdField;

    @FXML
    private Button submitButton;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleSubmit() {
        String bookIdText = bookIdField.getText().trim();
        String memberIdText = memberIdField.getText().trim();

        // Simple validation to check if values are numeric
        if (!isNumeric(bookIdText) || !isNumeric(memberIdText)) {
            messageLabel.setText("Details not found");
            return;
        }

        int bookId = Integer.parseInt(bookIdText);
        int memberId = Integer.parseInt(memberIdText);

        // Sample checking details 
        if (bookId == 123 && memberId == 456) {
            messageLabel.setText("Book issued successfully!");
            messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
        } else {
            messageLabel.setText("Details not found");
            messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
}
