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
        	int id = (str_id == null) ? 0 : Integer.parseInt(str_id);
        	String avail = availabilityField.getText();
        	AvailabilityStatus availability = AvailabilityStatus.valueOf(avail); 
        	if (str_id.isEmpty() || avail.isEmpty()) {
        	    showAlert("Error", "Please fill all fields.");
        	    return;
        	}
            bookService.updateBookAvailability(id,availability);
            showAlert("Success", "Book Availability Updated successfully.");
            clearFields();

        } catch (Exception e) {
        	System.out.print(e.getMessage());
            showAlert("Database Error", e.getMessage());
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
