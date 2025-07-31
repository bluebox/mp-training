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
    }

    @FXML
    public void initialize() {
        if (issueRecordService == null) {
            try {
                setConnection(DBUtil.getConnection());
            } catch (Exception e) {
                lblMessage.setText("Failed to initialize DB.");
                lblMessage.setStyle("-fx-text-fill: red;");
            }
        }
    }

    @FXML
    private void handleIssueBook() {
        if (issueRecordService == null) {
            lblMessage.setText("Database not initialized.");
            lblMessage.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            if (txtBookId.getText().trim().isEmpty() || txtMemberId.getText().trim().isEmpty()) {
                lblMessage.setText("Please enter both Book ID and Member ID.");
                lblMessage.setStyle("-fx-text-fill: red;");
                return;
            }

            int bookId = Integer.parseInt(txtBookId.getText().trim());
            int memberId = Integer.parseInt(txtMemberId.getText().trim());

            String issuedBy = "ADMIN"; // Hardcoded or fetch from session if needed
            String result = issueRecordService.issueBookToMember(bookId, memberId, issuedBy);
            lblMessage.setText(result);
            lblMessage.setStyle(result.toLowerCase().contains("success") ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

            if (result.toLowerCase().contains("success")) {
                txtBookId.clear();
                txtMemberId.clear();
            }
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid Book ID or Member ID.");
            lblMessage.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleReturnBook() {
        if (issueRecordService == null) {
            lblMessage.setText("Database not initialized.");
            lblMessage.setStyle("-fx-text-fill: red;");
            return;
        }

        try {
            if (txtBookId.getText().trim().isEmpty() || txtMemberId.getText().trim().isEmpty()) {
                lblMessage.setText("Please enter both Book ID and Member ID.");
                lblMessage.setStyle("-fx-text-fill: red;");
                return;
            }

            int bookId = Integer.parseInt(txtBookId.getText().trim());
            int memberId = Integer.parseInt(txtMemberId.getText().trim());

            String result = issueRecordService.returnBookFromMember(bookId, memberId);
            lblMessage.setText(result);
            lblMessage.setStyle(result.toLowerCase().contains("success") ? "-fx-text-fill: green;" : "-fx-text-fill: red;");

            if (result.toLowerCase().contains("success")) {
                txtBookId.clear();
                txtMemberId.clear();
            }
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid Book ID or Member ID.");
            lblMessage.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleViewIssueTable(ActionEvent event) throws Exception {
        Connection conn = DBUtil.getConnection();
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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}