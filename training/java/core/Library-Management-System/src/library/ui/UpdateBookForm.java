package library.ui;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.exception.LibraryException;
import library.model.Book;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.service.BookServiceImpl;

public class UpdateBookForm {

	@FXML
	private TextField bookIdField;
	@FXML
	private TextField titleField;
	@FXML
	private TextField authorField;
	@FXML
	private ComboBox<String> categoryComboBox;
	@FXML
	private ComboBox<String> statusComboBox;
	@FXML
	private Label messageLabel;

	private BookServiceImpl bookService;
	private Book currentBook;
	private final String CURRENT_USER = "ADMIN";

	public UpdateBookForm() {
		this.bookService = new BookServiceImpl();
	}

	@FXML
	private void initialize() {
		// Populate Category ComboBox
		categoryComboBox.setItems(FXCollections.observableArrayList());
		for (BookCategory category : BookCategory.values()) {
			categoryComboBox.getItems().add(category.getDisplayName());
		}
		// Populate Status ComboBox
		statusComboBox.setItems(
				FXCollections.observableArrayList(BookStatus.ACTIVE.toString(), BookStatus.INACTIVE.toString()));
	}

	public void setBook(Book book) {
		this.currentBook = book;
		if (book != null) {
			bookIdField.setText(String.valueOf(book.getBookId()));
			titleField.setText(book.getTitle());
			authorField.setText(book.getAuthor());
			categoryComboBox.getSelectionModel().select(book.getCategory().getDisplayName());
			statusComboBox.getSelectionModel().select(book.getStatus().toString());
		}
	}

	@FXML
	private void handleUpdateBook(ActionEvent event) {
		messageLabel.setText("");
		messageLabel.setTextFill(javafx.scene.paint.Color.RED); 

		if (currentBook == null) {
			messageLabel.setText("Error: No book selected for update.");
			return;
		}

		String title = titleField.getText();
		String author = authorField.getText();
		String categoryDisplayName = categoryComboBox.getSelectionModel().getSelectedItem();
		String statusSelectedString = statusComboBox.getSelectionModel().getSelectedItem();

		if (title.isEmpty() || author.isEmpty() || categoryDisplayName == null || statusSelectedString == null) {
			messageLabel.setText("Please fill in all fields.");
			return;
		}

		try {
			BookCategory bookCategory = BookCategory.fromDisplayName(categoryDisplayName);
			BookStatus bookStatus = BookStatus.valueOf(statusSelectedString);

			currentBook.setTitle(title);
			currentBook.setAuthor(author);
			currentBook.setCategory(bookCategory);
			currentBook.setStatus(bookStatus);

			boolean success = bookService.updateBook(currentBook, CURRENT_USER);
			if (success) {
				messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
				messageLabel.setText("Book ID " + currentBook.getBookId() + " updated successfully!");
			} else {
				messageLabel.setText(
						"Failed to update Book ID " + currentBook.getBookId() + ". No changes made to existing book.");
			}
		} catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) { 
				messageLabel.setText("Error: A book with the same title and category already exists.");
			} else {
				messageLabel.setText(e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void handleBackToViewBooks(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ViewBooksScreen.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root, 900,600);
			stage.setScene(scene);
			stage.setTitle("Library Management System - View All Books");
			stage.show();
		} catch (IOException e) {
			messageLabel.setText("Error navigating back to view books: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred during navigation: " + e.getMessage());
			e.printStackTrace();
		}
	}
}