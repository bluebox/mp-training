package com.libraryManagementSystem.controller;

import java.net.URL;
import java.util.List;
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

public class BookUpdateController implements Initializable {

	BookServices bookService = new BookServicesImpl();

	@FXML
	private ComboBox<Book> books;
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
	private Label bookId;
	@FXML
	private Label error;

	@FXML
	private void switchToBookOptions() throws Exception {
		App.setRoot("BookOptions");
	}

	@FXML
	private void switchToPrimary() throws Exception {
		App.setRoot("primary");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Book> bookTitles;
		try {

			bookTitles = bookService.getBooks();
			books.getItems().addAll(bookTitles);
			category.getItems().addAll(BookCategory.values());
			status.getItems().addAll(BookStatus.values());
			availability.getItems().addAll(BookAvailability.values());

			books.setOnAction(event -> {

				if (!books.getSelectionModel().isEmpty()) {
					bookPropertySetting();
				}
			});

			books.setValue(BooksViewAllController.getBookIdSelected());
			if (!books.getSelectionModel().isEmpty()) {
				bookPropertySetting();
			}
		} catch (InvalidException e) {
			error.setText(e.getMessage());
		}

	}

	@FXML
	public void updateBook() {

		Book book;

		if (!books.getSelectionModel().isEmpty()) {
			book = books.getSelectionModel().getSelectedItem();

			String bookTitle = title.getText();
			String bookAuthor = author.getText();
			BookCategory bookcategory = category.getSelectionModel().getSelectedItem();
			BookStatus bookstatus = status.getSelectionModel().getSelectedItem();
			BookAvailability bookAvailability = availability.getSelectionModel().getSelectedItem();

			Book newBook = new Book(book.getBookId(), bookTitle, bookAuthor, bookcategory, bookstatus,
					bookAvailability);

			if (!(book.equals(newBook))) {
				try {
					bookService.updateBook(newBook, book);
					error.setText(bookTitle + " Book updated Successfully");

					error.setStyle("-fx-text-fill: green");
					title.clear();
					author.clear();

					category.setValue(null);
					status.setValue(null);
					availability.setValue(null);
					books.setValue(null);

				} catch (InvalidException e) {
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

	private void bookPropertySetting() {

		Book book = books.getSelectionModel().getSelectedItem();

		category.setDisable(false);

		category.getSelectionModel().select(book.getCategory());

		status.setDisable(false);

		status.getSelectionModel().select(book.getStatus());

		availability.getSelectionModel().select(book.getAvailability());

		title.setDisable(false);
		title.setText(book.getTitle());

		author.setDisable(false);
		author.setText(book.getAuthor());

		bookId.setText(String.valueOf(book.getBookId()));
	}

}
