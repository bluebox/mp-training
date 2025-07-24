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

public class AddBookController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;
    @FXML private TextField statusField;
    @FXML private TextField availabilityField;

    private final BookService bookService = new BookService(new bookDao());

    @FXML
    private void handleAddBook() {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String stat = statusField.getText();
            BookStatus status=BookStatus.valueOf(stat);
            String avail = availabilityField.getText();
            AvailabilityStatus availability = AvailabilityStatus.valueOf(avail); 
            if (title.isEmpty() || author.isEmpty() || category.isEmpty() || status == null || availability == null) {
                showAlert("Error", "Please fill all fields.");
                return;
            }
            Book book = new Book(title, author, category, status, availability);
            bookService.addBook(book);
            showAlert("Success", "Book added successfully.");
            clearFields();
        } catch (Exception e) {
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
        titleField.clear();
        authorField.clear();
        categoryField.clear();
        statusField.clear();
        availabilityField.clear();
    }
}

