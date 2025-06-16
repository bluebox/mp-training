package Controller;

import Service.IssueService;
import casestudy.LibraryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReturnBookController {
    @FXML private TextField bookIdField;
    private IssueService issueService = new IssueService();
    private Stage primaryStage;

    public ReturnBookController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleReturnBook() {
        try {
            if (bookIdField.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("Book ID is required");
            }
            
            int bookId = Integer.parseInt(bookIdField.getText().trim());
            issueService.returnBookByBookId(bookId);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book returned successfully");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", 
                "Book ID must be a valid number. Please check your input.");
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Return Error", e.getMessage());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Unexpected Error", 
                "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/MainView.fxml"));
            Scene scene = new Scene(loader.load());
            MainController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Navigation Error", 
                "Failed to return to main view: " + e.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
    }

    private void clearFields() {
        bookIdField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        try {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        } catch (Exception e) {
            System.err.println("Error showing alert: " + e.getMessage());
            System.err.println("Alert details - Type: " + type + ", Title: " + title + ", Message: " + message);
        }
    }
}