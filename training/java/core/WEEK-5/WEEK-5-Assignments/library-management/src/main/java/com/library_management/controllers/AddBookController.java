package com.library_management.controllers;
import java.io.IOException;

import com.library_management.App;

import com.library_management.domain.BookCategory;
import com.library_management.exceptions.DatabaseException;
import com.library_management.exceptions.InvalidDetailsException;
import com.library_management.services.implementation.BookServiceImplementation;
import com.library_management.utilities.ResponseHandler;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddBookController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField authorField;

    @FXML
    private ComboBox<BookCategory> categoryComboBox;
    
    @FXML
    private Text message;

    @FXML
    public void initialize() {
    	categoryComboBox.getItems().addAll(BookCategory.values());
    }

    public void clearForm() {
    	titleField.setText("");
		authorField.setText("");
		categoryComboBox.setValue(null);
    }
    
    @FXML
    private void backButton() throws IOException {
        App.setRoot("bookOptions");
    }
	
	@FXML
	private void homeButton() throws IOException{
		App.setRoot("home");
	}
   
   
    // Methods to handle events
    @FXML
    public void addBook() throws InterruptedException {
    	String title=titleField.getText();
    	String author=authorField.getText();
    	BookCategory category=categoryComboBox.getSelectionModel().getSelectedItem();
    	try {
			new BookServiceImplementation().validateAddBook(title, author, category);
			clearForm();
			ResponseHandler.showResponse(message, "Book Added Succesfully...", Color.GREEN);
		} catch (InvalidDetailsException|DatabaseException e) {
			ResponseHandler.showResponse(message, e.getMessage(), Color.RED);
		}
    }

}

