package com.LibraryManagement.controller;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.models.IssueRecords;
import com.LibraryManagement.service.implementation.BookServiceImplementation;
import com.LibraryManagement.service.implementation.IssueRecordServiceImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateBookAvailability {

	@FXML
	private ComboBox<String> availabilityComboBox;
	@FXML
	private TextField bookIdField;
	@FXML
	private TextField titleField;
	@FXML
	private TextField authorField;
	@FXML
	private TextField categoryField;

	private Book book;

	@FXML
	public void initialize() {
		availabilityComboBox.getItems().addAll("Available", "Issued");
	}

	public void setBook(Book book) {
		this.book = book;
		bookIdField.setText(String.valueOf(book.getBookId()));
		titleField.setText(book.getTitle());
		authorField.setText(book.getAuthor());
		categoryField.setText(book.getCategory());

		String availabilityText = book.getAvailability().equalsIgnoreCase("A") ? "Available" : "Issued";
		availabilityComboBox.setValue(availabilityText);
	}

	@FXML
	private void handleUpdateAvailability() throws Exception {
		String availabilityText = availabilityComboBox.getValue();

		if (availabilityText == null) {
			showAlert("Invalid Selection", "Please select availability status.");
			return;
		}

		String newAvailabilityCode = availabilityText.equals("Available") ? "A" : "I";

		if (book.getAvailability().equalsIgnoreCase(newAvailabilityCode)) {
			showAlert("No Change", "The availability status is already set to the selected value.");
			return;
		}

		// Update the book's availability
		book.setAvailability(newAvailabilityCode);
		BookServiceImplementation bookService = new BookServiceImplementation();
		bookService.updateAvailability(book);

		// Sync issue record if status is set to Available
		if (newAvailabilityCode.equals("A")) {
			IssueRecordServiceImplementation issueDAO = new IssueRecordServiceImplementation();
			IssueRecords activeIssue = issueDAO.getActiveIssueByBookId(book.getBookId());
			if (activeIssue != null) {
				issueDAO.returnBook(activeIssue.getIssueId());
			}
		}

		showInfo("Updated", "Book availability updated successfully.");
		((Stage) availabilityComboBox.getScene().getWindow()).close();
	}

	@FXML
	public void Back(ActionEvent event) {
		try {
			((Stage) titleField.getScene().getWindow()).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showInfo(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
