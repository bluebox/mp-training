package com.lms.controller;

import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.util.Validator;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UpdateBookController {

    @FXML private TextField bookIdField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private ComboBox<BookCategory> categoryField = new ComboBox<>();
    @FXML private ToggleGroup statusGroup;
    @FXML private ToggleGroup availabilityGroup;
    @FXML private RadioButton statusActive;
    @FXML private RadioButton statusInactive;
    @FXML private RadioButton availableRadio;
    @FXML private RadioButton unavailableRadio;

    private Book currentBook = null;
    BookServiceImpl bookService = new BookServiceImpl();

    @FXML
    public void initialize() {
        categoryField.getItems().addAll(BookCategory.values());
    }

    @FXML
    private void fetchBookDetails() {
        String id = bookIdField.getText().trim();
        if (Validator.isEmptyBookId(id)) {
            showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter a Book ID.");
            return;
        }

        try {
            currentBook = bookService.getBookById(id);
        } catch (InvalidInputException e) {
            showAlert(Alert.AlertType.ERROR, "Book Not Found", e.getMessage());
            return;
        }

        if (currentBook != null) {
            titleField.setText(currentBook.getBookTitle());
            authorField.setText(currentBook.getBookAuthor());
            categoryField.setValue(currentBook.getBookCategory());
            statusGroup.selectToggle(currentBook.getStatus() == 'A' ? statusActive : statusInactive);
            availabilityGroup.selectToggle(currentBook.getAvailability() == 'A' ? availableRadio : unavailableRadio);
        }
    }

    @FXML
    private void updateBookDetails() {
        if (currentBook == null) {
            showAlert(Alert.AlertType.WARNING, "No Book Loaded", "Please fetch a book before updating.");
            return;
        }

        char status = (statusGroup.getSelectedToggle() == statusActive) ? 'A' : 'I';
        char availability = (availabilityGroup.getSelectedToggle() == availableRadio) ? 'A' : 'U';
        
        Book updateBook = new Book();
        updateBook.setBookId(currentBook.getBookId());
        updateBook.setBookTitle(titleField.getText().trim());
        updateBook.setBookAuthor(authorField.getText().trim());
        updateBook.setBookCategory(categoryField.getValue());
        updateBook.setStatus(status);
        updateBook.setAvailability(availability);
        

        try {
            bookService.updateBook(updateBook);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully.");
            handleExit();
        } catch (InvalidInputException e) {
            showAlert(Alert.AlertType.ERROR, "Update Failed", e.getMessage());
        }
    }

    @FXML
    private void handleExit() {
        bookIdField.clear();
        titleField.clear();
        authorField.clear();
        categoryField.setValue(null);
        statusGroup.selectToggle(null);
        availabilityGroup.selectToggle(null);
        currentBook = null;
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
