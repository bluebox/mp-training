package com.library.controller;

import com.Service.IssueService;
//import com.library.util.AlertMsg;
 
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.time.LocalDate;
 
public class ReturnBookController {
 
    @FXML 
    private TextField memberIdField;
    @FXML 
    private TextField bookIdField;
//    @FXML private TextField memberIdField;
    private final IssueService issueService = new IssueService();
 
    @FXML
    private void returnBook() {
        try {
//      int issueId = Integer.parseInt(iIdField.getText());
            int bookId=Integer.parseInt(bookIdField.getText());
			int memberId=Integer.parseInt(memberIdField.getText());
          LocalDate returnDate = LocalDate.now();
 
            issueService.returnBook(bookId,memberId);
 
            memberIdField.clear();
            bookIdField.clear();
        } catch (Exception e) {
            AlertMsg.showError(e.getMessage());
        }
    }
}
