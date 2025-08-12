package com.libraryManagementSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.BookNotFoundException;
import com.libraryManagementSystem.exceptions.DatabaseOperationException;
import com.libraryManagementSystem.exceptions.InvalidBookDataException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.BookStatus;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BooksViewAllController implements Initializable {

	@FXML
	private TableView<Book> bookTableView;
	@FXML
	private TableColumn<Book, Integer> idColumn;
	@FXML
	private TableColumn<Book, String> titleColumn;
	@FXML
	private TableColumn<Book, String> authorColumn;
	@FXML
	private TableColumn<Book, BookCategory> categoryColumn;
	@FXML
	private TableColumn<Book, BookStatus> statusColumn;
	@FXML
	private TableColumn<Book, BookAvailability> availabilityColumn;
	@FXML
	private TableColumn<Book, Void> actionsColumn;
	@FXML
	private Label error;

	private final BookServices bookService = new BookServicesImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setupTable();
		loadBooks();
	}

	private void setupTable() {
		bookTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

		wrapTextColumn(titleColumn);
		wrapTextColumn(authorColumn);

		addActionButtons();
	}

	private void wrapTextColumn(TableColumn<Book, String> column) {
		column.setCellFactory(col -> new TableCell<Book, String>() {
			private final Text text = new Text();

			{
				text.wrappingWidthProperty().bind(col.widthProperty().subtract(10));
				setGraphic(text);
			}

			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				text.setText(empty || item == null ? "" : item);
			}
		});
	}

	private void loadBooks() {
		try {
			List<Book> books = bookService.getBooks();
			ObservableList<Book> bookList = FXCollections.observableArrayList(books);
			bookTableView.setItems(bookList);
		} catch (DatabaseOperationException e) {
			showError(e.getMessage());
		}
	}

	private void addActionButtons() {
		actionsColumn.setCellFactory(col -> new TableCell<Book, Void>() {
			private final Button deleteButton = new Button("Delete");
			private final HBox actionBox = new HBox(10, deleteButton);

			{
				deleteButton.setOnAction(event -> {
					Book bookData = bookTableView.getItems().get(getIndex());

					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
					alert.setTitle("Delete Book");
					alert.setHeaderText("Delete item: " + bookData.getTitle());
					alert.setContentText("Are you sure?");

					Optional<ButtonType> result = alert.showAndWait();

					if (result.isPresent() && result.get() == ButtonType.OK) {
						try {
							bookService.deleteBook(bookData);
							loadBooks();
							showSuccess(bookData.getTitle() + " deleted successfully.");
						} catch (BookNotFoundException | DatabaseOperationException | InvalidBookDataException e) {
							showError(e.getMessage());
						}
					}
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setGraphic(empty ? null : actionBox);
			}
		});
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

	@FXML
	public void switchToBack() throws IOException {
		App.setRoot("BookOptions");
	}

	@FXML
	public void switchToHome() throws IOException {
		App.setRoot("primary");
	}
}
