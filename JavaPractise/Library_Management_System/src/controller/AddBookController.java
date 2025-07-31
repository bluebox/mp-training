package controller;

import dao.BookDao;
import domain.Book;
import domain.Status;
import domain.Category;
import domain.AvailabilityStatus;
import exceptions.ManagementException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.BookService;
import service.BookServiceInterface;

public class AddBookController {

	@FXML
	private TextField titleField;
	@FXML
	private TextField authorField;
	@FXML
	private TextField categoryField;
	@FXML
	private ComboBox<Category> categoryCombo;
	@FXML
	private ComboBox<Status> statusCombo;
	@FXML
	private ComboBox<AvailabilityStatus> availabilityCombo;

	private final BookServiceInterface bookService = new BookService(new BookDao());

	@FXML
	public void initialize() {
		statusCombo.setItems(FXCollections.observableArrayList(Status.values()));
		availabilityCombo.setItems(FXCollections.observableArrayList(AvailabilityStatus.values()));
		categoryCombo.setItems(FXCollections.observableArrayList(Category.values()));
	}

	@FXML
	private void handleAddBook() {
		try {
			String title = titleField.getText().trim();
			String author = authorField.getText().trim();
			Category category = categoryCombo.getValue();
			Status status = statusCombo.getValue();
			AvailabilityStatus availability = availabilityCombo.getValue();

			if (title.isEmpty() || author.isEmpty() || category == null || status == null || availability == null) {
				showAlert("Error", "Please fill all fields.");
				return;
			}
			Book book = new Book();

			book.setTitle(title);
			book.setAuthor(author);
			book.setCategory(category);
			book.setStatus(status);
			book.setAvailability(availability);
			book.setAddedBy("Akash");

			bookService.addBook(book);

			showAlert("Success", "Book added successfully.");
			clearFields();
		} catch (ManagementException e) {
			showAlert("Database Error", e.getMessage());
		} catch (Exception e) {
			showAlert("Error", "Unexpected error: " + e.getMessage());
		}
	}

	@FXML
	private void handleBack() throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/resources/BookManagement.fxml"));
		Stage stage = (Stage) titleField.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void clearFields() {
		titleField.clear();
		authorField.clear();
		categoryCombo.getSelectionModel().clearSelection();
		statusCombo.getSelectionModel().clearSelection();
		availabilityCombo.getSelectionModel().clearSelection();
	}
}
