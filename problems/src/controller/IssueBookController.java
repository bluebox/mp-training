package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

import Domain.IssueStatus;
import Service.ServiceLayer;

public class IssueBookController {

    @FXML
    public  TextField bookIdField, memberIdField;
     @FXML
    public ServiceLayer issueService = new ServiceLayer();

    public void handleIssueBook() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            int memberId = Integer.parseInt(memberIdField.getText());
             LocalDate returndate=LocalDate.now().plusDays(14);
            issueService.createBookIssue(bookId, memberId,IssueStatus.ISSUED,LocalDate.now(),returndate);
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}
