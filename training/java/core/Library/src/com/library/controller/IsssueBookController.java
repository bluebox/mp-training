package com.library.controller;

import com.library.exception.UserDefinedException;
import com.library.service.IssueService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class IsssueBookController {
	@FXML 
	private TextField bookIdField;
    @FXML 
    private TextField memberIdField;
    private final IssueService issueService = new IssueService();
    @FXML
    private void handleIssueBook() throws UserDefinedException{
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());
            issueService.issueBook(bookId, memberId);
            throw new UserDefinedException("Book issued successfully.");
        } catch (NumberFormatException e) {
        	throw new UserDefinedException("Invalid input. Enter valid numeric IDs.");
        } catch (UserDefinedException e) {
        	throw new UserDefinedException("This book is already issued.");
        } catch (Exception e) {
        	throw new UserDefinedException("Error issuing book: " + e.getMessage());
        }
    }
}
