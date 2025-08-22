package com.LibraryManagement.controller;

import com.LibraryManagement.DAO.Implementation.BookDAOImplementation;
import com.LibraryManagement.models.Book;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateBookAvailability {

    @FXML private ComboBox<String> availabilityComboBox;

    @FXML private TextField bookIdField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField categoryField;

    private Book book;

    @FXML
    public void initialize() {
        availabilityComboBox.getItems().addAll("Available", "Issued");
    }

    public void setBook(Book book) {
        this.book = book;

        bookIdField.setText(String.valueOf(book.getBookId()));
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        categoryField.setText(book.getCategory());

        String availabilityText = book.getAvailability().equalsIgnoreCase("A") ? "Available" : "Issued";
        availabilityComboBox.setValue(availabilityText);
    }

    @FXML
    private void handleUpdateAvailability() {
        String availabilityText = availabilityComboBox.getValue();

        if (availabilityText == null) {
            showAlert("Invalid Selection", "Please select availability status.");
            return;
        }

        String availabilityCode = availabilityText.equals("Available") ? "A" : "I";
        book.setAvailability(availabilityCode);

        BookDAOImplementation bookService = new BookDAOImplementation();
        bookService.updateAvailability(book);

        ((Stage) availabilityComboBox.getScene().getWindow()).close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
