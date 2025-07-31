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
             BookServiceImpl bookService = new BookServiceImpl();
        try {
        
        	Book newBook = new Book();
        	newBook.setBookTitle(titleField.getText().trim());
        	newBook.setBookAuthor(authorField.getText().trim());
        	newBook.setBookCategory(categoryField.getValue());
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
         
            clearForm();

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

   }