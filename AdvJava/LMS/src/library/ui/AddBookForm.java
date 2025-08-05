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
import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import library.service.BookServiceImpl;
import library.service.interfaces.BookService;

public class AddBookForm {

	@FXML
	private TextField titleField;
	@FXML
	private TextField authorField;
	@FXML
	private ComboBox<String> categoryComboBox;
	@FXML
	private ComboBox<String> statusComboBox;
	@FXML
	private ComboBox<String> availabilityComboBox;
	@FXML
	private Label messageLabel;

	private BookService bookServiceI;
	private final String CURRENT_USER = "ADMIN";

	public AddBookForm() {
		this.bookServiceI= new BookServiceImpl();
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

		// Populate Availability ComboBox 
		availabilityComboBox.setItems(FXCollections.observableArrayList(BookAvailability.AVAILABLE.toString(),
				BookAvailability.ISSUED.toString()));
	}

	@FXML
	private void handleAddBook(ActionEvent event) {
		messageLabel.setText("");
		messageLabel.setTextFill(javafx.scene.paint.Color.RED);

		String title = titleField.getText();
		String author = authorField.getText();
		String categoryDisplayName = categoryComboBox.getSelectionModel().getSelectedItem();
		String statusSelectedString = statusComboBox.getSelectionModel().getSelectedItem();
		String availabilitySelectedString = availabilityComboBox.getSelectionModel().getSelectedItem();

		if (title.isEmpty() || author.isEmpty() || categoryDisplayName == null || statusSelectedString == null
				|| availabilitySelectedString == null) {
			messageLabel.setText("Please fill in all fields.");
			return;
		}

		try {
			BookCategory bookCategory = BookCategory.fromDisplayName(categoryDisplayName);
			BookStatus bookStatus = BookStatus.valueOf(statusSelectedString);
			BookAvailability bookAvailability = BookAvailability.valueOf(availabilitySelectedString);

			Book newBook = new Book(title, author, bookCategory, bookStatus, bookAvailability);

			bookServiceI.addBook(newBook, CURRENT_USER);
			
			messageLabel.setTextFill(javafx.scene.paint.Color.GREEN);
			messageLabel.setText("Book '" + title + "' added successfully!");
			
			titleField.clear();
			authorField.clear();
			categoryComboBox.getSelectionModel().clearSelection();
			statusComboBox.getSelectionModel().clearSelection();
			availabilityComboBox.getSelectionModel().clearSelection();
			
		} catch (LibraryException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) { 
				messageLabel.setText("Error: A book with the same title and category already exists.");
			} else {
				messageLabel.setText(e.getMessage());
//				messageLabel.setText("Database Error: " + e.getMessage());
				System.err.println(e.getMessage());
			}
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private void handleBackToMainMenu(ActionEvent event) {
		try {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScreen.fxml"));
			Parent root = loader.load();

			Scene scene = new Scene(root, 900,600);
			stage.setScene(scene);
			stage.setTitle("Library Management System - Main Menu");
			stage.show();
		} catch (IOException e) {
			messageLabel.setText("Error navigating back to main menu: " + e.getMessage());
			System.err.println("Error navigating back to main menu: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			messageLabel.setText("An unexpected error occurred during navigation: " + e.getMessage());
			e.printStackTrace();
		}
	}
}