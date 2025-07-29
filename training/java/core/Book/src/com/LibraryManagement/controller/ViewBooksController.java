package com.LibraryManagement.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.LibraryManagement.models.Book;
import com.LibraryManagement.service.implementation.BookServiceImplementation;

public class ViewBooksController {

	@FXML
	private TableView<com.LibraryManagement.models.Book> bookTable;
	@FXML
	private TableColumn<Book, Integer> bookIdCol;
	@FXML
	private TableColumn<com.LibraryManagement.models.Book, String> titleCol;
	@FXML
	private TableColumn<Book, String> authorCol;
	@FXML
	private TableColumn<Book, String> categoryCol;
	@FXML
	private TableColumn<Book, String> statusCol;
	@FXML
	private TableColumn<Book, String> availabilityCol;
	@FXML
	private TableColumn<Book, Void> actionsCol;

	private final BookServiceImplementation bookService = new BookServiceImplementation();

	@FXML
	public void Back(ActionEvent event) {
		try {
			MainController.switchScene("books/BooksForm.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		bookIdCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

		statusCol.setCellFactory(column -> new TableCell<Book, String>() {
			@Override
			protected void updateItem(String status, boolean empty) {
				super.updateItem(status, empty);
				if (empty || status == null) {
					setText(null);
				} else {
					if (status.equalsIgnoreCase("a")) {
						setText("Active");
					} else if (status.equalsIgnoreCase("i")) {
						setText("Inactive");
					} else {
						setText(status); // fallback
					}
				}
			}
		});
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

		availabilityCol.setCellFactory(column -> new TableCell<Book, String>() {
			@Override
			protected void updateItem(String availability, boolean empty) {
				super.updateItem(availability, empty);
				if (empty || availability == null) {
					setText(null);
				} else {
					if (availability.equalsIgnoreCase("a")) {
						setText("Available");
					} else if (availability.equalsIgnoreCase("i")) {
						setText("Issued");
					} else {
						setText(availability);
					}
				}
			}
		});
		availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));

		actionsCol.setCellFactory(param -> new TableCell<Book, Void>() {
			private final Button updateBookBtn = new Button("Update");
			private final Button updateAvailBtn = new Button("Availability");

			{
				updateBookBtn.setOnAction(event -> {
					Book selectedBook = getTableView().getItems().get(getIndex());
					openEditBookPopup(selectedBook);
				});

				updateAvailBtn.setOnAction(event -> {
					Book selectedBook = getTableView().getItems().get(getIndex());
					updateAvailability(selectedBook);
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					HBox box = new HBox(10, updateBookBtn, updateAvailBtn);
					setGraphic(box);
				}
			}
		});

		loadBooks();
	}

	private void loadBooks() {
		try {
			List<Book> books = bookService.getAllBooks();
			ObservableList<Book> data = FXCollections.observableArrayList(books);
			bookTable.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void openEditBookPopup(Book book) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/com/LibraryManagement/application/books/UpdateBookForm.fxml"));
			Parent root = loader.load();

			UpdateBookDetails controller = loader.getController();
			controller.setBook(book);

			Stage popup = new Stage();
			popup.setTitle("Edit Book");
			popup.setScene(new Scene(root));
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setOnHiding(event -> loadBooks());
			popup.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateAvailability(Book book) {

		System.out.println("toggle Availability for " + book.getTitle());
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/com/LibraryManagement/application/books/UpdateBookAvailabilityForm.fxml"));
			Parent root = loader.load();

			UpdateBookAvailability controller = loader.getController();
			controller.setBook(book);

			Stage popup = new Stage();
			popup.setTitle("Edit Book");
			popup.setScene(new Scene(root));
			popup.initModality(Modality.APPLICATION_MODAL);
			popup.setOnHiding(event -> loadBooks());
			popup.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
