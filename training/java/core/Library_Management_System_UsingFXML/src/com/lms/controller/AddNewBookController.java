package com.lms.controller;



import java.io.IOException;

import com.lms.Utils.ControllerUtil;
import com.lms.exceptions.DBConstrainsException;
import com.lms.exceptions.EmptyFieldsException;
import com.lms.main.Main;
import com.lms.model.Book;
import com.lms.service.BookService;
import com.lms.service.impl.BookServiceImpl;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddNewBookController {
	
	BookService bookService;
	

	@FXML
	TextField titleField;
	@FXML
	TextField authorField;
	@FXML
	ComboBox<String> categoryComboBox;
	@FXML
	Label errorLabel;
	
	@FXML
	public void initialize() {
		bookService = new BookServiceImpl();

		categoryComboBox.getItems().addAll(
	            "Fiction",
	            "Non-Fiction",
	            "Science",
	            "Technology",
	            "History",
	            "Biography"
	            
	        );
	}

	@FXML
	public void submitButtonClick() throws EmptyFieldsException{
		try {
			String title = titleField.getText();
			String author = authorField.getText();
			String category = categoryComboBox.getValue();
			
			Book newBook = new Book(title,author,category);
			bookService.addNewBook(newBook);
			
			titleField.setText("");
			authorField.setText("");
			categoryComboBox.getSelectionModel().clearSelection();
			errorLabel.setText("");
			
			ControllerUtil.createAlert(AlertType.CONFIRMATION, "Success", "Added book", "Successfully added " + title);
			
		}  catch (NullPointerException | DBConstrainsException e) {
			ControllerUtil.createAlert(AlertType.INFORMATION, "Error", "Add book Failed", e.getMessage());
		}
	}
	
	@FXML
	public void cancleButtonClick() throws IOException {
		Main.changePage("LibraryHome"); 
	}
}
