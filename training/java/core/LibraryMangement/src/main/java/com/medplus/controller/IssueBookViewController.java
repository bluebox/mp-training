package com.medplus.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.medplus.service.LibraryService;

public class IssueBookViewController {
    @FXML private TextField bookIdField;
    @FXML private TextField memberIdField;

    private final LibraryService service = new LibraryService();

    @FXML
    public void handleIssueBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());
            service.issueBook(bookId, memberId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
