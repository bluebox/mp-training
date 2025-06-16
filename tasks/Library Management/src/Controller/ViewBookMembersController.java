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

public class ViewBookMembersController {
    @FXML private TextField bookIdField;
    @FXML private TableView<IssueRecord> membersTable;
    @FXML private TableColumn<IssueRecord, Integer> memberIdColumn;
    @FXML private TableColumn<IssueRecord, java.time.LocalDate> issueDateColumn;
    @FXML private TableColumn<IssueRecord, Character> statusColumn;
    private IssueService issueService = new IssueService();
    private Stage primaryStage;

    public ViewBookMembersController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void initialize() {
        memberIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getMemberId()).asObject());
        issueDateColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getIssueDate()));
        statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getStatus()));
    }

    @FXML
    private void handleViewBookMembers() {
        try {
            int bookId = Integer.parseInt(bookIdField.getText());
            List<IssueRecord> issues = issueService.getAllIssues().stream()
                    .filter(issue -> issue.getBookId() == bookId)
                    .collect(java.util.stream.Collectors.toList());
            membersTable.setItems(FXCollections.observableArrayList(issues));
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid book ID format");
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
        bookIdField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}