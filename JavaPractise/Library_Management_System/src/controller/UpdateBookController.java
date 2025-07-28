package controller;

import dao.bookDao;
import domain.BookStatus;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.BookService;

public class UpdateBookController {
	@FXML private TextField idField;
	@FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
    @FXML private TextField statusField;
    
    private final BookService bookService = new BookService(new bookDao());

    @FXML
    private void handleUpdateBook() {
        try {
            String str_id = idField.getText();
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String stat = statusField.getText();
            
            if (str_id == null || str_id.trim().isEmpty()) {
                showAlert("Validation Error", "Book ID is required.");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(str_id.trim());
            } catch (NumberFormatException e) {
                showAlert("Validation Error", "Book ID must be a valid number.");
                return;
            }
            BookStatus status = null;
            if (stat != null && !stat.trim().isEmpty()) {
                try {
                    status = BookStatus.valueOf(stat.trim().toUpperCase());
                } catch (IllegalArgumentException e) {
                    showAlert("Validation Error", "Invalid status. Use:  A for active, I for inactive, etc.");
                    return;
                }
            }
            if (!bookService.bookExists(id)) {
                showAlert("Book Not Found", "No book found with ID: " + id);
                return;
            }
            bookService.updateBookDetails(id,
                    title != null && !title.trim().isEmpty() ? title.trim() : null,
                    author != null && !author.trim().isEmpty() ? author.trim() : null,
                    category != null && !category.trim().isEmpty() ? category.trim() : null,
                    status); 

            showAlert("Success", "Book updated successfully.");
            clearFields();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred while updating: " + e.getMessage());
        } catch (Throwable e) {
			e.printStackTrace();
		}
    }


    @FXML
    private void handleBack() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/resources/BookManagement.fxml"));
        Stage stage = (Stage) titleField.getScene().getWindow();
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
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        statusField.clear();
    }
}
