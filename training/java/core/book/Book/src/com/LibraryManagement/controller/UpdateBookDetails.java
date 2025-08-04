package com.LibraryManagement.controller;

import com.LibraryManagement.DAO.BookDAO;
import com.LibraryManagement.DAO.Implementation.BookDAOImplementation;
import com.LibraryManagement.service.implementation.BookServiceImplementation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateBookDetails {
	@FXML
	private TextField titleField;
	@FXML
	private TextField authorField;
	@FXML
	private TextField categoryField;
	@FXML
	private ComboBox<String> statusComboBox;

	private com.LibraryManagement.models.Book book;

	public void setBook(com.LibraryManagement.models.Book book) {
		this.book = book;
		titleField.setText(book.getTitle());
		authorField.setText(book.getAuthor());
		categoryField.setText(book.getCategory());
		statusComboBox.getItems().addAll("Active", "Inactive");
		statusComboBox.setValue(book.getStatus().equals("A") ? "Active" : "Inactive");
	}

	@FXML
	public void Back(ActionEvent event) {
		try {
			((Stage) titleField.getScene().getWindow()).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleSave() {
		String title = titleField.getText().trim();
		String author = authorField.getText().trim();
		String category = categoryField.getText().trim();
		String selected = statusComboBox.getValue();

		if (title.isEmpty() || author.isEmpty() || category.isEmpty()) {
			showAlert("Validation Error", "All fields must be filled.");
			return;
		}

		if (selected == null) {
			showAlert("Invalid Selection", "Please choose a status.");
			return;
		}

		// Avoid NPE - safe comparison after null check
		if (book.getTitle().equals(title) && book.getAuthor().equals(author) && book.getCategory().equals(category)
				&& book.getStatus().equals(selected.equals("Active") ? "A" : "I")) {

			showAlert("Validation Error", "No change in the details.");
			return;
		}

		book.setTitle(title);
		book.setAuthor(author);
		book.setCategory(category);
		book.setStatus(selected.equals("Active") ? "A" : "I");

		BookServiceImplementation bookService = new BookServiceImplementation();
		bookService.updateBook(book);

		showAlertu("Update", "Updated Details");

	}

	private void showAlertu(String string, String string2) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(string);
		alert.setContentText(string2);
		alert.setHeight(100);
		alert.setWidth(100);
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.setHeight(100);
		alert.setWidth(100);
		alert.showAndWait();
	}
}