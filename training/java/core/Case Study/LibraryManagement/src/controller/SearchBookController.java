package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Book;
import service.BookService;
import enums.Availability;
import exception.DatabaseException;

public class SearchBookController {

    @FXML
    private TextField bookIdField;

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label statusLabel;

    private final BookService bookService = new BookService();

    @FXML
    private void onSearch() {
        clearLabels();

        String idText = bookIdField.getText().trim();
        if (idText.isEmpty()) {
            showMessage("Please enter a Book ID.");
            return;
        }

        try {
            int bookId = Integer.parseInt(idText);
            Book book = bookService.getBookById(bookId);

            if (book != null) {
                titleLabel.setText("Title: " + book.getTitle());
                authorLabel.setText("Author: " + book.getAuthor());
                statusLabel.setText("Status: " + (book.getAvailability() == Availability.Available ? "Available" : "Issued"));
            } else {
                showMessage("Book not found.");
            }

        } catch (NumberFormatException e) {
            showMessage("Invalid Book ID format. Please enter a number.");
        } catch (DatabaseException e) {
            showMessage("Database error: " + e.getMessage());
        } catch (Exception e) {
            showMessage("Unexpected error: " + e.getMessage());
        }
    }

    private void clearLabels() {
        titleLabel.setText("");
        authorLabel.setText("");
        statusLabel.setText("");
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Search Book");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
