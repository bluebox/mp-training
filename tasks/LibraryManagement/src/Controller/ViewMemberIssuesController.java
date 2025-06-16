package Controller;

import casestudy.IssueRecord;
import casestudy.LibraryException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

import Service.IssueService;

public class ViewMemberIssuesController {
    @FXML private TextField memberIdField;
    @FXML private TableView<IssueRecord> issuesTable;
    @FXML private TableColumn<IssueRecord, Integer> issueIdColumn, bookIdColumn;
    @FXML private TableColumn<IssueRecord, java.time.LocalDate> issueDateColumn;
    private IssueService issueService = new IssueService();
    private Stage primaryStage;

    public ViewMemberIssuesController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        issueIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getIssueId()).asObject());
        bookIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getBookId()).asObject());
        issueDateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getIssueDate()));
    }

    @FXML
    private void handleViewMemberIssues() {
        try {
            int memberId = Integer.parseInt(memberIdField.getText());
            List<IssueRecord> issues = issueService.getActiveIssuesByMember(memberId);
            issuesTable.setItems(FXCollections.observableArrayList(issues));
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid member ID format");
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    @FXML
    private void goBack() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        MainController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setScene(scene);
    }

    @FXML
    private void handleClear() {
        memberIdField.clear();
        issuesTable.getItems().clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}