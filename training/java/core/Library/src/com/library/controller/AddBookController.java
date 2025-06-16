package com.library.controller;

import com.library.dao.Book;
import com.library.enums.Availability;
import com.library.enums.Status;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class AddBookController {
	@FXML
	private TextField idField;
	@FXML
    private TextField titleField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField categoryField;
    private final com.library.service.BookService bookService = new com.library.service.BookService();
    @FXML
    private void handleAddBook() {
    	int bookId=Integer.parseInt(idField.getText());
        String title = titleField.getText();
        String author = authorField.getText();
        String category = categoryField.getText();
        if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
            showAlert("All fields are required.");
            return;
        }
        try {
            Book book = new Book(bookId,title,author,category,Status.Active,Availability.Available.Available);
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
