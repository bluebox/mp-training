package com.LibraryManagement.controller;

import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.service.implementation.IssueRecordServiceImplementation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class IssueBookController {

    @FXML private TextField bookIdField;
    @FXML private TextField memberIdField;
    @FXML private DatePicker issueDatePicker;

    @FXML private Label bookIdError;
    @FXML private Label memberIdError;
    @FXML private Label issueDateError;

    private final IssueRecordServiceImplementation issueService = new IssueRecordServiceImplementation();

    @FXML
    private void handleIssueBook() {
        clearErrors();

        String bookIdText = bookIdField.getText().trim();
        String memberIdText = memberIdField.getText().trim();
        LocalDate issueDate = issueDatePicker.getValue();

        boolean valid = true;

        if (bookIdText.isEmpty() || !bookIdText.matches("\\d+")) {
            bookIdError.setText("Valid Book ID is required");
            valid = false;
        }

        if (memberIdText.isEmpty() || !memberIdText.matches("\\d+")) {
            memberIdError.setText("Valid Member ID is required");
            valid = false;
        }

        if (issueDate == null) {
            issueDateError.setText("Issue date is required");
            valid = false;
        }

        if (!valid) return;

        IssueRecords record = new IssueRecords(
                Integer.parseInt(bookIdText),
                Integer.parseInt(memberIdText),
                "I",
                issueDate
        );

        boolean success;
		try {
			success = issueService.issueBook(record);
			if (success) {
	            showAlert("Success", "Book issued successfully!");
	            clearForm();
	        } else {
	            showAlert("Failed", "Book could not be issued. It may already be issued.");
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    private void clearErrors() {
        bookIdError.setText("");
        memberIdError.setText("");
        issueDateError.setText("");
    }

    private void clearForm() {
        bookIdField.clear();
        memberIdField.clear();
        issueDatePicker.setValue(null);
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
