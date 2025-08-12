package com.libraryManagementSystem.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.DuplicateBookException;
import com.libraryManagementSystem.exceptions.InvalidBookDataException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.Validations;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.Duration;

public class BookUpdateController implements Initializable {

	private final BookServices bookService = new BookServicesImpl();

	private ObservableList<Book> allBooks;

	@FXML
	private TextField bookSearchField;
	@FXML
	private ComboBox<String> category;
	@FXML
	private TextField title;
	@FXML
	private TextField author;
	@FXML
	private Label bookId;
	@FXML
	private Label error;

	private ContextMenu suggestionsPopup = new ContextMenu();
	private Book selectedBook = null;

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
		try {
			allBooks = FXCollections.observableArrayList(bookService.getBooks().stream()
					.filter(book -> book.getAvailability().equals(BookAvailability.AVAILABLE))
					.collect(Collectors.toList()));
			category.getItems().clear();
			category.getItems().addAll(Stream.of(BookCategory.values()).map(category -> category.getCategory())
					.collect(Collectors.toList()));

			setupSearch();

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

		} catch (DatabaseOperationException e) {
			showError("Error loading books: " + e.getMessage());
		}
	}

	private void setupSearch() {
		bookSearchField.textProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal == null || newVal.isEmpty()) {
				suggestionsPopup.hide();
				selectedBook = null;
				clearFormFieldsOnly();
				return;
			}

			String filter = newVal.toLowerCase();
			List<Book> matches = allBooks.stream().filter(
					b -> b.getTitle().toLowerCase().contains(filter) || b.getAuthor().toLowerCase().contains(filter))
					.collect(Collectors.toList());

			Book exactMatch = allBooks.stream()
					.filter(b -> (b.getTitle() + " - " + b.getAuthor()).equalsIgnoreCase(newVal)).findFirst()
					.orElse(null);

			if (exactMatch != null) {
				selectedBook = exactMatch;
				fillBookDetails(exactMatch);
			} else {
				selectedBook = null;
				clearFormFieldsOnly();
			}

			if (matches.isEmpty()) {
				suggestionsPopup.hide();
			} else {
				populateSuggestions(matches);
				if (!suggestionsPopup.isShowing()) {
					suggestionsPopup.show(bookSearchField, javafx.geometry.Side.BOTTOM, 0, 0);
				}
			}
		});
	}

	private void populateSuggestions(List<Book> matches) {
		suggestionsPopup.getItems().clear();
		for (Book b : matches) {
			MenuItem item = new MenuItem(b.getTitle() + " - " + b.getAuthor());
			item.setOnAction(e -> {
				selectedBook = b;
				bookSearchField.setText(b.getTitle() + " - " + b.getAuthor());
				fillBookDetails(b);
				suggestionsPopup.hide();
			});
			suggestionsPopup.getItems().add(item);
		}
	}

	private void fillBookDetails(Book book) {
		category.setDisable(false);
		category.getSelectionModel().select(book.getCategory().getCategory());

		title.setDisable(false);
		title.setText(book.getTitle());

		author.setDisable(false);
		author.setText(book.getAuthor());

		bookId.setText(String.valueOf(book.getBookId()));
	}

	@FXML
	public void updateBook() {
		if (selectedBook == null) {
			showError("Please select a book from suggestions.");
			return;
		}

		String bookTitle = title.getText().trim();
		String bookAuthor = author.getText().trim();
		BookCategory bookCategory = BookCategory.fromDisplayName(category.getSelectionModel().getSelectedItem());

		if (bookTitle.isEmpty() || bookAuthor.isEmpty() || bookCategory == null) {
			showError("All fields must be filled out.");
			return;
		}

		Book newBook = new Book(selectedBook.getBookId(), bookTitle, bookAuthor, bookCategory);

		try {
			bookService.updateBook(newBook, selectedBook);
			clearForm();
			showSuccess(bookTitle + " updated successfully.");
			initialize(null, null);

		} catch (BookNotFoundException | InvalidBookDataException | DatabaseOperationException
				| DuplicateBookException e) {
			showError("Update failed: " + e.getMessage());
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
		error.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
		PauseTransition pause = new PauseTransition(Duration.seconds(3));
		pause.setOnFinished(event -> error.setText(""));
		pause.play();
	}

	private void clearForm() {
		clearFormFieldsOnly();
		bookSearchField.clear();
		selectedBook = null;
	}

	private void clearFormFieldsOnly() {
		title.clear();
		author.clear();
		category.setValue(null);
		bookId.setText("");
	}

}
