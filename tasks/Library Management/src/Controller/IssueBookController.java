package Controller;

import casestudy.IssueRecord;
import casestudy.LibraryException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

import Service.IssueService;

public class IssueBookController {
    @FXML private TextField bookIdField, memberIdField;
    private IssueService issueService = new IssueService();
    private Stage primaryStage;

    public IssueBookController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private void handleIssueBook() {
        try {
            // Validate input fields
            String bookIdText = bookIdField.getText().trim();
            String memberIdText = memberIdField.getText().trim();
            
            if (bookIdText.isEmpty() || memberIdText.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all fields");
                return;
            }
            
            int bookId = Integer.parseInt(bookIdText);
            int memberId = Integer.parseInt(memberIdText);
            
            if (bookId <= 0 || memberId <= 0) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Book ID and Member ID must be positive numbers");
                return;
            }
            
            IssueRecord issue = new IssueRecord(
                bookId,
                memberId,
                'I',
                LocalDate.now(),
                null
            );
            
            issueService.issueBook(issue);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book issued successfully");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", 
                "Please enter valid numbers for Book ID and Member ID");
        } catch (LibraryException e) {
            showAlert(Alert.AlertType.ERROR, "Issue Error", e.getMessage());
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
        memberIdField.clear();
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