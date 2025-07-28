package controller;

import dao.bookDao;
import domain.AvailabilityStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.BookService;

public class UpdateBookAvailabilityController {
	@FXML private TextField idField;
    @FXML private TextField availabilityField;
    

    private final BookService bookService = new BookService(new bookDao());

    @FXML
    private void handleUpdateBook() {
        try {
            String str_id = idField.getText();
            String avail = availabilityField.getText();
            if (str_id == null || str_id.trim().isEmpty() || avail == null || avail.trim().isEmpty()) {
                showAlert("Error", "Please fill all fields.");
                return;
            }
            int id;
            try {
                id = Integer.parseInt(str_id.trim());
            } catch (NumberFormatException e) {
                showAlert("Error", "Book ID must be a valid number.");
                return;
            }

            AvailabilityStatus availability;
            try {
                availability = AvailabilityStatus.valueOf(avail.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                showAlert("Error", "Invalid availability status. Use:  A for available,  I unavailable");
                return;
            }

            if (!bookService.bookExists(id)) {
                showAlert("Book Not Found", "No book found with ID: " + id);
                return;
            }
            bookService.updateBookAvailability(id, availability);
            showAlert("Success", "Book availability updated successfully.");
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        } catch (Throwable e) {
			e.printStackTrace();
		}
    }


    @FXML
    private void handleBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/BookManagement.fxml"));
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
    	idField.clear();
        availabilityField.clear();
    }
}
