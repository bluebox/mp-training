package com.library.controller;

import com.library.domain.Book;
import com.library.service.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddBookController {

	@FXML
	private TextField titleField;
	@FXML
	private TextField authorField;
	@FXML
	private TextField categoryField;
	@FXML
	private ChoiceBox<String> statusChoiceBox;
	@FXML
	private ChoiceBox<String> availabilityChoiceBox;
	@FXML
	private Button submitButton;

	@FXML
	public void initialize() {
		statusChoiceBox.getItems().addAll("A - Active", "I - Inactive");
		availabilityChoiceBox.getItems().addAll("A - Available", "I - Issued");

		titleField.textProperty().addListener((obs, oldvalue, newvalue) -> validateForm());
		authorField.textProperty().addListener((obs, oldval, newval) -> validateForm());
		categoryField.textProperty().addListener((obs, oldValue, newValue) -> validateForm());
		statusChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldValue, newValue) -> validateForm());
		availabilityChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldValue, newValue) -> validateForm());
		
		submitButton.setDisable(true);

		submitButton.setOnAction(e -> handleSubmit());
	}
	
	@FXML
	public void validateForm()
    {
		boolean allFieldsEntered=!titleField.getText().trim().isEmpty() 
				&& !authorField.getText() .trim().isEmpty()
				&& !categoryField.getText().trim().isEmpty()
				&& statusChoiceBox.getValue() !=null
				&& availabilityChoiceBox.getValue() !=null;
		submitButton.setDisable(!allFieldsEntered);


  }
	@FXML
	private void handleSubmit() {
		String title = titleField.getText().trim();
		String author = authorField.getText().trim();
		String category = categoryField.getText().trim();
		String status = statusChoiceBox.getValue().substring(0, 1);       // Extract "A" or "I"
		String availability = availabilityChoiceBox.getValue().substring(0, 1); // Extract "A" or "I"

		Book book = new Book(title, author, category, status, availability);
		BookService bookService = new BookService();
		boolean success = bookService.addBook(book);

		if (success) {
			System.out.println("Book successfully added to DB.");
		} else {
			System.out.println("Failed to add book.");
		}
	}

}
