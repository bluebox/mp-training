package controller;

import dao.bookDao;
import domain.AvailabilityStatus;
import domain.Book;
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
        	String str_id=idField.getText();
        	int id =(str_id==null)?0:Integer.parseInt(str_id);
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String stat = statusField.getText();
            BookStatus status=BookStatus.valueOf(stat); 
            if (id ==0 || title.isEmpty() || author.isEmpty() || category.isEmpty() || status == null) {
                showAlert("Error", "Please fill all fields.");
                return;
            }
            bookService.updateBookDetails(id, title, author, category, status);
            showAlert("Success", "Book Updated successfully.");
            clearFields();

        } catch (Exception e) {
        	System.out.print(e.getMessage());
            showAlert("Database Error", e.getMessage());
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
