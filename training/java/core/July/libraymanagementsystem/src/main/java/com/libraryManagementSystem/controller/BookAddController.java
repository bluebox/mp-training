package com.libraryManagementSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.DuplicateBookException;
import com.libraryManagementSystem.exceptions.InvalidBookDataException;
import com.libraryManagementSystem.exceptions.InvalidEnumValueException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.Validations;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.Duration;

public class BookAddController implements Initializable {

	private final BookServices bookService = new BookServicesImpl();

	@FXML
	private ComboBox<String> category;
	@FXML
	private TextField title;
	@FXML
	private TextField author;
	@FXML
	private Label error;

	@FXML
	private void switchToBack() throws IOException {

		App.setRoot("BookOptions");
	}

	@FXML
	private void switchToHome() throws IOException {

		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		title.setTextFormatter(new TextFormatter<>(change -> {
			error.setText("");
			if (Validations.isValidTitle(change.getControlNewText())) {
				return change;
			}
			showError("Please use only letters, numbers, and allowed punctuation in the title.");
			return null;
		}));

		author.setTextFormatter(new TextFormatter<>(change -> {
			error.setText("");
			if (Validations.isValidName(change.getControlNewText())) {
				return change;
			}
			showError("Please use only letters and spaces for the author's name.");
			return null;
		}));

		category.getItems().addAll(
				Stream.of(BookCategory.values()).map(category -> category.getCategory()).collect(Collectors.toList()));

	}

	@FXML
	public void submit() {
		String bookTitle = title.getText();
		String bookAuthor = author.getText();
		String bookCategory = category.getSelectionModel().getSelectedItem();

		Book newBook;

		try {
			newBook = new Book(bookTitle, bookAuthor, BookCategory.fromDisplayName(bookCategory));
		} catch (InvalidEnumValueException e) {
			showError(e.getMessage() + " before adding a book");
			return;
		}

		try {
			bookService.addBook(newBook);
			clearForm();
			showSuccess(bookTitle + " Book added successfully");

		} catch (InvalidBookDataException | DuplicateBookException | DatabaseOperationException e) {
			showError(e.getMessage());
		}
	}

	private void showError(String message) {
		error.setText(message);
		error.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(event -> error.setText(""));
		pause.play();
	}

	private void showSuccess(String message) {
		error.setText(message);
		error.setStyle("-fx-text-fill: green");

		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(event -> error.setText(""));
		pause.play();
	}

	private void clearForm() {
		title.clear();
		author.clear();

		category.setValue(null);
		category.setPromptText("Select Option");
	}

}
