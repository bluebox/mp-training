package com.libraryManagementSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.BookStatus;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BookAddController implements Initializable {

	BookServices bookService = new BookServicesImpl();

	@FXML
	private ComboBox<BookCategory> category;
	@FXML
	private ComboBox<BookStatus> status;
	@FXML
	private ComboBox<BookAvailability> availability;
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

		category.getItems().addAll(BookCategory.values());
		status.getItems().addAll(BookStatus.values());
		availability.getItems().addAll(BookAvailability.values());

	}

	@FXML
	public void submit() {

		String bookTitle = title.getText();
		String bookAuthor = author.getText();
		BookCategory bookcategory = category.getSelectionModel().getSelectedItem();
		BookStatus bookstatus = status.getSelectionModel().getSelectedItem();
		BookAvailability bookAvailability = availability.getSelectionModel().getSelectedItem();

		Book newBook = new Book(-1, bookTitle, bookAuthor, bookcategory, bookstatus, bookAvailability);

		try {
			bookService.addBook(newBook);
			error.setText(bookTitle + " Book added Successfully");

			error.setStyle("-fx-text-fill: green");
			title.clear();
			author.clear();

			category.setValue(null);
			status.setValue(null);
			availability.setValue(null);

			category.setPromptText("Select Option");
			status.setPromptText("CheckStatus");
			availability.setPromptText("CheckAvailability");

		} catch (InvalidException e) {
			error.setText(e.getMessage());
			error.setStyle("-fx-text-fill: red");
		}

	}

}
