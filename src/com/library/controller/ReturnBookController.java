package com.library.controller;

import com.library.service.IssueService;
import com.library.util.AlertMsg;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class ReturnBookController {

    @FXML private TextField issueIdField;

    private final IssueService issueService = new IssueService();

    @FXML
    private void returnBook() {
        try {
            int issueId = Integer.parseInt(issueIdField.getText());
            LocalDate returnDate = LocalDate.now();

            issueService.returnBook(issueId, returnDate);

            issueIdField.clear();
        } catch (Exception e) {
            AlertMsg.showError(e.getMessage());
        }
    }
}