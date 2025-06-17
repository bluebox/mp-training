package com.medplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.medplus.service.LibraryService;

public class ReturnBookViewController {
    @FXML private TextField issueIdField;

    private final LibraryService service = new LibraryService();

    @FXML
    public void handleReturnBook() {
        try {
            int issueId = Integer.parseInt(issueIdField.getText());
            service.returnBook(issueId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
