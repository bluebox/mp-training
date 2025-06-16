package controller;

import enums.Availability;
import enums.Status;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Book;
import service.BookService;

public class AddBookController {

    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;

    private final BookService bookService = new BookService();

    @FXML
    private void handleAddBook() {
        String title = titleField.getText();
        String author = authorField.getText();
        String category = categoryField.getText();

        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }

        try {
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            book.setStatus(Status.Active);
            book.setAvailability(Availability.Available);

            bookService.addBook(book);
            showAlert("Book added successfully!");
            titleField.clear();
            authorField.clear();
            categoryField.clear();
        } catch (Exception e) {
            showAlert("Error: " + e.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Book");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
