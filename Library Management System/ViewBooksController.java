package com.LibraryManagement.controller;

import java.io.IOException;
import com.LibraryManagement.DAO.Implementation.BookDAOImplementation;
import com.LibraryManagement.models.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewBooksController {

	@FXML
	private TableView<Book> bookTable;

	@FXML
	private TableColumn<Book, Number> bookIdCol;
	@FXML
	private TableColumn<Book, String> titleCol;
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

	@FXML
	public void initialize() {
		addActionButtonsToTable();
		
		bookIdCol.setCellValueFactory(cellData -> cellData.getValue().bookIdProperty());
		titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
		authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
		categoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
		statusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
		availabilityCol.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty());
		
		 statusCol.setCellFactory(column -> new TableCell<>() {
		        @Override
		        protected void updateItem(String status, boolean empty) {
		            super.updateItem(status, empty);
		            setText(empty || status == null ? null : status.equals("A") ? "Active" : "Inactive");
		        }
		    });
		    statusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

		    // Custom display for Availability
		    availabilityCol.setCellFactory(column -> new TableCell<>() {
		        @Override
		        protected void updateItem(String availability, boolean empty) {
		            super.updateItem(availability, empty);
		            setText(empty || availability == null ? null : availability.equals("A") ? "Available" : "Issued");
		        }
		    });
		    availabilityCol.setCellValueFactory(cellData -> cellData.getValue().availabilityProperty());

		
		BookDAOImplementation bookservice = new BookDAOImplementation();
		ObservableList<Book> books = FXCollections.observableArrayList(bookservice.getAllBooks());
		bookTable.setItems(books);
	}

	private void addActionButtonsToTable() {
		actionsCol.setCellFactory(param -> new TableCell<>() {
			private final Button updateDetailsBtn = new Button("Update");
			private final Button updateAvailabilityBtn = new Button("Status");
			private final HBox actionBox = new HBox(10, updateDetailsBtn, updateAvailabilityBtn);

			{
				updateDetailsBtn.setOnAction(e -> {
					Book book = (Book) getTableView().getItems().get(getIndex());
					openUpdateDetailsPopup(book);
				});

				updateAvailabilityBtn.setOnAction(e -> {
					Book book = (Book) getTableView().getItems().get(getIndex());
					openUpdateStatusPopup(book);
				});

				actionBox.setStyle("-fx-alignment: CENTER;");
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				setGraphic(empty ? null : actionBox);
			}
		});
	}

	private void openUpdateDetailsPopup(Book book) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/com/LibraryManagement/application/books/UpdateBookForm.fxml"));
			Parent root = loader.load();
			UpdateBookDetails controller = loader.getController();
			controller.setBook(book);
			Stage stage = new Stage();
			stage.setTitle("Update Book Details");
			stage.setScene(new Scene(root, 400, 400));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openUpdateAvailabilityPopup(Book book) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/com/LibraryManagement/application/books/UpdateBookAvailabilityForm.fxml"));
			Parent root = loader.load();

			UpdateBookAvailability controller = loader.getController();
			controller.setBook(book);

			Stage stage = new Stage();
			stage.setTitle("Update Book Availability");
			stage.setScene(new Scene(root, 400, 400));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void openUpdateStatusPopup(Book book) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/com/LibraryManagement/application/books/UpdateStatusForm.fxml"));
			Parent root = loader.load();

			UpdateBookStatus controller = loader.getController();
			controller.setBook(book);

			Stage stage = new Stage();
			stage.setTitle("Update Book Availability");
			stage.setScene(new Scene(root, 400, 400));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}