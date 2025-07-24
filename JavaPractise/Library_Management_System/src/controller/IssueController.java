package controller;

import domain.IssueRecord;
import domain.IssueStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.IssueService;

import java.time.LocalDate;

public class IssueController {

    @FXML private TextField txtBookId;
    @FXML private TextField txtMemberId;
    @FXML private Label lblMessage;

    private final IssueService service = new IssueService();

    @FXML
    public void handleIssueBook() throws Throwable {
        try {
            IssueRecord record = new IssueRecord();
            record.setBookId(Integer.parseInt(txtBookId.getText()));
            record.setMemberId(Integer.parseInt(txtMemberId.getText()));
            record.setStatus(IssueStatus.I);
            record.setIssueDate(LocalDate.now());

            service.issueBook(record);
            lblMessage.setText("Book issued successfully!");
        } catch (Exception e) {
            lblMessage.setText("Issue failed: " + e.getMessage());
        }
    }

    @FXML
    public void handleReturnBook() throws Throwable {
        try {
            int bookId = Integer.parseInt(txtBookId.getText());
            int memberId = Integer.parseInt(txtMemberId.getText());

            service.returnBook(bookId, memberId);
            lblMessage.setText("Book returned successfully!");
        } catch (Exception e) {
            lblMessage.setText("Return failed: " + e.getMessage());
        }
    }
    @FXML
    private void handleBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/BookManagement.fxml"));
        Stage stage = (Stage) txtBookId.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
}
