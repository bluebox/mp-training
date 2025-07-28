package controller;

import Service.ServiceLayer;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class ReturnBookController {

    @FXML
    public TextField memberIdField, bookIdField;
@FXML
    public ServiceLayer issueService = new ServiceLayer();

    public void handleReturnBook() {
        try {
            int memberid = Integer.parseInt(memberIdField.getText());
            int bookId = Integer.parseInt(bookIdField.getText());
            issueService.returnBook(bookId, memberid);
            showAlert("Book returned successfully.");
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
