package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.IssueService;
import util.DBUtil;

import java.sql.Connection;

public class IssueController {
    @FXML private TextField txtBookId;
    @FXML private TextField txtMemberId;
    @FXML private Label lblMessage;
    @FXML private Button issueButton;
    @FXML private Button returnButton;

    private IssueService issueRecordService;
    public void setConnection(Connection connection) {
        this.issueRecordService = new IssueService(connection);
    }    @FXML
    private void handleIssueBook() {
        try {
            int bookId = Integer.parseInt(txtBookId.getText().trim());
            int memberId = Integer.parseInt(txtMemberId.getText().trim());

            String result = issueRecordService.issueBookToMember(bookId, memberId);
            lblMessage.setText(result);
            lblMessage.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid Book ID or Member ID.");
            lblMessage.setStyle("-fx-text-fill: red;");
        }
        if (issueRecordService == null) {
            lblMessage.setText("Database not initialized.");
            lblMessage.setStyle("-fx-text-fill: red;");
            return;
        }

    }
    @FXML
    private void handleReturnBook() {
        try {
            int bookId = Integer.parseInt(txtBookId.getText().trim());
            int memberId = Integer.parseInt(txtMemberId.getText().trim());

            String result = issueRecordService.returnBookFromMember(bookId, memberId);
            lblMessage.setText(result);
            lblMessage.setStyle("-fx-text-fill: green;");
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid Book ID or Member ID.");
            lblMessage.setStyle("-fx-text-fill: red;");
        }
        if (issueRecordService == null) {
            lblMessage.setText("Database not initialized.");
            lblMessage.setStyle("-fx-text-fill: red;");
            return;
        }
    }
    @FXML
    private void handleViewIssueTable(ActionEvent event) throws Exception {
    	Connection conn=DBUtil.getConnection();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view_issue.fxml"));
        Parent root = loader.load();
        ViewIssueController controller = loader.getController();
        controller.setConnection(conn); 
        Stage stage = new Stage();
        stage.setTitle("Issue Records");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void handleBack(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/Main.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}