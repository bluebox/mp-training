package com.library.controller;

import com.library.domain.Book;
import com.library.service.impl.LibraryServiceImpl;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	private Button backButton;

	@FXML
	public void initialize() {
		statusChoiceBox.getItems().addAll("A - Active", "I - Inactive");
		availabilityChoiceBox.getItems().addAll("A - Available", "I - Issued");

		titleField.textProperty().addListener((obs, oldValue, newValue) -> validateForm());
		authorField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());
		categoryField.textProperty().addListener((obs, oldValue, newValue) -> validateForm());
		statusChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldValue, newValue) -> validateForm());
		availabilityChoiceBox.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldValue, newValue) -> validateForm());

		submitButton.setDisable(true);
		submitButton.setOnAction(e -> handleSubmit());
	}

	@FXML
	public void validateForm() {
		boolean allFieldsEntered = !titleField.getText().trim().isEmpty() && !authorField.getText().trim().isEmpty()
				&& !categoryField.getText().trim().isEmpty() && statusChoiceBox.getValue() != null
				&& availabilityChoiceBox.getValue() != null;

		submitButton.setDisable(!allFieldsEntered);
	}

	@FXML
	private void handleSubmit() {
		String title = titleField.getText().trim();
		String author = authorField.getText().trim();
		String category = categoryField.getText().trim();
		String status = statusChoiceBox.getValue().substring(0, 1);
		String availability = availabilityChoiceBox.getValue().substring(0, 1);

		Book book = new Book(title, author, category, status, availability);
		LibraryServiceImpl bookService = new LibraryServiceImpl();
		boolean success = bookService.addBook(book);

		Stage stage = (Stage) submitButton.getScene().getWindow();

		if (success) {
			showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully.");
			clearFormFields();
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Failed to add book.");
			stage.centerOnScreen();
		}
	}

	private void clearFormFields() {
		titleField.clear();
		authorField.clear();
		categoryField.clear();
		statusChoiceBox.setValue(null);
		availabilityChoiceBox.setValue(null);
		submitButton.setDisable(true);
	}

	private void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	private void handleBack() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/Home.fxml"));
			Parent root = loader.load();
			Stage stage = (Stage) backButton.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.setTitle("Library System - Home");
			stage.centerOnScreen();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
