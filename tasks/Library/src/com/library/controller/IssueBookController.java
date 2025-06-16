package com.library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.library.serviceInterface.IssueServiceInterface;
import com.library.services.IssueService;

public class IssueBookController {

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField memberIdField;

    @FXML
    private Label statusLabel;

    @FXML
    public void handleIssueBook() {
        String bookIdText = bookIdField.getText().trim();
        String memberIdText = memberIdField.getText().trim();

        if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
            statusLabel.setText("Book ID and Member ID are required.");
            return;
        }
        
        IssueServiceInterface issueService= new IssueService();
        try {
			issueService.issueBook(Integer.parseInt(bookIdText),Integer.parseInt(memberIdText));
			
			statusLabel.setText("Book issued successfully");
        } catch (Exception e) {
        	statusLabel.setText(e.getMessage());
			
		}
    }
}