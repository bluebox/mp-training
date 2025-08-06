package com.lms.controller;

import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.serviceImpl.BookServiceImpl;
import com.lms.util.Validator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddBookController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private ComboBox<BookCategory> categoryField = new ComboBox<>();

    @FXML
    public void initialize() {
        categoryField.getItems().addAll(BookCategory.values());
    }

    @FXML
    public void handleAddBook() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        BookCategory category = categoryField.getValue(); 
        BookServiceImpl bookService = new BookServiceImpl();
        try {
        
        	Book newBook = new Book();
        	newBook.setBookTitle(title);
        	newBook.setBookAuthor(author);
        	newBook.setBookCategory(category);
        	newBook.setStatus('A');
        	newBook.setAvailability('A');
        	Validator.validateBook(newBook);
        	bookService.addBook(newBook);

        }
        catch (Exception e) {
        showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
			return;
		}
		showAlert(Alert.AlertType.INFORMATION, "Book Created", "The book has been created successfully.");
         
            handleExit();

    }
    
    private void clearForm() {
        titleField.clear();
        authorField.clear();
        categoryField.setValue(null);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void handleExit() {
    	clearForm();
    }
}