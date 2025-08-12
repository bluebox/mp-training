package com.libraryManagementSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.utilities.BookAvailability;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class BookAvailabilityController implements Initializable {

	BookServices bookService = new BookServicesImpl();
	@FXML
	private ComboBox<Book> books;
	@FXML
	private ComboBox<BookAvailability> availability;
	@FXML
	private Label error;

	@FXML
	private void switchToBookOptions() throws Exception {
		App.setRoot("BookOptions");
	}

	@FXML
	private void switchToPrimary() throws IOException {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Book> bookTitles;
		try {
			bookTitles = bookService.getBooks();
			books.getItems().addAll(bookTitles);
			availability.getItems().addAll(BookAvailability.values());

			books.setOnAction(event -> {

				if (!books.getSelectionModel().isEmpty()) {
					Book book = books.getSelectionModel().getSelectedItem();
					availability.setDisable(false);
					availability.getSelectionModel().select(book.getAvailability());
				}
			});
		} catch (DatabaseOperationException e) {

			error.setText(e.getMessage());
		}

	}

	@FXML
	public void updateBook() {
		Book book;

		if (!books.getSelectionModel().isEmpty()) {
			book = books.getSelectionModel().getSelectedItem();
			BookAvailability bookAvailability = availability.getSelectionModel().getSelectedItem();

			if (!(book.getAvailability().equals(bookAvailability))) {
				try {
					bookService.updateBookAvailability(book, bookAvailability);
					error.setText(book.getTitle() + " Book  Availability updated Successfully");

					error.setStyle("-fx-text-fill: green");
					availability.setValue(null);
					books.setValue(null);

				} catch (BookNotFoundException | DatabaseOperationException e) {
					error.setText(e.getMessage());
					error.setStyle("-fx-text-fill: red");
				}
			} else {
				error.setText("Please edit at least one Field");
				error.setStyle("-fx-text-fill: red");
			}

		} else {
			error.setText("Please Select Book Frist");
			error.setStyle("-fx-text-fill: red");
		}
	}
}
