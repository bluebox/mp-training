package com.library.controller;

import java.io.IOException;

import com.library.domain.IssueRecord;
import com.library.service.IssueBookService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    
    public void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/library/UI/Home.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Issue Records");
        stage.show();
    }
}
