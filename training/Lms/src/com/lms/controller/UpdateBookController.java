package com.lms.controller;


import com.lms.daoImpl.BookDao;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.service.BookService;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.util.Validator;

import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class UpdateBookController {

    @FXML
    private TextField bookIdField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private ComboBox<BookCategory> categoryField = new ComboBox<>();

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    private ToggleGroup availabilityGroup;

    @FXML
    private RadioButton statusActive;

    @FXML
    private RadioButton statusInactive;

    @FXML
    private RadioButton availableRadio;

    @FXML
    private RadioButton unavailableRadio;

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
        
        Book currentBook=null;
		try {
			currentBook = bookService.getBookById(id);
		} catch (InvalidInputException e) {
            showAlert(Alert.AlertType.ERROR, "Book Not Found", "No book found with ID: " + id);

		}


   	 

        // Populate fields
        titleField.setText(currentBook.getBookTitle());
        authorField.setText(currentBook.getBookAuthor());
        categoryField.setValue(currentBook.getBookCategory());

        // Status toggle
        if (currentBook.getStatus() == 'A') {
            statusGroup.selectToggle(statusActive);
        } else {
            statusGroup.selectToggle(statusInactive);
        }

        // Availability toggle
        if (currentBook.getAvailability() == 'A') {
            availabilityGroup.selectToggle(availableRadio);
        } else {
            availabilityGroup.selectToggle(unavailableRadio);
        }
    }

    @FXML
    private void updateBookDetails() {
        if (currentBook == null) {
            showAlert(Alert.AlertType.WARNING, "No Book Loaded", "Please fetch a book before updating.");
            return;
        }

        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        BookCategory category = categoryField.getValue();

        if (title.isEmpty() || author.isEmpty() || category == null) {
            showAlert(Alert.AlertType.WARNING, "Missing Fields", "All fields must be filled.");
            return;
        }

        RadioButton selectedStatus = (RadioButton) statusGroup.getSelectedToggle();
        RadioButton selectedAvailability = (RadioButton) availabilityGroup.getSelectedToggle();

        char status = (selectedStatus != null && "Active".equalsIgnoreCase(selectedStatus.getText())) ? 'A' : 'I';
        char availability = (selectedAvailability != null && "Available".equalsIgnoreCase(selectedAvailability.getText())) ? 'A' : 'U';

//        boolean updated = BookDao.getInstance().updateBook(currentBook.getBookId(), title, author, category, status, availability);
        Boolean updated;
		try {
			updated = bookService.updateBook(currentBook.getBookId(), title, author, category, status, availability);
		} catch (InvalidInputException e) {
            showAlert(Alert.AlertType.ERROR, "Update Failed", "An error occurred while updating.");
            return;
		}
        
        if (updated) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book updated successfully.");
            handleExit();
        } else {
            showAlert(Alert.AlertType.ERROR, "Update Failed", "An error occurred while updating.");
        }
    }

    @FXML
    private void handleExit() {
        //Platform.exit(); // You can change this to just clear the form if needed
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
