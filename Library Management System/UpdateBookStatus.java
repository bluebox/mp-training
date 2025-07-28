package com.LibraryManagement.controller;

import com.LibraryManagement.DAO.Implementation.BookDAOImplementation;
import com.LibraryManagement.models.Book;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateBookStatus {

	@FXML
	private TextField bookIdField;
	@FXML
	private TextField titleField;
	@FXML
	private TextField authorField;
	@FXML
	private TextField categoryField;
	@FXML
	private ComboBox<String> statusComboBox;

	private Book book;

	public void setBook(Book book) {
		this.book = book;

		bookIdField.setText(String.valueOf(book.getBookId()));
		titleField.setText(book.getTitle());
		authorField.setText(book.getAuthor());
		categoryField.setText(book.getCategory());

		statusComboBox.getItems().addAll("Active", "Inactive");
		statusComboBox.setValue(book.getStatus().equals("A") ? "Active" : "Inactive");
	}

	@FXML
	private void handleUpdateStatus() {
		String selected = statusComboBox.getValue();
		if (selected == null) {
			showAlert("Invalid Selection", "Please choose a status.");
			return;
		}

		String statusCode = selected.equals("Active") ? "A" : "I";
		if (book.getStatus().equals(statusCode)) {
			showAlert("Validation Error", "No change in the Status");
			return;
		}
		book.setStatus(statusCode);

		BookDAOImplementation bookDAO = new BookDAOImplementation();
		bookDAO.updateStatus(book);
		showAlertu("Update", "Updated Status");

		((Stage) statusComboBox.getScene().getWindow()).close();
	}

	private void showAlertu(String string, String string2) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(string);
		alert.setContentText(string2);
	    alert.getDialogPane().setPrefSize(300, 150); 
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	private void showAlert(String title, String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
}
