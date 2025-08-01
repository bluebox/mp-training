package com.libraryManagementSystem.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.libraryManagementSystem.App;
import com.libraryManagementSystem.domain.Book;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.BookServices;
import com.libraryManagementSystem.services.impl.BookServicesImpl;
import com.libraryManagementSystem.utilities.BookAvailability;
import com.libraryManagementSystem.utilities.BookCategory;
import com.libraryManagementSystem.utilities.BookStatus;

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

	private static Book bookIdSelected = null;

	private BookServices bookService = new BookServicesImpl();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Book> books;
		try {

			bookTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			books = bookService.getBooks();

			idColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));
			titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
			authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
			categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
			statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
			availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

			ObservableList<Book> bookList = FXCollections.observableArrayList();
			books.forEach(book -> {
				bookList.add(book);
			});

			bookTableView.setItems(bookList);

			for (TableColumn<Book, ?> column : bookTableView.getColumns()) {
				if (bookList.isEmpty() || column.getCellData(0) instanceof String) {
					@SuppressWarnings("unchecked")
					TableColumn<Book, String> stringColumn = (TableColumn<Book, String>) column;

					stringColumn.setCellFactory(col -> new TableCell<Book, String>() {
						private final Text text = new Text();

						{
							text.wrappingWidthProperty().bind(col.widthProperty().subtract(10));
							text.setStyle("-fx-padding: 5px;");
							setGraphic(text);
						}

						@Override
						protected void updateItem(String item, boolean empty) {
							super.updateItem(item, empty);
							text.setText(empty || item == null ? "" : item);
						}
					});
				}
			}
			addActionButtons();
		} catch (InvalidException e) {

			error.setText(e.getMessage());
		}

	}

	private void addActionButtons() {
		actionsColumn.setCellFactory(col -> new TableCell<Book, Void>() {

			private final Button editButton = new Button("Edit");
			private final Button deletBotton = new Button("Delete");
			private final HBox actionBox = new HBox(10, editButton, deletBotton);

			{
				deletBotton.setOnAction(event -> {

					Book bookData = bookTableView.getItems().get(getIndex());
					Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

					alert.setTitle("Delete Book ");
					alert.setHeaderText("Delete item : " + bookData.getTitle());
					alert.setContentText("Are you sure? ");

					Optional<ButtonType> result = alert.showAndWait();

					if (result.isPresent() && (result.get() == ButtonType.OK)) {

						Alert deleteShow = new Alert(Alert.AlertType.INFORMATION);

						try {
							bookService.deleteBook(bookData);
							initialize(null, null);
							deleteShow.setContentText(bookData.getTitle() + "Deleted successfully ");
							deleteShow.show();

						} catch (InvalidException e) {
							deleteShow.setContentText(e.getMessage());
							deleteShow.show();
						}

					} else {
						Alert deleteShow = new Alert(Alert.AlertType.INFORMATION);
						deleteShow.setContentText("Cannceled ");
						deleteShow.show();
					}

				});

				editButton.setOnAction(event -> {
					try {
						bookIdSelected = bookTableView.getItems().get(getIndex());
						App.setRoot("BookUpdate");
					} catch (IOException e) {
						e.printStackTrace();
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

	public static Book getBookIdSelected() {
		return bookIdSelected;
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
