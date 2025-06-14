package com.library.controller;

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

		String title = titleField.getText();
		String author = authorField.getText();
		String category = categoryField.getText();
		String status = statusChoiceBox.getValue();
		String availability = availabilityChoiceBox.getValue();

		System.out.println("Book Info:");
		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		System.out.println("Category: " + category);
		System.out.println("Status: " + status);
		System.out.println("Availability: " + availability);
	}
}
