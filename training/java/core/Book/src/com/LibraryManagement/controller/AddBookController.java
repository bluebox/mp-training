package com.LibraryManagement.controller;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.service.implementation.BookServiceImplementation;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

	@FXML
	private TextField title;
	@FXML
	private TextField author;
	@FXML
	private ComboBox<String> category;

	@FXML
	private Label titleErrorLabel, authorErrorLabel, categoryErrorLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.setItems(FXCollections.observableArrayList("Fiction", "Mystery", "Thriller", "Story", "Adventure",
				"Humor", "Science Fiction", "Narrative", "Autobiography"));
	}

	@FXML
	public void AddBook(ActionEvent event) {
		if (!validateForm())
			return;
		String titleText = title.getText().trim();
		String authorText = author.getText().trim();
		String categoryText = category.getValue();

		BookServiceImplementation bookService = new BookServiceImplementation();
		int bookid = bookService.addBook(new Book(titleText, authorText, categoryText));
		showAlertu("Status", "Successfully added Book and generated Book Id : " + bookid);
		clearForm();
	}

	private void clearForm() {
		title.clear();
		author.clear();
		category.setValue(null);

		titleErrorLabel.setText("");
		authorErrorLabel.setText("");
		categoryErrorLabel.setText("");
	}

	@FXML
	public void Back(ActionEvent event) {
		try {
			MainController.switchScene("books/BooksForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validateForm() {
		boolean valid = true;

		titleErrorLabel.setText("");
		authorErrorLabel.setText("");
		categoryErrorLabel.setText("");

		if (title.getText().trim().isEmpty()) {
			titleErrorLabel.setText("Name is required *");

			valid = false;
		}
		if (author.getText().trim().isEmpty()) {
			authorErrorLabel.setText("author is required *");
			valid = false;
		}
		if (category.getValue().isEmpty()) {
			categoryErrorLabel.setText("category is required *");
		}

		return valid;
	}

	private void showAlertu(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
